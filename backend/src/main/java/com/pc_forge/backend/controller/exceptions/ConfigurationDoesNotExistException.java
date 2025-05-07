package com.pc_forge.backend.controller.exceptions;

/**
 * Wyjątek wyrzucany w przypadku próby odwołania się do nieistniejącej konfiguracji.
 * Jest to wyjątek typu checked, co oznacza, że musi być jawnie obsłużony w kodzie
 */
public class ConfigurationDoesNotExistException extends Exception {

    /**
     * Konstruktor wyjątku
     *
     * @param message wiadomość informująca o błędzie
     */
    public ConfigurationDoesNotExistException(String message) {
        super(message);
    }
}
