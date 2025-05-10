package com.pc_forge.backend.model.repository.user;

import com.pc_forge.backend.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repozytorium/DAO dla {@link User}. Zawiera niezbędne kwerendy pobierające odpowiednie dane z bazy danych.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Kwerenda znajdująca użytkownika po jego adresie email.
     *
     * @param email adres email użytkownika
     * @return Obiekt klasy {@link User} obudowany w {@link Optional}
     */
    Optional<User> findByEmailIgnoreCase(String email);

    /**
     * Kwerenda znajdująca użytkownika po jego nazwie.
     *
     * @param username nazwa użytkownika
     * @return Obiekt klasy {@link User} obudowany w {@link Optional}
     */
    Optional<User> findByUsernameIgnoreCase(String username);
}
