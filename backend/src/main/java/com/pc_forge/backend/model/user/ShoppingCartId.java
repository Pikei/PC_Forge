package com.pc_forge.backend.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartId implements Serializable {
    @Serial
    private static final long serialVersionUID = 5435305916282428170L;
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @NotNull
    @Column(name = "product_ean", nullable = false)
    private Long productEan;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShoppingCartId entity = (ShoppingCartId) o;
        return Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.productEan, entity.productEan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productEan);
    }

}