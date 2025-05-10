package com.pc_forge.backend.model.entity.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Klasa będąca reprezentacją klucza złożonego koszyka zakupowego użytkownika.
 * Klucz składa się z identyfikatora użytkownika i identyfikatora produktu.
 */
@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartId implements Serializable {
    @Serial
    private static final long serialVersionUID = 5435305916282428170L;

    /**
     * Identyfikator użytkownika.
     */
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * Identyfikator produktu.
     */
    @NotNull
    @Column(name = "product_ean", nullable = false)
    private Long productEan;
}