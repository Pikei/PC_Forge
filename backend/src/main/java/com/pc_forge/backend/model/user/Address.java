package com.pc_forge.backend.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address", schema = "public")
public class Address {
    @Id
    @Column(name = "address_id", nullable = false)
    private Integer id;

    @Column(name = "city", nullable = false, length = Integer.MAX_VALUE)
    private String city;

    @Column(name = "postal_code", nullable = false, length = Integer.MAX_VALUE)
    private String postalCode;

    @Column(name = "street", nullable = false, length = Integer.MAX_VALUE)
    private String street;

    @Column(name = "house_number", nullable = false, length = Integer.MAX_VALUE)
    private String houseNumber;

    @Column(name = "apartment_number", length = Integer.MAX_VALUE)
    private String apartmentNumber;

}