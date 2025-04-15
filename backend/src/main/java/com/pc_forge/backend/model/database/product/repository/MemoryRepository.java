package com.pc_forge.backend.model.database.product.repository;

import com.pc_forge.backend.model.database.product.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
}