package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import com.pc_forge.backend.controller.api.constants.UrlPath;
import com.pc_forge.backend.controller.exceptions.*;
import com.pc_forge.backend.controller.service.UserService;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.view.body.auth.LoginBody;
import com.pc_forge.backend.view.body.auth.ResetPasswordBody;
import com.pc_forge.backend.view.response.StringResponse;
import com.pc_forge.backend.view.response.auth.LoginResponse;
import com.pc_forge.backend.view.body.auth.RegistrationBody;
import com.pc_forge.backend.view.response.auth.ResetPasswordResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler obsługujący żądania związane z kontem użytkownika.
 * Odpowiada za przetwarzanie rejestracji, logowania, pobraniu informacji o profilu, weryfikacji adresu email,
 * resetowaniem hasła i usuwaniem konta.
 */
@RestController
@CrossOrigin("http://localhost:4200/")
public class AuthenticationController {
    /**
     * Serwis obsługujący logikę związaną z kontem użytkownika
     */
    private final UserService userService;

    /**
     * Konstruktor wstrzykujący zależność do serwisu użytkownika
     *
     * @param userService Serwis użytkownika
     */
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Metoda dodająca nowe konto użytkownika do bazy danych.
     * Jeśli rejestracja przebiegnie pomyślnie, wysyłany jest do użytkownika email z linkiem weryfikacyjnym.
     *
     * @param registration Ciało żądania zawierające dane rejestracji
     * @return Jeśli rejestracja przebiegła pomyślnie zwracana jest pusta odpowiedź ze statusem HTTP 200 (OK).
     * W przypadku przechwycenia błędu wypisywana jest jego wiadomość w konsoli i zwracana jest odpowiedź
     * ze statusem HTTP 409 (CONFLICT) w przypadku gdy wystąpił błąd podczas dodawania użytkownika lub
     * ze statusem HTTP 500 (INTERNAL_SERVER_ERROR), gdy wystąpił błąd podczas wysyłania maila na wskazany adres
     */
    @PostMapping(UrlPath.REGISTER)
    public ResponseEntity<StringResponse> register(@Valid @RequestBody RegistrationBody registration) {
        try {
            userService.createAccount(registration);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new StringResponse(e.getMessage()));
        } catch (EmailFailureException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * @param login Ciało żądania zawierające dane logowania
     * @return Odpowiedź zawierająca token uwierzytelniający, jeśli logowanie przebiegło pomyślnie.
     * W przeciwnym razie zwracana jest odpowiedź ze statusem HTTP 401 (UNAUTHORIZED) lub HTTP 500 (INTERNAL_SERVER_ERROR)
     */
    @Transactional
    @PostMapping(UrlPath.LOGIN)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginBody login) {
        String jwt;
        try {
            jwt = userService.login(login);
        } catch (UserNotVerifiedException e) {
            LoginResponse response = new LoginResponse();
            response.setSuccess(false);
            response.setError("USER_NOT_VERIFIED");
            if (e.getNewEmailSent()) {
                response.setError(response.getError() + "_NEW_EMAIL_SENT");
            }
            return ResponseEntity.ok(response);
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

    /**
     * Weryfikuje konto użytkownika na podstawie otrzymanego tokenu.
     *
     * @param token Token JWT wysłany użytkownikowi na adres email
     * @return Jeśli weryfikacja przebiegła pomyślnie zwracana jest pusta odpowiedź ze statusem HTTP 200 (OK).
     * W przeciwnym razie zwracana jest odpowiedź ze statusem HTTP 409 (CONFLICT)
     */
    @PostMapping(UrlPath.VERIFY)
    public ResponseEntity<Void> verify(@RequestParam(RequestParams.TOKEN) String token) {
        if (userService.verifyUser(token)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Zwraca odpowiedź zawierającą dane użytkownika
     *
     * @param user Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @return Odpowiedź ze statusem HTTP 200 (OK).
     * Jeśli użytkownik nie jest uwierzytelniony, Spring Security zwróci odpowiedź ze statusem HTTP 403 (FORBIDDEN).
     */
    @GetMapping(UrlPath.PROFILE)
    public ResponseEntity<User> getProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }

    /**
     * Trwale usuwa konto użytkownika z bazy danych
     *
     * @param user Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @return Odpowiedź ze statusem HTTP 200 (OK).
     * Jeśli użytkownik nie jest uwierzytelniony, Spring Security zwróci odpowiedź ze statusem HTTP 403 (FORBIDDEN).
     */
    @PostMapping(UrlPath.DELETE_ACCOUNT)
    public ResponseEntity<Void> deleteAccount(@AuthenticationPrincipal User user) {
        userService.deleteAccount(user);
        return ResponseEntity.ok().build();
    }

    /**
     * @param email Adres email użytkownika, na który ma zostać wysłany link resetujący hasło
     * @return Odpowiedź ze statusem HTTP 200 (OK) w przypadku gdy nie został wyrzucony żaden wyjątek,
     * HTTP 500 (INTERNAL_SERVER_ERROR) w przypadku gdy wysyłanie maila nie powiodło się lub
     * HTTP 400 (BAD_REQUEST) w przypadku gdy użytkownik o podanym adresie email nie istnieje
     */
    @PostMapping(UrlPath.PASSWORD_FORGOT)
    public ResponseEntity<Void> forgotPassword(@RequestParam String email) {
        try {
            userService.forgotPassword(email);
            return ResponseEntity.ok().build();
        } catch (EmailFailureException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (UserDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param resetPasswordBody Ciało żądania zawierające token resetujący i nowe hasło
     * @return Odpowiedź zawierająca dane czy reset hasła się powiódł i ewentualną wiadomość błędu.
     * Możliwe są zwracane odpowiedzi ze statusem HTTP 200 (OK) w przypadku powodzenia oraz
     * HTTP 400 (BAD_REQUEST) w przypadku niepowodzenia (nieprawidłowy token lub użytkownik nie istnieje).
     */
    @PostMapping(UrlPath.PASSWORD_RESET)
    public ResponseEntity<ResetPasswordResponse> resetPassword(@Valid @RequestBody ResetPasswordBody resetPasswordBody) {
        ResetPasswordResponse response = new ResetPasswordResponse();
        try {
            userService.resetPassword(resetPasswordBody);
            response.setSuccess(true);
            return ResponseEntity.ok(response);
        } catch (UserDoesNotExistException | TokenException e) {
            response.setSuccess(false);
            response.setError(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
