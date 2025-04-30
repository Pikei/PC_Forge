package com.pc_forge.backend.model.user;

import com.pc_forge.backend.model.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shopping_cart", schema = "public")
public class ShoppingCart {
    @EmbeddedId
    private ShoppingCartId id;

    @MapsId("userId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("productEan")
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_ean", nullable = false)
    private Product product;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}