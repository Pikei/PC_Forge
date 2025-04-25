package com.pc_forge.backend.model.user.repository;

import com.pc_forge.backend.model.user.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}