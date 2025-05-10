package com.pc_forge.backend.model.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Klasa będąca reprezentacją encji verification_token w bazie danych. Zawiera tokeny wygenerowane przez użytkownika,
 * podczas weryfikacji próby zalogowania się, jeśli adres email, nie został jeszcze zweryfikowany przez użytkownika.
 * Jeśli konto zostanie zweryfikowane, wszystkie tokeny wygenerowane przez użytkownika zostają usunięte z bazy danych.
 */
@Getter
@Setter
@Entity
@Table(name = "verification_token")
public class VerificationToken {
    /**
     * Identyfikator tokenu
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Token JWT używany do weryfikacji konta użytkownika
     */
    @Column(name = "token", nullable = false, unique = true)
    private String token;

    /**
     * Data stworzenia tokenu.
     */
    @Column(name = "created", nullable = false)
    private Timestamp created;

    /**
     * Obiekt użytkownika, dla którego konta przypisany jest token
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
