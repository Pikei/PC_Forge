package com.pc_forge.backend.model.repository.order;

import com.pc_forge.backend.model.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repozytorium/DAO dla {@link Order}. Zawiera niezbędne kwerendy pobierające odpowiednie dane z bazy danych.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Kwerenda zwracająca listę zamówień użytkownika, na podstawie jego identyfikatora.
     *
     * @param id Identyfikator użytkownika
     * @return Lista zamówień użytkownika
     */
    @Query("select o from Order o where o.user.id = ?1 order by o.orderDate desc")
    List<Order> findByUser_Id(Long id);

    /**
     * Kwerenda znajdująca zamówienie na podstawie jego identyfikatora sesji płatniczej.
     *
     * @param sessionId Identyfikator sesji płatniczej Stripe
     * @return Obiekt zamówienia obudowany w {@link Optional}
     */
    Optional<Order> findOrderBySessionId(String sessionId);
}