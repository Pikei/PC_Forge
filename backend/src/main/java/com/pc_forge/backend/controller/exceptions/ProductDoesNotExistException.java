package com.pc_forge.backend.controller.exceptions;

/**
 * Wyjątek wyrzucany w przypadku próby odwołania się do nieistniejącego produktu.
 * Jest to wyjątek typu checked, co oznacza, że musi być jawnie obsłużony w kodzie
 */
public class ProductDoesNotExistException extends Exception {

    /**
     * Konstruktor wyjątku
     *
     * @param message wiadomość informująca o błędzie
     */
    public ProductDoesNotExistException(String message) {
        super(message);
    }
}
