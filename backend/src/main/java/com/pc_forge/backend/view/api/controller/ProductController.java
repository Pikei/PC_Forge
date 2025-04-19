package com.pc_forge.backend.view.api.controller;

import com.pc_forge.backend.controller.service.ProductService;
import com.pc_forge.backend.model.database.product.HardDiskDrive;
import com.pc_forge.backend.model.database.product.Processor;
import com.pc_forge.backend.model.database.product.Product;
import com.pc_forge.backend.view.api.ProductTypeCodes;
import com.pc_forge.backend.view.api.model.product.HddResponse;
import com.pc_forge.backend.view.api.model.product.ProcessorResponse;
import com.pc_forge.backend.view.api.model.product.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/kategoria-{category}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsInCategory(category);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(getProductResponse(product));
        }
        if (productResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productResponses);
    }

    private ProductResponse getProductResponse(Product product) {
        if (product == null) {
            return null;
        }
        if (product.getCategory().equals(ProductTypeCodes.CPU.getCode())) {
            return getCpuResponse((Processor) product);
        } else if (product.getCategory().equals(ProductTypeCodes.HDD.getCode())) {
            return getHddResponse((HardDiskDrive) product);
        }
        return null;
    }

    private ProductResponse getHddResponse(HardDiskDrive product) {
        HddResponse hdd = new HddResponse();
        hdd.setName(product.getName());
        hdd.setPrice(product.getPrice());
        hdd.setRotationalSpeed(product.getRotationalSpeed());
        return hdd;
    }

    private ProductResponse getCpuResponse(Processor product) {
        ProcessorResponse cpu = new ProcessorResponse();
        cpu.setSocket(product.getSocket().getSocket());
        cpu.setName(product.getName());
        cpu.setPrice(product.getPrice());
        cpu.setCores(product.getCores());
        cpu.setThreads(product.getThreads());
        cpu.setUnlocked(product.getUnlocked());
        cpu.setFrequency(product.getFrequency());
        cpu.setIntegratedGraphicsUnit(product.getIntegratedGraphicsUnit());
        cpu.setCoolerIncluded(product.getCoolerIncluded());
        cpu.setUnlocked(product.getUnlocked());
        return cpu;
    }
}

