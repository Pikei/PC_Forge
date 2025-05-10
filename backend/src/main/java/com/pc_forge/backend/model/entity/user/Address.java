package com.pc_forge.backend.model.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji address w bazie danych. Zawiera adresowe, pod które ma zostać dostarczone zamówienie.
 */
@Getter
@Setter
@Entity
@Table(name = "address", schema = "public")
public class Address {
    /**
     * Identyfikator adresu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Long id;

    /**
     * Miasto
     */
    @NotNull
    @Column(name = "city", nullable = false, length = Integer.MAX_VALUE)
    private String city;

    /**
     * Kod pocztowy.
     */
    @NotNull
    @Column(name = "postal_code", nullable = false, length = Integer.MAX_VALUE)
    private String postalCode;

    /**
     * Ulica.
     */
    @NotNull
    @Column(name = "street", nullable = false, length = Integer.MAX_VALUE)
    private String street;

    /**
     * Numer domu.
     */
    @NotNull
    @Column(name = "house_number", nullable = false, length = Integer.MAX_VALUE)
    private String houseNumber;

    /**
     * Numer mieszkania.
     */
    @Column(name = "apartment_number", length = Integer.MAX_VALUE)
    private String apartmentNumber;

}