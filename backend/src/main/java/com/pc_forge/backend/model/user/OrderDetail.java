package com.pc_forge.backend.model.user;

import com.pc_forge.backend.model.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_detail", schema = "public")
public class OrderDetail {
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order orderData;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_ean", nullable = false)
    private Product productEan;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}