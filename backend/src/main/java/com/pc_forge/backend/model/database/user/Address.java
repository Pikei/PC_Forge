package com.pc_forge.backend.model.database.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address", schema = "public")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "city", nullable = false, length = Integer.MAX_VALUE)
    private String city;

    @NotNull
    @Column(name = "postal_code", nullable = false, length = Integer.MAX_VALUE)
    private String postalCode;

    @NotNull
    @Column(name = "street", nullable = false, length = Integer.MAX_VALUE)
    private String street;

    @NotNull
    @Column(name = "house_number", nullable = false, length = Integer.MAX_VALUE)
    private String houseNumber;

    @Column(name = "apartment_number", length = Integer.MAX_VALUE)
    private String apartmentNumber;

}