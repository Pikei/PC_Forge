package com.pc_forge.backend.model.product.repository;

import com.pc_forge.backend.model.product.Cooler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoolerRepository extends JpaRepository<Cooler, Long> {
}