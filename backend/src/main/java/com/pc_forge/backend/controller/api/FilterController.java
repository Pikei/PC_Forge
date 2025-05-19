package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.api.constants.UrlPath;
import com.pc_forge.backend.controller.service.product.*;
import com.pc_forge.backend.view.response.filter.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Klasa kontrolera filtrów produktów. Służy do obsługi żądań pobrania dostępnych filtrów
 */
@RestController
@RequestMapping(UrlPath.FILTER)
@CrossOrigin("http://localhost:4200/")
public class FilterController {
    /**
     * Serwis służący do pobrania filtrów dla wszystkich produktów
     */
    private final CommonProductService productService;

    /**
     * Serwis służący do pobrania filtrów dla procesorów
     */
    private final ProcessorService processorService;

    /**
     * Serwis służący do pobrania filtrów dla płyt głównych
     */
    private final MotherboardService motherboardService;

    /**
     * Serwis służący do pobrania filtrów dla pamięci operacyjnych
     */
    private final MemoryService memoryService;

    /**
     * Serwis służący do pobrania filtrów dla kart graficznych
     */
    private final GraphicsCardService graphicsCardService;

    /**
     * Serwis służący do pobrania filtrów dla zasilaczy komputerowych
     */
    private final PowerSupplyService powerSupplyService;

    /**
     * Serwis służący do pobrania filtrów dla obudów
     */
    private final CaseService caseService;

    /**
     * Serwis służący do pobrania filtrów dla dysków SSD
     */
    private final SsdService ssdService;

    /**
     * Serwis służący do pobrania filtrów dla dysków HDD
     */
    private final HddService hddService;

    /**
     * Serwis służący do pobrania filtrów dla układów chłodzenia powietrzem
     */
    private final AirCoolerService airCoolerService;

    /**
     * Serwis służący do pobrania filtrów dla układów chłodzenia cieczą
     */
    private final LiquidCoolerService liquidCoolerService;

    /**
     * Konstruktor wstrzykujący zależności do odpowiednich serwisów
     *
     * @param productService      serwis produktu
     * @param processorService    serwis procesora
     * @param motherboardService  serwis płyty głównej
     * @param memoryService       serwis pamięci operacyjnej RAM
     * @param graphicsCardService serwis karty graficznej
     * @param powerSupplyService  serwis zasilacza
     * @param caseService         serwis obudowy
     * @param ssdService          serwis dysku SSD
     * @param hddService          serwis dysku HDD
     * @param airCoolerService    serwis układu chłodzenia powietrzem
     * @param liquidCoolerService serwis układu chłodzenia cieczą
     */
    public FilterController(CommonProductService productService, ProcessorService processorService, MotherboardService motherboardService, MemoryService memoryService, GraphicsCardService graphicsCardService, PowerSupplyService powerSupplyService, CaseService caseService, SsdService ssdService, HddService hddService, AirCoolerService airCoolerService, LiquidCoolerService liquidCoolerService) {
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

    /**
     * Kontroler zwracający, w jakich kategoriach można filtrować produkty podczas wyszukiwania
     *
     * @return Odpowiedź zawierająca listę składającą się z dostępnych kategorii i liczbie elementów w niej zawartej
     */
    @GetMapping(UrlPath.SEARCH)
    public ResponseEntity<List<Object[]>> getProductsByCategoryFilter() {
        return ResponseEntity.ok(productService.getSearchFilter());
    }

    /**
     * Kontroler zwracający, w jakich kategoriach można filtrować produkty podczas wyszukiwania.
     * Dynamicznie zmienia wartość w zależności od przekazanej w adresie URL nazwy
     *
     * @return Odpowiedź zawierająca listę składającą się z dostępnych kategorii i liczbie elementów w niej zawartej
     */
    @GetMapping(UrlPath.SEARCH + "/{name}")
    public ResponseEntity<List<Object[]>> getProductsByCategoryFilter(@PathVariable String name) {
        return ResponseEntity.ok(productService.getSearchByNameFilter(name));
    }

    /**
     * @return Odpowiedż zawierająca listę składającą się z dostępnych filtrów dla procesorów
     * oraz liczbie dostępnych sztuk w danym filtrze
     */
    @GetMapping(UrlPath.PROCESSOR)
    public ResponseEntity<ProcessorFilterResponse> getProcessorFilters() {
        return ResponseEntity.ok(processorService.getAvailableFilters());
    }

    /**
     * @return Odpowiedż zawierająca listę składającą się z dostępnych filtrów dla płyt głównych
     * oraz liczbie dostępnych sztuk w danym filtrze
     */
    @GetMapping(UrlPath.MOTHERBOARD)
    public ResponseEntity<MotherboardFilterResponse> getMotherboardFilters() {
        return ResponseEntity.ok(motherboardService.getAvailableFilters());
    }

    /**
     * @return Odpowiedż zawierająca listę składającą się z dostępnych filtrów dla pamięci operacyjnych
     * oraz liczbie dostępnych sztuk w danym filtrze
     */
    @GetMapping(UrlPath.MEMORY)
    public ResponseEntity<MemoryFilterResponse> getMemoryFilters() {
        return ResponseEntity.ok(memoryService.getAvailableFilters());
    }

    /**
     * @return Odpowiedż zawierająca listę składającą się z dostępnych filtrów dla kart graficznych
     * oraz liczbie dostępnych sztuk w danym filtrze
     */
    @GetMapping(UrlPath.GRAPHICS_CARD)
    public ResponseEntity<GpuFilterResponse> getGraphicsCardFilters() {
        return ResponseEntity.ok(graphicsCardService.getAvailableFilters());
    }

    /**
     * @return Odpowiedż zawierająca listę składającą się z dostępnych filtrów dla zasilaczy
     * oraz liczbie dostępnych sztuk w danym filtrze
     */
    @GetMapping(UrlPath.POWER_SUPPLY)
    public ResponseEntity<PsFilterResponse> getPowerSupplyFilters() {
        return ResponseEntity.ok(powerSupplyService.getAvailableFilters());
    }

    /**
     * @return Odpowiedż zawierająca listę składającą się z dostępnych filtrów dla obudów
     * oraz liczbie dostępnych sztuk w danym filtrze
     */
    @GetMapping(UrlPath.CASE)
    public ResponseEntity<CaseFilterResponse> getCaseFilters() {
        return ResponseEntity.ok(caseService.getAvailableFilters());
    }

    /**
     * @return Odpowiedż zawierająca listę składającą się z dostępnych filtrów dla dysków SSD
     * oraz liczbie dostępnych sztuk w danym filtrze
     */
    @GetMapping(UrlPath.SSD)
    public ResponseEntity<SsdFilterResponse> getSSDFilters() {
        return ResponseEntity.ok(ssdService.getAvailableFilters());
    }

    /**
     * @return Odpowiedż zawierająca listę składającą się z dostępnych filtrów dla dysków HDD
     * oraz liczbie dostępnych sztuk w danym filtrze
     */
    @GetMapping(UrlPath.HDD)
    public ResponseEntity<HddFilterResponse> getHDDFilters() {
        return ResponseEntity.ok(hddService.getAvailableFilters());
    }

    /**
     * @return Odpowiedż zawierająca listę składającą się z dostępnych filtrów dla układów chłodzenia powietrzem
     * oraz liczbie dostępnych sztuk w danym filtrze
     */
    @GetMapping(UrlPath.AIR_COOLER)
    public ResponseEntity<AirCoolerFilterResponse> getAirCoolerFilters() {
        return ResponseEntity.ok(airCoolerService.getAvailableFilters());
    }

    /**
     * @return Odpowiedż zawierająca listę składającą się z dostępnych filtrów dla układów chłodzenia cieczą
     * oraz liczbie dostępnych sztuk w danym filtrze
     */
    @GetMapping(UrlPath.LIQUID_COOLER)
    public ResponseEntity<LiquidCoolerFilterResponse> getLiquidCoolerFilters() {
        return ResponseEntity.ok(liquidCoolerService.getAvailableFilters());
    }
}