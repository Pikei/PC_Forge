package com.pc_forge.backend.model.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Klasa będąca reprezentacją encji reset_token w bazie danych. Zawiera tokeny wygenerowane przez użytkownika,
 * podczas resetowania hasła. Jeśli hasło zostanie zresetowane, wszystkie tokeny wygenerowane przez użytkownika
 * zostają usunięte z bazy danych.
 */
@Getter
@Setter
@Entity
@Table(name = "reset_token")
public class ResetPasswordToken {

    /**
     * Identyfikator tokenu
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Token JWT używany do resetowania hasła
     */
    @Column(name = "token", nullable = false, unique = true)
    private String token;

    /**
     * Data stworzenia tokenu.
     */
    @Column(name = "created", nullable = false)
    private Timestamp created;

    /**
     * Obiekt użytkownika, który wygenerował token
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
