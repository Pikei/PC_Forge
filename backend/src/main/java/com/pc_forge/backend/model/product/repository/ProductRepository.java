package com.pc_forge.backend.model.product.repository;

import com.pc_forge.backend.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}