package com.pc_forge.backend.controller.exceptions;

import lombok.Getter;

/**
 * Wyjątek wyrzucany w przypadku próby zalogowania się przez użytkownika w przypadku gdy nie został on zweryfikowany.
 * Jest to wyjątek typu checked, co oznacza, że musi być jawnie obsłużony w kodzie
 */
@Getter
public class UserNotVerifiedException extends Exception {
    /**
     * Flaga określająca czy nowy mail z linkiem weryfikacyjnym został wysłany do użytkownika
     */
    private final Boolean newEmailSent;

    /**
     * Konstruktor wyjątku
     *
     * @param newEmailSent Flaga określająca czy nowy mail z linkiem weryfikacyjnym został wysłany do użytkownika
     */
    public UserNotVerifiedException(Boolean newEmailSent) {
        this.newEmailSent = newEmailSent;
    }
}
