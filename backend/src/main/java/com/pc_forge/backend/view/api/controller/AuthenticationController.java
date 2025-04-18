package com.pc_forge.backend.view.api.controller;

import com.pc_forge.backend.controller.exceptions.UserAlreadyExistsException;
import com.pc_forge.backend.controller.service.UserService;
import com.pc_forge.backend.model.database.user.User;
import com.pc_forge.backend.view.api.model.Login;
import com.pc_forge.backend.view.api.model.LoginResponse;
import com.pc_forge.backend.view.api.model.Registration;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> register(@Valid @RequestBody Registration registration) {
        try {
            userService.createAccount(registration);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody Login login) {
        String jwt = userService.login(login);
        if (jwt != null) {
            LoginResponse token = new LoginResponse();
            token.setJwt(jwt);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/profile")
    public User getProfile(@AuthenticationPrincipal User user) {
        return user;
    }
}
