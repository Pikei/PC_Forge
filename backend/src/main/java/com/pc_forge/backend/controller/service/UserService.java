package com.pc_forge.backend.controller.service;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.pc_forge.backend.controller.exceptions.*;
import com.pc_forge.backend.model.entity.user.ResetPasswordToken;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.model.entity.user.VerificationToken;
import com.pc_forge.backend.model.repository.user.ResetPasswordTokenRepository;
import com.pc_forge.backend.model.repository.user.UserRepository;
import com.pc_forge.backend.model.repository.user.VerificationTokenRepository;
import com.pc_forge.backend.view.body.auth.LoginBody;
import com.pc_forge.backend.view.body.auth.RegistrationBody;
import com.pc_forge.backend.view.body.auth.ResetPasswordBody;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * Serwis obsługujący operacje związane z użytkownikami systemu.
 */
@Service
public class UserService {
    /**
     * Repozytorium/DAO użytkownika
     */
    private final UserRepository userRepository;

    /**
     * Serwis zabezpieczeń, służący do szyfrowania i sprawdzania haseł
     */
    private final SecurityService securityService;

    /**
     * Serwis JWT, służący do generowania i dekodowania tokenów JWT
     */
    private final JWTService jwtService;

    /**
     * Serwis email, służący do wysyłania wiadomości
     */
    private final EmailService emailService;

    /**
     * Serwis zamówień użytkownika, służący do operacji związanych z zamówieniami
     */
    private final OrderService orderService;

    /**
     * Repozytorium/DAO tokenów weryfikacyjnych
     */
    private final VerificationTokenRepository verificationTokenRepository;

    /**
     * Repozytorium/DAO tokenów resetujących hasło
     */
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    /**
     * Konstruktor serwisu użytkowników wstrzykujący niezbędne zależności
     *
     * @param userRepository               Repozytorium użytkownika
     * @param securityService              użytkownika
     * @param jwtService                   tokenów weryfikacyjnych
     * @param emailService                 tokenów resetujących hasło
     * @param orderService                 Serwis zamówień
     * @param verificationTokenRepository  Repozytorium tokenów weryfikacyjnych
     * @param resetPasswordTokenRepository Repozytorium tokenów resetujących hasło
     */
    public UserService(
            UserRepository userRepository,
            SecurityService securityService,
            JWTService jwtService,
            EmailService emailService,
            OrderService orderService,
            VerificationTokenRepository verificationTokenRepository,
            ResetPasswordTokenRepository resetPasswordTokenRepository) {
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.orderService = orderService;
        this.verificationTokenRepository = verificationTokenRepository;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
    }

    /**
     * Metoda służąca do stworzenia nowego konta użytkownika
     *
     * @param registration Dane rejestracyjne użytkownika
     * @throws UserAlreadyExistsException gdy użytkownik o podanym emailu lub nazwie już istnieje
     * @throws EmailFailureException      gdy wystąpi błąd podczas wysyłania emaila
     */
    public void createAccount(RegistrationBody registration) throws UserAlreadyExistsException, EmailFailureException {
        if (userRepository.findByEmailIgnoreCase(registration.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email \"" + registration.getEmail() + "\" jest już przypisany do innego użytkownika");
        }

        if (userRepository.findByUsernameIgnoreCase(registration.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Nazwa użytkownika \"" + registration.getUsername() + "\" jest już zajęta");
        }
        User user = new User();
        user.setEmail(registration.getEmail());
        user.setUsername(registration.getUsername());
        user.setPassword(securityService.hashPassword(registration.getPassword()));
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setPhoneNumber(registration.getPhoneNumber());
        VerificationToken verificationToken = generateVerificationToken(user);
        emailService.sendVerificationEmail(verificationToken);
        userRepository.save(user);
    }

    /**
     * Generuje token weryfikacyjny dla użytkownika.
     *
     * @param user obiekt użytkownika, dla którego generowany jest token
     * @return Wygenerowany token weryfikacyjny
     */
    public VerificationToken generateVerificationToken(User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationToken(user));
        verificationToken.setCreated(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(user);
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }

    /**
     * Loguje użytkownika do systemu.
     *
     * @param login Dane logowania
     * @return Token JWT lub {@code null} jeśli logowanie się nie powiodło
     * @throws UserNotVerifiedException gdy konto nie jest zweryfikowane
     * @throws EmailFailureException    gdy wystąpi błąd podczas wysyłania emaila, w przypadku
     *                                  gdy poprzedni token weryfikacyjny wygasł i miała być wysłana nowa wiadomość.
     */
    public String login(LoginBody login) throws UserNotVerifiedException, EmailFailureException {
        Optional<User> optUser = userRepository.findByUsernameIgnoreCase(login.getUsername());
        if (optUser.isPresent()) {
            User user = optUser.get();
            if (securityService.checkPassword(login.getPassword(), user.getPassword())) {
                if (user.getVerified()) {
                    return jwtService.generateLoginToken(user);
                } else {
                    List<VerificationToken> tokens = user.getVerificationTokens();
                    boolean resend = tokens.isEmpty() || tokens.getFirst().getCreated().before(new Timestamp(System.currentTimeMillis() - (3600 * 1000)));
                    if (resend) {
                        VerificationToken token = generateVerificationToken(user);
                        verificationTokenRepository.save(token);
                        emailService.sendVerificationEmail(token);
                    }
                    throw new UserNotVerifiedException(resend);
                }
            }
        }
        return null;
    }

    /**
     * Weryfikuje konto użytkownika.
     *
     * @param token Token weryfikacyjny
     * @return {@code true} jeśli weryfikacja się powiodła, {@code false} w przeciwnym razie
     */
    @Transactional
    public boolean verifyUser(String token) {
        Optional<VerificationToken> optToken = verificationTokenRepository.findByToken(token);
        if (optToken.isEmpty()) {
            return false;
        }
        VerificationToken verificationToken = optToken.get();
        User user = verificationToken.getUser();
        if (!user.getVerified()) {
            user.setVerified(true);
            userRepository.save(user);
            verificationTokenRepository.deleteByUser(user);
            return true;
        }
        return false;
    }

    /**
     * Usuwa konto użytkownika i zamówienia przez niego złożone.
     *
     * @param user obiekt zalogowanego użytkownika dostarczany przez Spring Security, którego konto ma zostać usunięte
     */
    @Transactional
    public void deleteAccount(User user) {
        orderService.deleteOrder(user);
        userRepository.delete(user);
    }

    /**
     * Inicjuje proces resetowania hasła, wysyłając odpowiedniego maila na wskazany adres, o ile ten istnieje w bazie danych.
     *
     * @param email Email użytkownika
     * @throws EmailFailureException     gdy wystąpi błąd podczas wysyłania maila
     * @throws UserDoesNotExistException gdy użytkownik nie istnieje
     */
    public void forgotPassword(String email) throws EmailFailureException, UserDoesNotExistException {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String token = jwtService.generatePasswordResetToken(user);
            ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
            resetPasswordToken.setToken(token);
            resetPasswordToken.setCreated(new Timestamp(System.currentTimeMillis()));
            resetPasswordToken.setUser(user);
            resetPasswordTokenRepository.save(resetPasswordToken);
            emailService.sendPasswordResetEmail(user, token);
        } else {
            throw new UserDoesNotExistException("Użytkownik o emailu " + email + " nie istnieje");
        }
    }

    /**
     * Resetuje hasło użytkownika na podstawie dostarczonych danych.
     *
     * @param resetPasswordBody Dane do resetowania hasła
     * @throws UserDoesNotExistException gdy użytkownik nie istnieje
     * @throws TokenException            gdy token jest nieprawidłowy lub wygasł
     */
    @Transactional
    public void resetPassword(ResetPasswordBody resetPasswordBody) throws UserDoesNotExistException, TokenException {
        String email;
        try {
            email = jwtService.getResetPasswordEmail(resetPasswordBody.getToken());
        } catch (TokenExpiredException e) {
            throw new TokenException("Link wygasł");
        }
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
        if (optionalUser.isEmpty()) {
            throw new UserDoesNotExistException("Użytkownik o emailu " + email + " nie istnieje");
        }
        User user = optionalUser.get();
        if (user.getResetTokens().isEmpty()) {
            throw new TokenException("Nieprawidłowy link");
        }
        String userToken = user.getResetTokens().getFirst().getToken();
        ResetPasswordToken savedToken = resetPasswordTokenRepository.findByToken(resetPasswordBody.getToken()).orElse(new ResetPasswordToken());
        if (!userToken.equals(resetPasswordBody.getToken()) || !savedToken.getToken().equals(resetPasswordBody.getToken())) {
            throw new TokenException("Nieprawidłowy link");
        }
        if (user.getResetTokens().getFirst().getCreated().before(new Timestamp(System.currentTimeMillis() - (1000 * 60 * 30)))) {
            throw new TokenException("Link wygasł");
        }
        user.setPassword(securityService.hashPassword(resetPasswordBody.getPassword()));
        userRepository.save(user);
        resetPasswordTokenRepository.deleteByUser(user);
    }
}