package com.pc_forge.backend.model.database.user.repository;

import com.pc_forge.backend.model.database.user.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}