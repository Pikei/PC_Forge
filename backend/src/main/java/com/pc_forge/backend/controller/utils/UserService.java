package com.pc_forge.backend.controller.utils;

import com.pc_forge.backend.controller.exceptions.UserAlreadyExistsException;
import com.pc_forge.backend.model.database.user.User;
import com.pc_forge.backend.model.database.user.repository.UserRepository;
import com.pc_forge.backend.view.api.model.Registration;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        user.setPassword(registration.getPassword());
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setPhoneNumber(registration.getPhoneNumber());
        return userRepository.save(user);
    }
}
