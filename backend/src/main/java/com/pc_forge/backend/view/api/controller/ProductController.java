package com.pc_forge.backend.view.api.controller;

import com.pc_forge.backend.controller.filter.GpuFilter;
import com.pc_forge.backend.controller.filter.MemoryFilter;
import com.pc_forge.backend.controller.filter.MotherboardFilter;
import com.pc_forge.backend.controller.filter.ProcessorFilter;
import com.pc_forge.backend.controller.service.product.*;
import com.pc_forge.backend.model.database.product.*;
import com.pc_forge.backend.view.api.request.response.product.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public final class ProductController {
    private final CommonProductService productService;
    private final ProcessorService processorService;
    private final MotherboardService motherboardService;
    private final MemoryService memoryService;
    private final GraphicsCardService graphicsCardService;

    public ProductController(CommonProductService productService, ProcessorService processorService, MotherboardService motherboardService, MemoryService memoryService, GraphicsCardService graphicsCardService) {
        this.productService = productService;
        this.processorService = processorService;
        this.motherboardService = motherboardService;
        this.memoryService = memoryService;
        this.graphicsCardService = graphicsCardService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        try {
            Long productId = Long.parseLong(id);
            Product product = productService.getProductById(productId);
            if (product == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(product);
        } catch (NumberFormatException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductResponse>> getProductsByName(
            @PathVariable String name,
            @RequestParam(value = "category", required = false) String category
    ) {
        List<Product> products = productService.getProductsByName(name, category);
        return ResponseBuilder.generateProductList(products);
    }

    @GetMapping("/category/processors")
    public ResponseEntity<List<ProductResponse>> getFilteredProducts(
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "producer", required = false) List<String> producers,
            @RequestParam(value = "socket", required = false) List<String> sockets,
            @RequestParam(value = "line", required = false) List<String> lines,
            @RequestParam(value = "cores", required = false) List<Integer> cores,
            @RequestParam(value = "freq", required = false) List<Double> freq,
            @RequestParam(value = "igu", required = false) List<String> igu,
            @RequestParam(value = "pack", required = false) List<String> pack,
            @RequestParam(value = "unlocked", required = false) Boolean unlocked,
            @RequestParam(value = "cooler", required = false) Boolean cooler
    ) {
        ProcessorFilter filter = new ProcessorFilter();
        filter.setPriceMinimum(minPrice);
        filter.setPriceMaximum(maxPrice);
        filter.setSelectedProducers(producers);
        filter.setSelectedSockets(sockets);
        filter.setSelectedLines(lines);
        filter.setSelectedCores(cores);
        filter.setSelectedFrequencies(freq);
        filter.setSelectedGraphicsUnits(igu);
        filter.setSelectedPackagingTypes(pack);
        filter.setUnlocked(unlocked);
        filter.setCoolerIncluded(cooler);
        List<Processor> products = processorService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
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

    @GetMapping("/category/memory")
    public ResponseEntity<List<ProductResponse>> getFilteredMemories(
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

    @GetMapping("/category/graphics-card")
    public ResponseEntity<List<ProductResponse>> getFilteredGraphicCards(
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "producer", required = false) List<String> producers,
            @RequestParam(value = "chipsetProducer", required = false) List<String> chipsetProducers,
            @RequestParam(value = "chipset", required = false) List<String> chipsets,
            @RequestParam(value = "ram", required = false) List<Integer> ramCapacities,
            @RequestParam(value = "ramType", required = false) List<String> ramTypes,
            @RequestParam(value = "dlss", required = false) List<String> dlss,
            @RequestParam(value = "connector", required = false) List<String> connectors,
            @RequestParam(value = "len", required = false) List<Integer> lengths,
            @RequestParam(value = "res", required = false) List<String> resolutions,
            @RequestParam(value = "psPower", required = false) List<Integer> psPowers,
            @RequestParam(value = "cooling", required = false) List<String> coolingTypes,
            @RequestParam(value = "fans", required = false) List<Integer> numOfFans,
            @RequestParam(value = "light", required = false) Boolean lightning
    ) {
        GpuFilter filter = new GpuFilter();
        filter.setPriceMinimum(minPrice);
        filter.setPriceMaximum(maxPrice);
        filter.setSelectedProducers(producers);
        filter.setSelectedChipsetProducers(chipsetProducers);
        filter.setSelectedChipsets(chipsets);
        filter.setSelectedRamCapacities(ramCapacities);
        filter.setSelectedRamTypes(ramTypes);
        filter.setSelectedDlls(dlss);
        filter.setSelectedConnectors(connectors);
        filter.setSelectedLengths(lengths);
        filter.setSelectedResolutions(resolutions);
        filter.setSelectedRecommendedPs(psPowers);
        filter.setSelectedCoolingTypes(coolingTypes);
        filter.setSelectedFans(numOfFans);
        filter.setLightning(lightning);
        List<GraphicsCard> products = graphicsCardService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }
}
