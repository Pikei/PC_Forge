package com.pc_forge.backend.model.product.compatibility.repository;

import com.pc_forge.backend.model.product.compatibility.CpuSocket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuSocketRepository extends JpaRepository<CpuSocket, Integer> {
}