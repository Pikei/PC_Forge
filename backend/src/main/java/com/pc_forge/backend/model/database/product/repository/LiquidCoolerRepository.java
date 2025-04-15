package com.pc_forge.backend.model.database.product.repository;

import com.pc_forge.backend.model.database.product.LiquidCooler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiquidCoolerRepository extends JpaRepository<LiquidCooler, Long> {
}