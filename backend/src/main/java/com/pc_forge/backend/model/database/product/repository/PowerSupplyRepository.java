package com.pc_forge.backend.model.database.product.repository;

import com.pc_forge.backend.model.database.product.PowerSupply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PowerSupplyRepository extends JpaRepository<PowerSupply, Long> {
}