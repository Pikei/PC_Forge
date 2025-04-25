package com.pc_forge.backend.model.product.repository;

import com.pc_forge.backend.model.product.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Long> {
}