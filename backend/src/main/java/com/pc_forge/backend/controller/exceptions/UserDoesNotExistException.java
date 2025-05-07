package com.pc_forge.backend.controller.exceptions;

/**
 * Wyjątek wyrzucany w przypadku próby wysłania maila na adres nieistniejącego użytkownika, np. przy resetowaniu hasła.
 * Jest to wyjątek typu checked, co oznacza, że musi być jawnie obsłużony w kodzie
 */
public class UserDoesNotExistException extends Exception {

    /**
     * Konstruktor wyjątku
     *
     * @param message wiadomość informująca o błędzie
     */
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
