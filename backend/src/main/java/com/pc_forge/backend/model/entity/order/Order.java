package com.pc_forge.backend.model.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pc_forge.backend.model.entity.user.Address;
import com.pc_forge.backend.model.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "order_data", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "order_data_order_id_key", columnNames = {"order_id"})
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private Address shippingAddress;

    @NotNull
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @NotNull
    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "status")
    private OrderStatus status;

    public String getAddress() {
        String addr = shippingAddress.getCity() + ", ";
        addr += shippingAddress.getStreet() + " " + shippingAddress.getHouseNumber();
        if (shippingAddress.getApartmentNumber() != null) {
            addr += "/" + shippingAddress.getApartmentNumber();
        }
        addr += " " + shippingAddress.getPostalCode();
        return addr;
    }

    public String getCustomer() {
        return user.getFirstName() + " " + user.getLastName();
    }

}