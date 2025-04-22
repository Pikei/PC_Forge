package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.MemoryFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.database.product.Memory;
import com.pc_forge.backend.model.database.product.repository.MemoryRepository;
import com.pc_forge.backend.model.database.product.repository.ProductRepository;
import com.pc_forge.backend.view.api.request.response.filter.MemoryFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class MemoryService extends AbstractProductService<Memory> {
    private final MemoryRepository memoryRepository;

    public MemoryService(ProductRepository<Memory> productRepository, MemoryRepository memoryRepository) {
        super(productRepository);
        this.memoryRepository = memoryRepository;
    }

    @Override
    public List<Memory> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.RAM.getCode()));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.RAM.getCode());
        filterByProducers(ProductCategoryCode.RAM.getCode(), filter.getSelectedProducers());
        filterByListParam(((MemoryFilter) filter).getSelectedTypes(), memoryRepository::findByMemoryType);
        filterByListParam(((MemoryFilter) filter).getSelectedCapacities(), memoryRepository::findByTotalCapacity);
        filterByListParam(((MemoryFilter) filter).getSelectedFrequencies(), memoryRepository::findByFrequency);
        filterByListParam(((MemoryFilter) filter).getSelectedModules(), memoryRepository::findByNumberOfModules);
        filterByListParam(((MemoryFilter) filter).getSelectedLatencies(), memoryRepository::findByLatency);
        filterByBooleanParam(((MemoryFilter) filter).getLightning(), memoryRepository::findByLighting);
        return applyFilters();
    }

    @Override
    @SuppressWarnings("unchecked")
    public MemoryFilterResponse getAvailableFilters() {
        MemoryFilterResponse response = new MemoryFilterResponse();
        response.setPriceMinimum(productRepository.getMinPriceFilter(ProductCategoryCode.RAM.getCode()));
        response.setPriceMaximum(productRepository.getMaxPriceFilter(ProductCategoryCode.RAM.getCode()));
        response.setProducers(productRepository.getProducerFilter(ProductCategoryCode.RAM.getCode()));
        response.setMemoryType(memoryRepository.getMemoryTypeFilter());
        response.setTotalCapacity(memoryRepository.getTotalCapacityFilter());
        response.setFrequency(memoryRepository.getFrequencyFilter());
        response.setNumberOfModules(memoryRepository.getNumberOfModulesFilter());
        response.setLatency(memoryRepository.getLatencyFilter());
        response.setLightning(memoryRepository.getLightningFilter());
        return response;
    }
}
