package com.pc_forge.backend.model.repository.order;

import com.pc_forge.backend.model.entity.order.Order;
import com.pc_forge.backend.model.entity.order.OrderDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrder_Id(Long id);

    @Query("delete from OrderDetail o where o.order.id = ?1")
    @Modifying
    @Transactional
    void deleteByOrder(Long orderId);
}