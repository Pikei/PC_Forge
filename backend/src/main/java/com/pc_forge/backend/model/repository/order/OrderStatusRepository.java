package com.pc_forge.backend.model.repository.order;

import com.pc_forge.backend.model.entity.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repozytorium/DAO dla {@link OrderStatus}. Zawiera niezbędne kwerendy pobierające odpowiednie dane z bazy danych.
 */
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
    /**
     * Kwerenda zwracająca listę statusów zamówień, które można anulować.
     *
     * @return Lista statusów zamówień, które można anulować.
     */
    @Query("select o from OrderStatus o where o.id between 2 and 5")
    List<OrderStatus> getCancellableStatuses();

}
