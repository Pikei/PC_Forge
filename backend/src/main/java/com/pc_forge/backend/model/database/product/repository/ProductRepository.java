package com.pc_forge.backend.model.database.product.repository;

import com.pc_forge.backend.model.database.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainsIgnoreCase(String name);

    List<Product> findByCategory(String category);

    List<Product> findByCategoryAndProducer(String category, String producer);

    List<Product> findByPriceBetweenAndCategory(Double priceStart, Double priceEnd, String category);

    List<Product> findByPriceGreaterThanEqualAndCategory(Double price, String category);

    List<Product> findByPriceLessThanEqualAndCategory(Double price, String category);


}