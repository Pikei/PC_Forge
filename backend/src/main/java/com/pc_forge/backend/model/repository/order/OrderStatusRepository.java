package com.pc_forge.backend.model.repository.order;

import com.pc_forge.backend.model.entity.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
    @Query("select o from OrderStatus o where o.id between 2 and 5")
    List<OrderStatus> getCancellableStatuses();

}