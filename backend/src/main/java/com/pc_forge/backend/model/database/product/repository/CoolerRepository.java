package com.pc_forge.backend.model.database.product.repository;

import com.pc_forge.backend.model.database.product.Cooler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoolerRepository extends JpaRepository<Cooler, Long> {
}