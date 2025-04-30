package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.exceptions.UserAlreadyExistsException;
import com.pc_forge.backend.controller.service.UserService;
import com.pc_forge.backend.model.user.User;
import com.pc_forge.backend.view.body.LoginBody;
import com.pc_forge.backend.view.response.LoginResponse;
import com.pc_forge.backend.view.body.RegistrationBody;
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

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegistrationBody registration) {
        try {
            userService.createAccount(registration);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginBody login) {
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
