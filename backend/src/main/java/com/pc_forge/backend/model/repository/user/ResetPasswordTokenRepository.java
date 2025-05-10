package com.pc_forge.backend.model.repository.user;

import com.pc_forge.backend.model.entity.user.ResetPasswordToken;
import com.pc_forge.backend.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repozytorium/DAO dla {@link ResetPasswordToken}. Zawiera niezbędne kwerendy pobierające odpowiednie dane z bazy danych.
 */
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    /**
     * Kwerenda znajdująca encję w bazie danych na tokenu
     *
     * @param token Token JWT resetujący hasło
     * @return Obiekt klasy {@link ResetPasswordToken} obudowany w {@link Optional}
     */
    Optional<ResetPasswordToken> findByToken(String token);

    /**
     * Kwerenda usuwająca wszystkie tokeny resetujące hasło wygenerowane przez użytkownika z bazy danych.
     *
     * @param user Obiekt zalogowanego użytkownika
     */
    @Transactional
    @Modifying
    @Query("delete from ResetPasswordToken r where r.user = ?1")
    void deleteByUser(User user);
}