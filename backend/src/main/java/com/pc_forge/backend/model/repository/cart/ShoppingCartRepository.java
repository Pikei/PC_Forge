package com.pc_forge.backend.model.repository.cart;

import com.pc_forge.backend.model.entity.cart.ShoppingCart;
import com.pc_forge.backend.model.entity.cart.ShoppingCartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartId> {
    List<ShoppingCart> findById_UserId(Integer userId);

    @Transactional
    @Modifying
    @Query("delete from ShoppingCart s where s.id.userId = :userId")
    void deleteById_UserId(@Param("userId") Integer userId);

}