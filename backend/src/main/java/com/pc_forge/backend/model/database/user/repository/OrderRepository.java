package com.pc_forge.backend.model.database.user.repository;

import com.pc_forge.backend.model.database.user.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}