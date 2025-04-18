package com.pc_forge.backend.model.database.product.compatibility.repository;

import com.pc_forge.backend.model.database.product.compatibility.CaseMbCompatibility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseMbCompatibilityRepository extends JpaRepository<CaseMbCompatibility, Integer> {
}