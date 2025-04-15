package com.pc_forge.backend.model.database.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "order_data", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "order_data_order_id_key", columnNames = {"order_id"})
})
public class Order {
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @Column(name = "status", nullable = false, length = 200)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private Address shippingAddress;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

}