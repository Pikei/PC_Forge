package com.pc_forge.backend.model.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa będąca reprezentacją encji user_data w bazie danych. Jest to klasa przechowująca informacje dotyczące konta użytkownika.
 */
@Getter
@Setter
@Entity
@Table(name = "user_data", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "user_data_user_id_key", columnNames = {"user_id"})
})
public class User {
    /**
     * Identyfikator użytkownika.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    @JsonIgnore
    private Long id;

    /**
     * Nazwa konta użytkownika.
     */
    @Column(name = "username", nullable = false, length = 200)
    private String username;

    /**
     * Adres email użytkownika.
     */
    @Column(name = "email", nullable = false, length = 200)
    private String email;

    /**
     * Flaga określająca, czy użytkownik jest zweryfikowany.
     */
    @Column(name = "verified", nullable = false)
    private Boolean verified = false;

    /**
     * Zaszyfrowane hasło użytkownika.
     */
    @JsonIgnore
    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    /**
     * Imię użytkownika.
     */
    @Column(name = "first_name", nullable = false, length = Integer.MAX_VALUE)
    private String firstName;

    /**
     * Nazwisko użytkownika.
     */
    @Column(name = "last_name", nullable = false, length = Integer.MAX_VALUE)
    private String lastName;

    /**
     * Numer telefonu użytkownika.
     */
    @Column(name = "phone_number", length = Integer.MAX_VALUE)
    private String phoneNumber;

    /**
     * Zmapowana lista tokenów weryfikacyjnych wygenerowanych przez użytkownika.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id desc")
    private List<VerificationToken> verificationTokens = new ArrayList<>();

    /**
     * Zmapowana lista tokenów resetujących hasło wygenerowanych przez użytkownika.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id desc")
    private List<ResetPasswordToken> resetTokens = new ArrayList<>();

}