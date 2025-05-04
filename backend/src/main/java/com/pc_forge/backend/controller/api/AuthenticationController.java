package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.exceptions.EmailFailureException;
import com.pc_forge.backend.controller.exceptions.UserAlreadyExistsException;
import com.pc_forge.backend.controller.exceptions.UserNotVerifiedException;
import com.pc_forge.backend.controller.service.UserService;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.view.body.auth.LoginBody;
import com.pc_forge.backend.view.response.auth.LoginResponse;
import com.pc_forge.backend.view.body.auth.RegistrationBody;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        } catch (EmailFailureException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginBody login) {
        String jwt = null;
        try {
            jwt = userService.login(login);
        } catch (UserNotVerifiedException e) {
            LoginResponse response = new LoginResponse();
            response.setSuccess(false);
            response.setError("USER_NOT_VERIFIED");
            if (e.getNewEmailSent()) {
                response.setError(response.getError() + "_NEW_EMAIL_SENT");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (EmailFailureException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if (jwt != null) {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            response.setSuccess(true);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/verify")
    public ResponseEntity<Void> verify(@RequestParam String token) {
        if (userService.verifyUser(token)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/profile")
    public User getProfile(@AuthenticationPrincipal User user) {
        return user;
    }
}
