package com.pc_forge.backend.model.user.repository;

import com.pc_forge.backend.model.user.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where o.user.id = ?1")
    List<Order> findByUser_Id(Integer id);


}