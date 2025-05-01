package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.api.constants.UrlPath;
import com.pc_forge.backend.controller.service.product.*;
import com.pc_forge.backend.view.response.filter.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UrlPath.FILTER)
public class FilterController {
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

    public FilterController(CommonProductService productService,
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

    @GetMapping(UrlPath.SEARCH)
    public ResponseEntity<List<Object[]>> getProductsByCategoryFilter() {
        return ResponseEntity.ok(productService.getSearchFilter());
    }

    @GetMapping(UrlPath.SEARCH + "/{name}")
    public ResponseEntity<List<Object[]>> getProductsByCategoryFilter(@PathVariable String name) {
        return ResponseEntity.ok(productService.getSearchByNameFilter(name));
    }

    @GetMapping(UrlPath.PROCESSOR)
    public ResponseEntity<ProcessorFilterResponse> getProcessorFilters() {
        return ResponseEntity.ok(processorService.getAvailableFilters());
    }

    @GetMapping(UrlPath.MOTHERBOARD)
    public ResponseEntity<MotherboardFilterResponse> getMotherboardFilters() {
        return ResponseEntity.ok(motherboardService.getAvailableFilters());
    }

    @GetMapping(UrlPath.MEMORY)
    public ResponseEntity<MemoryFilterResponse> getMemoryFilters() {
        return ResponseEntity.ok(memoryService.getAvailableFilters());
    }

    @GetMapping(UrlPath.GRAPHICS_CARD)
    public ResponseEntity<GpuFilterResponse> getGraphicsCardFilters() {
        return ResponseEntity.ok(graphicsCardService.getAvailableFilters());
    }

    @GetMapping(UrlPath.POWER_SUPPLY)
    public ResponseEntity<PsFilterResponse> getPowerSupplyFilters() {
        return ResponseEntity.ok(powerSupplyService.getAvailableFilters());
    }

    @GetMapping(UrlPath.CASE)
    public ResponseEntity<CaseFilterResponse> getCaseFilters() {
        return ResponseEntity.ok(caseService.getAvailableFilters());
    }

    @GetMapping(UrlPath.SSD)
    public ResponseEntity<SsdFilterResponse> getSSDFilters() {
        return ResponseEntity.ok(ssdService.getAvailableFilters());
    }

    @GetMapping(UrlPath.HDD)
    public ResponseEntity<HddFilterResponse> getHDDFilters() {
        return ResponseEntity.ok(hddService.getAvailableFilters());
    }

    @GetMapping(UrlPath.AIR_COOLER)
    public ResponseEntity<AirCoolerFilterResponse> getAirCoolerFilters() {
        return ResponseEntity.ok(airCoolerService.getAvailableFilters());
    }

    @GetMapping(UrlPath.LIQUID_COOLER)
    public ResponseEntity<LiquidCoolerFilterResponse> getLiquidCoolerFilters() {
        return ResponseEntity.ok(liquidCoolerService.getAvailableFilters());
    }
}
