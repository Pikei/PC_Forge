package com.pc_forge.backend.model.product.compatibilities.repository;

import com.pc_forge.backend.model.product.compatibilities.CpuSocket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuSocketRepository extends JpaRepository<CpuSocket, Integer> {
}