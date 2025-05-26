package com.pc_forge.backend.view.body.auth;

import com.pc_forge.backend.view.util.NullOrNotBlank;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) wykorzystywanego podczas rejestracji użytkownika.
 * Odbierane jest przez kontroler jako Request Body.
 */
@Data
public class RegistrationBody {
    /**
     * Nazwa użytkownika.
     */
    @NotNull
    @NotBlank
    @Size(min = 3, max = 64)
    private String username;

    /**
     * Adres email użytkownika.
     */
    @Email
    @NotNull
    @NotBlank
    private String email;

    /**
     * Hasło użytkownika.
     */
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")
    private String password;

    /**
     * Imię użytkownika.
     */
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]{3,64}$")
    private String firstName;

    /**
     * Nazwisko użytkownika.
     */
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+(-[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+)?$")
    private String lastName;

    /**
     * Numer telefonu użytkownika.
     */
    @Pattern(regexp = "^\\d{9}$")
    @NullOrNotBlank
    private String phoneNumber;
}
