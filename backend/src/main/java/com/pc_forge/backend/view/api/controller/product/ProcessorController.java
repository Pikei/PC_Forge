package com.pc_forge.backend.view.api.controller.product;

import com.pc_forge.backend.controller.filter.ProcessorFilter;
import com.pc_forge.backend.controller.service.product.ProcessorService;
import com.pc_forge.backend.model.database.product.Processor;
import com.pc_forge.backend.view.api.request.response.filter.ProcessorFilterResponse;
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
public class ProcessorController implements FilterResponse {
    private final ProcessorService processorService;

    public ProcessorController(ProcessorService processorService) {
        this.processorService = processorService;
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

    @Override
    @GetMapping("/filter/processors")
    public ResponseEntity<ProcessorFilterResponse> getProductFilters() {
        return ResponseEntity.ok(processorService.getAvailableFilters());
    }
}
