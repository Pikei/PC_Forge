package com.pc_forge.backend.model.entity.order;

import com.pc_forge.backend.model.entity.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji order_detail z bazy danych.
 * Zawiera szczegóły dotyczące zamówienia, czyli zamówiony produkt oraz liczbę jego sztuk.
 */
@Getter
@Setter
@Entity
@Table(name = "order_detail", schema = "public")
public class OrderDetail {
    /**
     * Identyfikator detali zamówienia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id", nullable = false)
    private Long id;

    /**
     * Obiekt zamówienia, dla którego został zamówiony produkt.
     */
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * Obiekt produktu znajdującego się w zamówieniu.
     */
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_ean", nullable = false)
    private Product product;

    /**
     * Liczba sztuk produktu znajdująca się w zamówieniu.
     */
    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /**
     * Sumaryczny koszt zamówionych sztuk produktu.
     */
    @NotNull
    @Column(name = "cost", nullable = false)
    private Double cost;

}