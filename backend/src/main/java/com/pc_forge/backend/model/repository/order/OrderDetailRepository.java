package com.pc_forge.backend.model.repository.order;

import com.pc_forge.backend.model.entity.order.OrderDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repozytorium/DAO dla {@link OrderDetail}. Zawiera niezbędne kwerendy pobierające odpowiednie dane z bazy danych.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    /**
     * Kwerenda znajdująca wszystkie zamówione produkty na podstawie identyfikatora zamówienia.
     *
     * @param id Identyfikator zamówienia
     * @return Lista zamówionych produktów
     */
    List<OrderDetail> findByOrder_Id(Long id);

    /**
     * Usuwa szczegóły zamówienia na podstawie jego identyfikatora.
     *
     * @param orderId Identyfikator zamówienia
     */
    @Query("delete from OrderDetail o where o.order.id = ?1")
    @Modifying
    @Transactional
    void deleteByOrder(Long orderId);
}
