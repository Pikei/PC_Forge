package com.pc_forge.backend.controller.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * Klasa serwisu zabezpieczeń. Jest odpowiedzialna za szyfrowanie i sprawdzanie poprawności haseł użytkowników
 */
@Service
public class SecurityService {
    /**
     * Liczba rund używanych do generowania soli, czyli wartości losowej
     * używanej podczas szyfrowania za pomocą funkcji skrótu Bcrypt.
     */
    @Value("${encryption.salt.rounds}")
    private int saltRounds;

    /**
     * Wartość losowa używana podczas szyfrowania za pomocą funkcji skrótu Bcrypt
     */
    private String salt;

    /**
     * Metoda wywoływana po konstrukcji obiektu. Przypisuje wartość dla {@link #salt}
     */
    @PostConstruct
    public void postConstruct() {
        salt = BCrypt.gensalt(saltRounds);
    }

    /**
     * Szyfruje podane hasło, używając funkcji skrótu Bcrypt i wcześniej wygenerowanej soli.
     *
     * @param password Hasło do zaszyfrowania
     * @return Zaszyfrowane hasło
     */
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, salt);
    }

    /**
     * Sprawdza, czy podane hasło odpowiada jego zaszyfrowanej wersji.
     *
     * @param password       Hasło do sprawdzenia
     * @param hashedPassword Zaszyfrowana wersja hasła
     * @return {@code true} jeśli hasła się zgadzają, {@code false} w przeciwnym razie
     */
    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
