package com.pc_forge.backend.model.database.product.compatibilities.repository;

import com.pc_forge.backend.model.database.product.compatibilities.CaseMbCompatibility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseMbCompatibilityRepository extends JpaRepository<CaseMbCompatibility, Integer> {
}