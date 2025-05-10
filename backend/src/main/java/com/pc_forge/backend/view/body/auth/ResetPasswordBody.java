package com.pc_forge.backend.view.body.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) wykorzystywanego podczas resetowania hasła przez użytkownika.
 * Odbierane jest przez kontroler jako Request Body.
 */
@Getter
@Setter
public class ResetPasswordBody {
    /**
     * Token JWT resetujący hasło.
     */
    @NotNull
    @NotBlank
    private String token;

    /**
     * Nowe hasło użytkownika.
     */
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")
    private String password;
}
