package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.controller.exceptions.EmailFailureException;
import com.pc_forge.backend.controller.exceptions.UserAlreadyExistsException;
import com.pc_forge.backend.controller.exceptions.UserDoesNotExistException;
import com.pc_forge.backend.controller.exceptions.UserNotVerifiedException;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.model.entity.user.VerificationToken;
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

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final JWTService jwtService;
    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final OrderService orderService;

    public UserService(
            UserRepository userRepository,
            SecurityService securityService,
            JWTService jwtService,
            EmailService emailService,
            VerificationTokenRepository verificationTokenRepository, OrderService orderService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.verificationTokenRepository = verificationTokenRepository;
        this.orderService = orderService;
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
        VerificationToken verificationToken = verify(user);
        emailService.sendVerificationEmail(verificationToken);
        userRepository.save(user);
    }

    public VerificationToken verify(User user) {
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
                        VerificationToken token = verify(user);
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
            emailService.sendPasswordResetEmail(user, token);
        } else {
            throw new UserDoesNotExistException("User with email " + email + " does not exist");
        }
    }

    public void resetPassword(ResetPasswordBody resetPasswordBody) throws UserDoesNotExistException {
        String email = jwtService.getResetPasswordEmail(resetPasswordBody.getToken());
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(securityService.hashPassword(resetPasswordBody.getPassword()));
            userRepository.save(user);
        } else {
            throw new UserDoesNotExistException("User with email " + email + " does not exist");
        }
    }
}
