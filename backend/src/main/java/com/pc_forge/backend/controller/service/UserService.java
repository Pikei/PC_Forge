package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.controller.exceptions.UserAlreadyExistsException;
import com.pc_forge.backend.model.database.user.User;
import com.pc_forge.backend.model.database.user.repository.UserRepository;
import com.pc_forge.backend.view.api.model.Login;
import com.pc_forge.backend.view.api.model.Registration;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final JWTService jwtService;

    public UserService(UserRepository userRepository, SecurityService securityService, JWTService jwtService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.jwtService = jwtService;
    }

    public User createAccount(Registration registration) throws UserAlreadyExistsException {
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
        return userRepository.save(user);
    }

    public String login(Login login) {
        Optional<User> optUser = userRepository.findByUsernameIgnoreCase(login.getUsername());
        if (optUser.isPresent()) {
            User user = optUser.get();
            if (securityService.checkPassword(login.getPassword(), user.getPassword())) {
                return jwtService.createJWT(user);
            }
        }
        return null;
    }
}
