package com.pc_forge.backend.controller.exceptions;

/**
 * Wyjątek wyrzucany w przypadku próby rejestracji nowego użytkownika na istniejący w bazie danych adres email lub nazwę użytkownika/login.
 * Jest to wyjątek typu checked, co oznacza, że musi być jawnie obsłużony w kodzie
 */
public class UserAlreadyExistsException extends Exception {

    /**
     * Konstruktor wyjątku
     *
     * @param message wiadomość informująca o błędzie
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
