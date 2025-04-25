package com.pc_forge.backend.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "order_data", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "order_data_order_id_key", columnNames = {"order_id"})
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Setter
    @Column(name = "status", nullable = false, length = 200)
    private String status;

    @NotNull
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private Address shippingAddress;

    @NotNull
    @Setter
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Setter
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @NotNull
    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    public void setTotalCost(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails) {
            if (Objects.equals(orderDetail.getOrder().getId(), this.getId())) {
                this.totalCost += orderDetail.getCost();
            }
        }
    }
}