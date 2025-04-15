package com.pc_forge.backend.model.product.repository;

import com.pc_forge.backend.model.product.LiquidCooler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiquidCoolerRepository extends JpaRepository<LiquidCooler, Long> {
}