package com.pc_forge.backend.model.repository.order;

import com.pc_forge.backend.model.entity.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
}