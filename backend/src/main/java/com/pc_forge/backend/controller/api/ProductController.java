package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import com.pc_forge.backend.controller.api.constants.UrlPath;
import com.pc_forge.backend.controller.filter.*;
import com.pc_forge.backend.controller.service.product.*;
import com.pc_forge.backend.model.entity.product.Product;
import com.pc_forge.backend.model.entity.product.cooler.AirCooler;
import com.pc_forge.backend.model.entity.product.cooler.LiquidCooler;
import com.pc_forge.backend.model.entity.product.cpu.Processor;
import com.pc_forge.backend.model.entity.product.drive.HardDiskDrive;
import com.pc_forge.backend.model.entity.product.drive.SolidStateDrive;
import com.pc_forge.backend.model.entity.product.gpu.GraphicsCard;
import com.pc_forge.backend.model.entity.product.mb.Motherboard;
import com.pc_forge.backend.model.entity.product.pc_case.Case;
import com.pc_forge.backend.model.entity.product.ps.PowerSupply;
import com.pc_forge.backend.model.entity.product.ram.Memory;
import com.pc_forge.backend.view.response.product.ProductResponse;
import com.pc_forge.backend.view.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final CommonProductService productService;
    private final ProcessorService processorService;
    private final MotherboardService motherboardService;
    private final MemoryService memoryService;
    private final GraphicsCardService graphicsCardService;
    private final PowerSupplyService powerSupplyService;
    private final CaseService caseService;
    private final SsdService ssdService;
    private final HddService hddService;
    private final AirCoolerService airCoolerService;
    private final LiquidCoolerService liquidCoolerService;

    public ProductController(CommonProductService productService,
                             ProcessorService processorService,
                             MotherboardService motherboardService,
                             MemoryService memoryService,
                             GraphicsCardService graphicsCardService,
                             PowerSupplyService powerSupplyService,
                             CaseService caseService,
                             SsdService ssdService,
                             HddService hddService,
                             AirCoolerService airCoolerService,
                             LiquidCoolerService liquidCoolerService) {
        this.productService = productService;
        this.processorService = processorService;
        this.motherboardService = motherboardService;
        this.memoryService = memoryService;
        this.graphicsCardService = graphicsCardService;
        this.powerSupplyService = powerSupplyService;
        this.caseService = caseService;
        this.ssdService = ssdService;
        this.hddService = hddService;
        this.airCoolerService = airCoolerService;
        this.liquidCoolerService = liquidCoolerService;
    }

    @GetMapping(UrlPath.PRODUCT + "/{id}")
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

    @GetMapping(UrlPath.PRODUCT + UrlPath.SEARCH + "/{name}")
    public ResponseEntity<List<ProductResponse>> getProductsByName(
            @PathVariable String name,
            @RequestParam(value = RequestParams.PRODUCT_CATEGORY, required = false) String category
    ) {
        List<Product> products = productService.getProductsByName(name, category);
        return ResponseBuilder.generateProductList(products);
    }

    @GetMapping(UrlPath.CATEGORY + UrlPath.PROCESSOR)
    public ResponseEntity<List<ProductResponse>> getFilteredProcessors(HttpServletRequest request
    ) {
        Map<String, String[]> params = request.getParameterMap();
        ProcessorFilter filter = new ProcessorFilter(params);
        List<Processor> products = processorService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    @GetMapping(UrlPath.CATEGORY + UrlPath.MOTHERBOARD)
    public ResponseEntity<List<ProductResponse>> getFilteredMotherboards(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        MotherboardFilter filter = new MotherboardFilter(params);
        List<Motherboard> products = motherboardService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    @GetMapping(UrlPath.CATEGORY + UrlPath.MEMORY)
    public ResponseEntity<List<ProductResponse>> getFilteredMemories(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        MemoryFilter filter = new MemoryFilter(params);
        List<Memory> products = memoryService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    @GetMapping(UrlPath.CATEGORY + UrlPath.GRAPHICS_CARD)
    public ResponseEntity<List<ProductResponse>> getFilteredGraphicCards(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        GpuFilter filter = new GpuFilter(params);
        List<GraphicsCard> products = graphicsCardService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    @GetMapping(UrlPath.CATEGORY + UrlPath.POWER_SUPPLY)
    public ResponseEntity<List<ProductResponse>> getFilteredPowerSupplies(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        PowerSupplyFilter filter = new PowerSupplyFilter(params);
        List<PowerSupply> products = powerSupplyService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    @GetMapping(UrlPath.CATEGORY + UrlPath.CASE)
    public ResponseEntity<List<ProductResponse>> getFilteredCases(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        CaseFilter filter = new CaseFilter(params);
        List<Case> products = caseService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    @GetMapping(UrlPath.CATEGORY + UrlPath.SSD)
    public ResponseEntity<List<ProductResponse>> getFilteredSSDs(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        SsdFilter filter = new SsdFilter(params);
        List<SolidStateDrive> products = ssdService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    @GetMapping(UrlPath.CATEGORY + UrlPath.HDD)
    public ResponseEntity<List<ProductResponse>> getFilteredHDDs(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        HddFilter filter = new HddFilter(params);
        List<HardDiskDrive> products = hddService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    @GetMapping(UrlPath.CATEGORY + UrlPath.AIR_COOLER)
    public ResponseEntity<List<ProductResponse>> getFilteredAirCoolers(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        AirCoolerFilter filter = new AirCoolerFilter(params);
        List<AirCooler> products = airCoolerService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    @GetMapping(UrlPath.CATEGORY + UrlPath.LIQUID_COOLER)
    public ResponseEntity<List<ProductResponse>> getFilteredLiquidCoolers(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        LiquidCoolerFilter filter = new LiquidCoolerFilter(params);
        List<LiquidCooler> products = liquidCoolerService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }
}
