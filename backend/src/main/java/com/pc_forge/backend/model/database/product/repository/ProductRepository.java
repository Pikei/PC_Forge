package com.pc_forge.backend.model.database.product.repository;

import com.pc_forge.backend.model.database.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}