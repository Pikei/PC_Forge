package com.pc_forge.backend.model.repository.user;

import com.pc_forge.backend.model.entity.user.ResetPasswordToken;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.model.entity.user.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repozytorium/DAO dla {@link VerificationToken}. Zawiera niezbędne kwerendy pobierające odpowiednie dane z bazy danych.
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    /**
     * Kwerenda znajdująca encję w bazie danych na tokenu
     *
     * @param token Token JWT weryfikujący konto
     * @return Obiekt klasy {@link ResetPasswordToken} obudowany w {@link Optional}
     */
    Optional<VerificationToken> findByToken(String token);

    /**
     * Kwerenda usuwająca wszystkie tokeny weryfikacyjne wygenerowane przez użytkownika z bazy danych.
     *
     * @param user Obiekt zalogowanego użytkownika
     */
    void deleteByUser(User user);
}