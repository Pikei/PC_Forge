package com.pc_forge.backend.view.api.controller.product;

import com.pc_forge.backend.controller.filter.MotherboardFilter;
import com.pc_forge.backend.controller.service.product.MotherboardService;
import com.pc_forge.backend.model.database.product.Motherboard;
import com.pc_forge.backend.view.api.request.response.filter.MotherboardFilterResponse;
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
public final class MotherboardController implements FilterResponse {
    private final MotherboardService motherboardService;

    public MotherboardController(MotherboardService motherboardService) {
        this.motherboardService = motherboardService;
    }

    @GetMapping("/category/motherboards")
    public ResponseEntity<List<ProductResponse>> getFilteredProducts(
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "producer", required = false) List<String> producers,
            @RequestParam(value = "socket", required = false) List<String> sockets,
            @RequestParam(value = "standard", required = false) List<String> standards,
            @RequestParam(value = "chipset", required = false) List<String> chipsets,
            @RequestParam(value = "ramType", required = false) List<String> memoryStandards,
            @RequestParam(value = "ramSlots", required = false) List<Integer> memorySlots,
            @RequestParam(value = "ramMax", required = false) List<Integer> maxMemory,
            @RequestParam(value = "freq", required = false) List<Integer> frequencies,
            @RequestParam(value = "bt", required = false) Boolean bluetooth,
            @RequestParam(value = "wifi", required = false) Boolean wifi,
            @RequestParam(value = "minWidth", required = false) Double minWidth,
            @RequestParam(value = "maxWidth", required = false) Double maxWidth,
            @RequestParam(value = "minDepth", required = false) Double minDepth,
            @RequestParam(value = "maxDepth", required = false) Double maxDepth
    ) {
        MotherboardFilter filter = new MotherboardFilter();
        filter.setPriceMinimum(minPrice);
        filter.setPriceMaximum(maxPrice);
        filter.setSelectedProducers(producers);
        filter.setSelectedSockets(sockets);
        filter.setSelectedStandards(standards);
        filter.setSelectedChipsets(chipsets);
        filter.setSelectedMemoryStandards(memoryStandards);
        filter.setSelectedMemorySlots(memorySlots);
        filter.setSelectedMaxMemoryCapacity(maxMemory);
        filter.setSelectedFrequencies(frequencies);
        filter.setBluetooth(bluetooth);
        filter.setWifi(wifi);
        filter.setMinWidth(minWidth);
        filter.setMaxWidth(maxWidth);
        filter.setMinDepth(minDepth);
        filter.setMaxDepth(maxDepth);
        List<Motherboard> products = motherboardService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    @Override
    @GetMapping("/filter/motherboards")
    public ResponseEntity<MotherboardFilterResponse> getProductFilters() {
        return ResponseEntity.ok(motherboardService.getAvailableFilters());
    }
}
