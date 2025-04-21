package com.pc_forge.backend.view.api.request.response.filter;

import com.pc_forge.backend.model.database.product.Product;
import com.pc_forge.backend.model.database.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class CommonProductService {
    private ProductRepository<Product> productRepository;

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameContainsIgnoreCase(name);
    }
}
