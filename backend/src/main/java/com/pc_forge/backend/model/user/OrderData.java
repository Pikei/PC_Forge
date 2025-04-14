package com.pc_forge.backend.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_data", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "order_data_order_id_key", columnNames = {"order_id"})
})
public class OrderData {
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @Column(name = "status", nullable = false, length = 200)
    private String status;

    @Column(name = "city", nullable = false, length = 500)
    private String city;

    @Column(name = "street", nullable = false, length = 500)
    private String street;

    @Column(name = "house_number", nullable = false, length = 10)
    private String houseNumber;

    @Column(name = "apartment_number", nullable = false, length = 10)
    private String apartmentNumber;

    @Column(name = "postal_code", nullable = false, length = 7)
    private String postalCode;

    @Column(name = "phone_number", nullable = false, length = 9)
    private String phoneNumber;

    @Column(name = "first_name", nullable = false, length = Integer.MAX_VALUE)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = Integer.MAX_VALUE)
    private String lastName;

}