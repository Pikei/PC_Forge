package com.pc_forge.backend.model.database.product.repository;

import com.pc_forge.backend.model.database.product.GraphicsCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphicsCardRepository extends JpaRepository<GraphicsCard, Long> {
}