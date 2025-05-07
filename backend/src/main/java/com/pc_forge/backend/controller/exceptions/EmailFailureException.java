package com.pc_forge.backend.controller.exceptions;

/**
 * Wyjątek wyrzucany w przypadku błędu podczas wysyłania maili.
 * Jest to wyjątek typu checked, co oznacza, że musi być jawnie obsłużony w kodzie
 */
public class EmailFailureException extends Exception {

    /**
     * Konstruktor wyjątku
     *
     * @param message wiadomość informująca o błędzie
     */
    public EmailFailureException(String message) {
        super(message);
    }
}
