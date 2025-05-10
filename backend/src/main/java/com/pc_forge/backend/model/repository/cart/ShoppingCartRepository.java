package com.pc_forge.backend.model.repository.cart;

import com.pc_forge.backend.model.entity.cart.ShoppingCart;
import com.pc_forge.backend.model.entity.cart.ShoppingCartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repozytorium/DAO dla {@link ShoppingCart}. Zawiera niezbędne kwerendy pobierające odpowiednie dane z bazy danych.
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartId> {
    /**
     * Kwerenda znajdująca produkty w koszyku po identyfikatorze użytkownika.
     *
     * @param userId Identyfikator użytkownika
     * @return Lista produktów znajdujących się w koszyku
     */
    List<ShoppingCart> findById_UserId(Long userId);

    /**
     * Kwerenda usuwająca produkty z koszyka użytkownika.
     *
     * @param userId Identyfikator użytkownika
     */
    @Transactional
    @Modifying
    @Query("delete from ShoppingCart s where s.id.userId = :userId")
    void deleteById_UserId(@Param("userId") Long userId);

}
