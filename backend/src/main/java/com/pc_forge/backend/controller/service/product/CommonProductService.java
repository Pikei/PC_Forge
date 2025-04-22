package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.model.database.product.Product;
import com.pc_forge.backend.model.database.product.repository.CommonProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonProductService {
    private final CommonProductRepository productRepository;

    public CommonProductService(CommonProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByName(String name, String category) {
        if (category == null || category.isEmpty()) {
            return productRepository.findByNameContainsIgnoreCase(name);
        }
        return productRepository.findByNameContainsIgnoreCaseAndCategory(name, category);
    }

    public List<Object[]> getSearchFilter() {
        return productRepository.getCategoryFilter();
    }

    public List<Object[]> getSearchByNameFilter(String name) {
        return productRepository.getCategorySearchFilter(name);
    }
}
