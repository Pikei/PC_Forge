package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.database.product.Product;
import com.pc_forge.backend.model.database.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public abstract class ProductService {
    private final ProductRepository productRepository;
    protected List<List<Product>> filteredProducts;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsInCategory(String category) {
        return productRepository.findByCategory(category);
    }

    protected List<Product> getProductsInCategoryByProducers(String category, String producer) {
        return productRepository.findByCategoryAndProducer(category, producer);
    }

    public abstract List<Product> getFilteredProducts(ProductFilter filter);

    private List<Product> getProductsByPrice(Double minPrice, Double maxPrice, String category) {
        if (minPrice != null && maxPrice != null) {
            return productRepository.findByPriceBetweenAndCategory(minPrice, maxPrice, category);
        }
        if (minPrice != null) {
            return productRepository.findByPriceGreaterThanEqualAndCategory(minPrice, category);
        }
        if (maxPrice != null) {
            return productRepository.findByPriceLessThanEqualAndCategory(maxPrice, category);
        }
        return List.of();
    }

    protected void filterByPrice(Double minPrice, Double maxPrice, String category) {
        List<Product> productsByPrice = getProductsByPrice(minPrice, maxPrice, category);
        if (productsByPrice != null) {
            filteredProducts.add(productsByPrice);
        }
    }

    protected List<Product> applyFilters() {
//        List<Product> filtered = new ArrayList<>(filteredProducts.getFirst());
//        for (List<Product> products : filteredProducts) {
//            filtered.retainAll(products);
//        }
//        return filtered;
        return filteredProducts.stream()
                .map(HashSet::new)
                .reduce((set1, set2) -> {
                    set1.retainAll(set2);
                    return set1;
                })
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }

}
