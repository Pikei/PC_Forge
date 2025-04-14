package com.pc_forge.backend.model.user;

import com.pc_forge.backend.model.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contains", schema = "public")
public class Contain {
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderData orderData;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Product ean;

}