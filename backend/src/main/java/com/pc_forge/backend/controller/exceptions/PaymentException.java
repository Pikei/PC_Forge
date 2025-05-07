package com.pc_forge.backend.controller.exceptions;

/**
 * Wyjątek wyrzucany w przypadku błędu utworzenia sesji płatności.
 * Jest to wyjątek typu checked, co oznacza, że musi być jawnie obsłużony w kodzie
 */
public class PaymentException extends Exception {

    /**
     * Konstruktor wyjątku
     *
     * @param message wiadomość informująca o błędzie
     */
    public PaymentException(String message) {
        super(message);
    }
}
