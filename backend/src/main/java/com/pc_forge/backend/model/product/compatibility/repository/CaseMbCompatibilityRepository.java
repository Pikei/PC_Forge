package com.pc_forge.backend.model.product.compatibility.repository;

import com.pc_forge.backend.model.product.compatibility.CaseMbCompatibility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseMbCompatibilityRepository extends JpaRepository<CaseMbCompatibility, Integer> {
}