package com.pc_forge.backend.view.api.controller;

import com.pc_forge.backend.controller.service.product.*;
import com.pc_forge.backend.view.api.request.response.filter.GpuFilterResponse;
import com.pc_forge.backend.view.api.request.response.filter.MemoryFilterResponse;
import com.pc_forge.backend.view.api.request.response.filter.MotherboardFilterResponse;
import com.pc_forge.backend.view.api.request.response.filter.ProcessorFilterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilterController {
    private final CommonProductService productService;
    private final ProcessorService processorService;
    private final MotherboardService motherboardService;
    private final MemoryService memoryService;
    private final GraphicsCardService graphicsCardService;

    public FilterController(CommonProductService productService, ProcessorService processorService, MotherboardService motherboardService, MemoryService memoryService, GraphicsCardService graphicsCardService) {
        this.productService = productService;
        this.processorService = processorService;
        this.motherboardService = motherboardService;
        this.memoryService = memoryService;
        this.graphicsCardService = graphicsCardService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Object[]>> getProductsByCategoryFilter() {
        return ResponseEntity.ok(productService.getSearchFilter());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Object[]>> getProductsByCategoryFilter(@PathVariable String name) {
        return ResponseEntity.ok(productService.getSearchByNameFilter(name));
    }

    @GetMapping("/processor")
    public ResponseEntity<ProcessorFilterResponse> getProcessorFilters() {
        return ResponseEntity.ok(processorService.getAvailableFilters());
    }

    @GetMapping("/motherboard")
    public ResponseEntity<MotherboardFilterResponse> getMotherboardFilters() {
        return ResponseEntity.ok(motherboardService.getAvailableFilters());
    }

    @GetMapping("/memory")
    public ResponseEntity<MemoryFilterResponse> getMemoryFilters() {
        return ResponseEntity.ok(memoryService.getAvailableFilters());
    }

    @GetMapping("/gpu")
    public ResponseEntity<GpuFilterResponse> getGraphicsCardFilters() {
        return ResponseEntity.ok(graphicsCardService.getAvailableFilters());
    }
}
