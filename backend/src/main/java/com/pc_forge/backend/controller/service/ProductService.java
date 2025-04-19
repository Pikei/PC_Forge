package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.model.database.product.Product;
import com.pc_forge.backend.model.database.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsInCategory(String category) {
        return productRepository.findByCategory(category);
    }
}
