package com.pc_forge.backend.controller.exceptions;

/**
 * Wyjątek wyrzucany w przypadku próby odwołania się do nieistniejącego zamówienia.
 * Jest to wyjątek typu checked, co oznacza, że musi być jawnie obsłużony w kodzie
 */
public class InvalidOrderDataException extends Exception {

    /**
     * Konstruktor wyjątku
     *
     * @param message wiadomość informująca o błędzie
     */
    public InvalidOrderDataException(String message) {
        super(message);
    }
}
