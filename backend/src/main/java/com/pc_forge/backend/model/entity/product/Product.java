package com.pc_forge.backend.model.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa abstrakcyjna będąca reprezentacją encji product z bazy danych. Jest to klasa bazowa dla wszystkich typów produktów.
 * Zawiera informacje wspólne dla każdej kategorii, np. nazwa, cena, opis, kod producenta.
 */
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "product", schema = "public")
@DiscriminatorColumn(name = "category", discriminatorType = DiscriminatorType.STRING)
public abstract class Product {
    /**
     * Identyfikator produktu, kod EAN (European Article Number).
     */
    @Id
    @Column(name = "ean", nullable = false, unique = true)
    private Long id;

    /**
     * Kod kategorii produktu.
     */
    @Size(max = 255)
    @NotNull
    @Column(name = "category", nullable = false, insertable = false, updatable = false)
    private String category;

    /**
     * Nazwa produktu
     */
    @Size(max = 1000)
    @NotNull
    @Column(name = "name", nullable = false, length = 1000)
    private String name;

    /**
     * Opis produktu w kodzie html
     */
    @NotNull
    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    /**
     * Cena produktu
     */
    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    /**
     * Kod producenta
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "producer_code", nullable = false, length = 200)
    private String producerCode;

    /**
     * Nazwa producenta
     */
    @Size(max = 255)
    @NotNull
    @Column(name = "producer", nullable = false)
    private String producer;
}
