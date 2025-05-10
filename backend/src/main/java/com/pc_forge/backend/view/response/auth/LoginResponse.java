package com.pc_forge.backend.view.response.auth;

import lombok.Data;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) wykorzystywanego podczas logowania użytkownika.
 * Obiekt tej klasy zwracany jest jako odpowiedź na żądanie logowania. Zawiera informacje o tokenie autoryzacyjnym JWT
 * oraz dodatkowe informacje w przypadku błędów logowania.
 */
@Data
public class LoginResponse {
    /**
     * Token autoryzacyjny JWT.
     */
    private String jwt;

    /**
     * Flaga określająca czy logowanie się powiodło
     */
    private Boolean success;

    /**
     * Kod błędu zwracany w przypadku błędu logowania.
     */
    private String error;
}
