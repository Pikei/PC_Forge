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
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final JWTService jwtService;
    private final EmailService emailService;
    private final OrderService orderService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

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

    public void createAccount(RegistrationBody registration) throws UserAlreadyExistsException, EmailFailureException {
        if (userRepository.findByEmailIgnoreCase(registration.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email \"" + registration.getEmail() + "\" is already linked to another user");
        }

        if (userRepository.findByUsernameIgnoreCase(registration.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username \"" + registration.getUsername() + "\" is already taken");
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

    public VerificationToken generateVerificationToken(User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationJWT(user));
        verificationToken.setCreated(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(user);
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }

    public String login(LoginBody login) throws UserNotVerifiedException, EmailFailureException {
        Optional<User> optUser = userRepository.findByUsernameIgnoreCase(login.getUsername());
        if (optUser.isPresent()) {
            User user = optUser.get();
            if (securityService.checkPassword(login.getPassword(), user.getPassword())) {
                if (user.getVerified()) {
                    return jwtService.createJWT(user);
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

    @Transactional
    public void deleteAccount(User user) {
        orderService.deleteOrder(user);
        userRepository.delete(user);
    }

    public void forgotPassword(String email) throws EmailFailureException, UserDoesNotExistException {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String token = jwtService.generatePasswordResetJWT(user);
            ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
            resetPasswordToken.setToken(token);
            resetPasswordToken.setCreated(new Timestamp(System.currentTimeMillis()));
            resetPasswordToken.setUser(user);
            resetPasswordTokenRepository.save(resetPasswordToken);
            emailService.sendPasswordResetEmail(user, token);
        } else {
            throw new UserDoesNotExistException("User with email " + email + " does not exist");
        }
    }

    @Transactional
    public void resetPassword(ResetPasswordBody resetPasswordBody) throws UserDoesNotExistException, TokenException {
        String email;
        try {
            email = jwtService.getResetPasswordEmail(resetPasswordBody.getToken());
        } catch (TokenExpiredException e) {
            throw new TokenException("URL expired");
        }
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
        if (optionalUser.isEmpty()) {
            throw new UserDoesNotExistException("User with email " + email + " does not exist");
        }
        User user = optionalUser.get();
        if (user.getResetTokens().isEmpty()) {
            throw new TokenException("Invalid URL");
        }
        String userToken = user.getResetTokens().getFirst().getToken();
        ResetPasswordToken savedToken = resetPasswordTokenRepository.findByToken(resetPasswordBody.getToken()).orElse(new ResetPasswordToken());
        if (!userToken.equals(resetPasswordBody.getToken()) || !savedToken.getToken().equals(resetPasswordBody.getToken())) {
            throw new TokenException("Invalid URL");
        }
        if (user.getResetTokens().getFirst().getCreated().before(new Timestamp(System.currentTimeMillis() - (1000 * 60 * 30)))) {
            throw new TokenException("URL expired");
        }
        user.setPassword(securityService.hashPassword(resetPasswordBody.getPassword()));
        userRepository.save(user);
        resetPasswordTokenRepository.deleteByUser(user);
    }
}
