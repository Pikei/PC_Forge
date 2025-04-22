package com.pc_forge.backend.view.api.controller.product;

import com.pc_forge.backend.controller.filter.MemoryFilter;
import com.pc_forge.backend.controller.service.product.MemoryService;
import com.pc_forge.backend.model.database.product.Memory;
import com.pc_forge.backend.view.api.request.response.filter.MemoryFilterResponse;
import com.pc_forge.backend.view.api.request.response.product.ProductResponse;
import com.pc_forge.backend.view.api.request.response.product.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class MemoryController implements FilterResponse {
    private final MemoryService memoryService;

    public MemoryController(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    @GetMapping("/category/ram")
    public ResponseEntity<List<ProductResponse>> getFilteredProducts(
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "producer", required = false) List<String> producers,
            @RequestParam(value = "ramType", required = false) List<String> type,
            @RequestParam(value = "capacity", required = false) List<Integer> capacity,
            @RequestParam(value = "freq", required = false) List<Integer> freq,
            @RequestParam(value = "modules", required = false) List<Integer> modules,
            @RequestParam(value = "latency", required = false) List<String> latency,
            @RequestParam(value = "light", required = false) Boolean light
    ) {
        MemoryFilter filter = new MemoryFilter();
        filter.setPriceMinimum(minPrice);
        filter.setPriceMaximum(maxPrice);
        filter.setSelectedProducers(producers);
        filter.setSelectedTypes(type);
        filter.setSelectedCapacities(capacity);
        filter.setSelectedFrequencies(freq);
        filter.setSelectedModules(modules);
        filter.setSelectedLatencies(latency);
        filter.setLightning(light);
        List<Memory> products = memoryService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    @Override
    @GetMapping("/filter/ram")
    public ResponseEntity<MemoryFilterResponse> getProductFilters() {
        return ResponseEntity.ok(memoryService.getAvailableFilters());
    }
}
