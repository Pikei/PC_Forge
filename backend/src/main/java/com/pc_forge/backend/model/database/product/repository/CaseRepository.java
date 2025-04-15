package com.pc_forge.backend.model.database.product.repository;

import com.pc_forge.backend.model.database.product.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Long> {
}