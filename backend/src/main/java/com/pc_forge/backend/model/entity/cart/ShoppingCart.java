package com.pc_forge.backend.model.entity.cart;

import com.pc_forge.backend.model.entity.product.Product;
import com.pc_forge.backend.model.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji shopping_cart z bazy danych.
 * Przechowuje informacje o produktach dodanych do koszyka zakupowego użytkownika.
 */
@Getter
@Setter
@Entity
@Table(name = "shopping_cart", schema = "public")
public class ShoppingCart {
    /**
     * Złożony klucz główny koszyka zakupowego składający się z identyfikatora użytkownika i identyfikatora produktu
     */
    @EmbeddedId
    private ShoppingCartId id;

    /**
     * Obiekt użytkownika, do którego należy koszyk zakupowy.
     */
    @MapsId("userId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Obiekt produktu znajdującego się w koszyku.
     */
    @MapsId("productEan")
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_ean", nullable = false)
    private Product product;

    /**
     * Ilość danego produktu w koszyku zakupowym
     */
    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
