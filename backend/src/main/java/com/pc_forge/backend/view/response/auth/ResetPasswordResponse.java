package com.pc_forge.backend.view.response.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) wykorzystywanego podczas rejestracji użytkownika.
 * Obiekt tej klasy zwracany jest jako odpowiedź na żądanie rejestracji. Zawiera informacje o sukcesie rejestracji
 * oraz dodatkowe informacje w przypadku niepowodzenia.
 */
@Getter
@Setter
public class ResetPasswordResponse {
    /**
     * Flaga określająca czy rejestracja się powiodła
     */
    private boolean success;

    /**
     * Informacja o przyczynie błędu rejestracji.
     */
    private String error;
}
