package com.pc_forge.backend.view.body.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) wykorzystywanego podczas logowania użytkownika.
 * Odbierane jest przez kontroler jako Request Body.
 */
@Data
public class LoginBody {
    /**
     * Nazwa użytkownika.
     */
    @NotNull
    @NotBlank
    @Size(min = 3, max = 64)
    private String username;

    /**
     * Hasło użytkownika.
     */
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")
    private String password;
}
