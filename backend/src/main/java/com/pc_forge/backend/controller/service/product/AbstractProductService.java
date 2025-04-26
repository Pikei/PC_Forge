package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.product.Product;
import com.pc_forge.backend.model.product.repository.ProductRepository;
import com.pc_forge.backend.view.response.filter.ProductFilterResponse;

import java.util.*;
import java.util.function.Function;

public abstract class AbstractProductService<T extends Product> {
    protected final ProductRepository<T> productRepository;
    protected List<List<T>> filteredProducts = new ArrayList<>();

    public AbstractProductService(ProductRepository<T> productRepository) {
        this.productRepository = productRepository;
    }

    protected <V> void filterByListParam(List<V> selectedValues, Function<V, List<T>> function) {
        if (selectedValues != null && !selectedValues.isEmpty()) {
            List<T> filtered = new ArrayList<>();
            for (V val : selectedValues) {
                filtered.addAll(function.apply(val));
            }
            filteredProducts.add(filtered);
        }
    }

    protected void filterByBooleanParam(Boolean param, Function<Boolean, List<T>> function) {
        if (param != null) {
            filteredProducts.add(function.apply(param));
        }
    }

    public List<T> getProductsInCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public abstract List<T> getFilteredProducts(ProductFilter filter);

    protected void filterByProducers(String category, List<String> producers) {
        if (producers != null && !producers.isEmpty()) {
            List<T> filteredByProducers = new ArrayList<>();
            for (String producer : producers) {
                filteredByProducers.addAll(productRepository.findByProducerAndCategory(producer, category));
            }
            filteredProducts.add(filteredByProducers);
        }
    }

    protected void filterByPrice(Double minPrice, Double maxPrice, String category) {
        if (minPrice == null && maxPrice == null) {
            return;
        }
        if (minPrice == null) {
            minPrice = productRepository.getMinPriceFilter(category);
        }
        if (maxPrice == null) {
            maxPrice = productRepository.getMaxPriceFilter(category);
        }
        filteredProducts.add(productRepository.findByPriceBetweenAndCategory(minPrice, maxPrice, category));
    }

    protected List<T> applyFilters() {
        return filteredProducts.stream()
                .map(HashSet::new)
                .reduce((set1, set2) -> {
                    set1.retainAll(set2);
                    return set1;
                })
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }

    public abstract <V extends ProductFilterResponse> V getAvailableFilters();
}
