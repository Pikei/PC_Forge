package com.pc_forge.backend.model.database.user;

import com.pc_forge.backend.model.database.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "order_detail", schema = "public")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id", nullable = false)
    private Integer id;

    @NotNull
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotNull
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_ean", nullable = false)
    private Product productEan;

    @NotNull
    @Setter
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "cost", nullable = false)
    private Double cost;

    public void setCost() {
        this.cost = quantity * productEan.getPrice();
    }
}