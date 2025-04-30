package com.pc_forge.backend.model.user.repository;

import com.pc_forge.backend.model.user.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
}