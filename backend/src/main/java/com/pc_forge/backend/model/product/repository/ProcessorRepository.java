package com.pc_forge.backend.model.product.repository;

import com.pc_forge.backend.model.product.Processor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessorRepository extends JpaRepository<Processor, Long> {
}