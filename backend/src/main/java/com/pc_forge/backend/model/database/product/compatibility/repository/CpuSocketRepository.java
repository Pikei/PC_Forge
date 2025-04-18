package com.pc_forge.backend.model.database.product.compatibility.repository;

import com.pc_forge.backend.model.database.product.compatibility.CpuSocket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuSocketRepository extends JpaRepository<CpuSocket, Integer> {
}