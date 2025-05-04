package com.pc_forge.backend.model.repository.order;

import com.pc_forge.backend.model.entity.order.Order;
import com.pc_forge.backend.model.entity.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where o.user.id = ?1")
    List<Order> findByUser_Id(Integer id);

    @Query("delete from Order o where o.user.id = ?1")
    @Modifying
    @Transactional
    void deleteByUser(Integer userId);

}