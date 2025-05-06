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

/**
 * Kontroler obsługujący żądania związane pobieraniem danych o produktach.
 */
@RestController
public class ProductController {
    /**
     * Serwis obsługujący logikę związaną ze wszystkimi produktami
     */
    private final CommonProductService productService;


    /**
     * Serwis obsługujący logikę związaną z pobieraniem informacji o procesorach
     */
    private final ProcessorService processorService;


    /**
     * Serwis obsługujący logikę związaną z pobieraniem informacji o płytach głównych
     */
    private final MotherboardService motherboardService;

    /**
     * Serwis obsługujący logikę związaną z pobieraniem informacji o pamięciach RAM
     */
    private final MemoryService memoryService;

    /**
     * Serwis obsługujący logikę związaną z pobieraniem informacji o kartach graficznych
     */
    private final GraphicsCardService graphicsCardService;

    /**
     * Serwis obsługujący logikę związaną z pobieraniem informacji o zasilaczach
     */
    private final PowerSupplyService powerSupplyService;

    /**
     * Serwis obsługujący logikę związaną z pobieraniem informacji o obudowach komputerowych
     */
    private final CaseService caseService;

    /**
     * Serwis obsługujący logikę związaną z pobieraniem informacji o dyskach SSD
     */
    private final SsdService ssdService;

    /**
     * Serwis obsługujący logikę związaną z pobieraniem informacji o dyskach HDD
     */
    private final HddService hddService;

    /**
     * Serwis obsługujący logikę związaną z pobieraniem informacji o układach chłodzenia powietrzem
     */
    private final AirCoolerService airCoolerService;

    /**
     * Serwis obsługujący logikę związaną z pobieraniem informacji o układach chłodzenia cieczą
     */
    private final LiquidCoolerService liquidCoolerService;

    /**
     * Konstruktor klasy kontrolera produktów
     *
     * @param productService      serwis ogólny dla wszystkich kategorii produktów
     * @param processorService    serwis procesorów
     * @param motherboardService  serwis płyt głównych
     * @param memoryService       serwis pamięci RAM
     * @param graphicsCardService serwis kart graficznych
     * @param powerSupplyService  serwis zasilaczy
     * @param caseService         serwis obudów komputerowych
     * @param ssdService          serwis dysków SSD
     * @param hddService          serwis dysków HDD
     * @param airCoolerService    serwis układów chłodzenia powietrzem
     * @param liquidCoolerService serwis układów chłodzenia cieczą
     */
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

    /**
     * Pobiera szczegółowe dane o produkcie na podstawie jego ID.
     *
     * @param id ID produktu
     * @return Odpowiedź HTTP zawierająca obiekt klasy dziedziczącej z {@link Product}, jeśli zostanie znaleziony.
     *         Zwraca kod 404 (Not Found), jeśli produkt nie zostanie znaleziony
     *         lub jeśli ID nie jest prawidłowym numerem.
     */
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

    /**
     * Wyszukuje produkty po nazwie, z opcjonalnym filtrowaniem po kategorii.
     *
     * @param name     Nazwa produktu do wyszukania.
     * @param category Kod kategorii produktu określony w {@link com.pc_forge.backend.controller.api.constants.ProductCategoryCode}.
     *                 Jest to parametr opcjonalny. Jego brak spowoduje wyszukanie produktów zawierających w nazwie podany ciąg znaków
     *                 spośród wszystkich kategorii. Podanie kodu kategorii zawęzi obszar poszukiwań.
     * @return Lista {@link ProductResponse}, czyli DTO produktów zawierających uproszczone dane, tworzone przez {@link ResponseBuilder}
     */
    @GetMapping(UrlPath.PRODUCT + UrlPath.SEARCH + "/{name}")
    public ResponseEntity<List<ProductResponse>> getProductsByName(
            @PathVariable String name,
            @RequestParam(value = RequestParams.PRODUCT_CATEGORY, required = false) String category
    ) {
        List<Product> products = productService.getProductsByName(name, category);
        return ResponseBuilder.generateProductList(products);
    }

    /**
     * Pobiera listę procesorów, filtrując je na podstawie parametrów zapytania.
     *
     * @param request Obiekt {@link HttpServletRequest} zawierający parametry zapytania użyte do filtrowania procesorów.
     *                Na podstawie przekazanych parametrów tworzony jest obiekt {@link ProcessorFilter}
     *                służący do filtrowania wyników.
     * @return Odpowiedź HTTP zawierająca listę {@link ProductResponse}, czyli DTO mających uproszczone dane produktów.
     */
    @GetMapping(UrlPath.CATEGORY + UrlPath.PROCESSOR)
    public ResponseEntity<List<ProductResponse>> getFilteredProcessors(HttpServletRequest request
    ) {
        Map<String, String[]> params = request.getParameterMap();
        ProcessorFilter filter = new ProcessorFilter(params);
        List<Processor> products = processorService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    /**
     * Pobiera listę płyt głównych, filtrując je na podstawie parametrów zapytania.
     *
     * @param request Obiekt {@link HttpServletRequest} zawierający parametry zapytania użyte do filtrowania płyt głównych.
     *                Na podstawie przekazanych parametrów tworzony jest obiekt {@link MotherboardFilter}
     *                służący do filtrowania wyników.
     * @return Odpowiedź HTTP zawierająca listę {@link ProductResponse}, czyli DTO mających uproszczone dane produktów.
     */
    @GetMapping(UrlPath.CATEGORY + UrlPath.MOTHERBOARD)
    public ResponseEntity<List<ProductResponse>> getFilteredMotherboards(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        MotherboardFilter filter = new MotherboardFilter(params);
        List<Motherboard> products = motherboardService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    /**
     * Pobiera listę pamięci RAM, filtrując je na podstawie parametrów zapytania.
     *
     * @param request Obiekt {@link HttpServletRequest} zawierający parametry zapytania użyte do filtrowania pamięci RAM.
     *                Na podstawie przekazanych parametrów tworzony jest obiekt {@link MemoryFilter}
     *                służący do filtrowania wyników.
     * @return Odpowiedź HTTP zawierająca listę {@link ProductResponse}, czyli DTO mających uproszczone dane produktów.
     *
     */
    @GetMapping(UrlPath.CATEGORY + UrlPath.MEMORY)
    public ResponseEntity<List<ProductResponse>> getFilteredMemories(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        MemoryFilter filter = new MemoryFilter(params);
        List<Memory> products = memoryService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    /**
     * Pobiera listę kart graficznych, filtrując je na podstawie parametrów zapytania.
     *
     * @param request Obiekt {@link HttpServletRequest} zawierający parametry zapytania użyte do filtrowania kart graficznych.
     *                Na podstawie przekazanych parametrów tworzony jest obiekt {@link GpuFilter}
     *                służący do filtrowania wyników.
     * @return Odpowiedź HTTP zawierająca listę {@link ProductResponse}, czyli DTO mających uproszczone dane produktów.
     *
     */
    @GetMapping(UrlPath.CATEGORY + UrlPath.GRAPHICS_CARD)
    public ResponseEntity<List<ProductResponse>> getFilteredGraphicCards(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        GpuFilter filter = new GpuFilter(params);
        List<GraphicsCard> products = graphicsCardService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    /**
     * Pobiera listę zasilaczy, filtrując je na podstawie parametrów zapytania.
     *
     * @param request Obiekt {@link HttpServletRequest} zawierający parametry zapytania użyte do filtrowania zasilaczy.
     *                Na podstawie przekazanych parametrów tworzony jest obiekt {@link PowerSupplyFilter}
     *                służący do filtrowania wyników.
     * @return Odpowiedź HTTP zawierająca listę {@link ProductResponse}, czyli DTO mających uproszczone dane produktów.
     */
    @GetMapping(UrlPath.CATEGORY + UrlPath.POWER_SUPPLY)
    public ResponseEntity<List<ProductResponse>> getFilteredPowerSupplies(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        PowerSupplyFilter filter = new PowerSupplyFilter(params);
        List<PowerSupply> products = powerSupplyService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    /**
     * Pobiera listę obudów komputerowych, filtrując je na podstawie parametrów zapytania.
     *
     * @param request Obiekt {@link HttpServletRequest} zawierający parametry zapytania użyte do filtrowania obudów.
     *                Na podstawie przekazanych parametrów tworzony jest obiekt {@link CaseFilter}
     *                służący do filtrowania wyników.
     * @return Odpowiedź HTTP zawierająca listę {@link ProductResponse}, czyli DTO mających uproszczone dane produktów.
     */
    @GetMapping(UrlPath.CATEGORY + UrlPath.CASE)
    public ResponseEntity<List<ProductResponse>> getFilteredCases(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        CaseFilter filter = new CaseFilter(params);
        List<Case> products = caseService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    /**
     * Pobiera listę dysków SSD, filtrując je na podstawie parametrów zapytania.
     *
     * @param request Obiekt {@link HttpServletRequest} zawierający parametry zapytania użyte do filtrowania dysków SSD.
     *                Na podstawie przekazanych parametrów tworzony jest obiekt {@link SsdFilter}
     *                służący do filtrowania wyników.
     * @return Odpowiedź HTTP zawierająca listę {@link ProductResponse}, czyli DTO mających uproszczone dane produktów.
     */
    @GetMapping(UrlPath.CATEGORY + UrlPath.SSD)
    public ResponseEntity<List<ProductResponse>> getFilteredSSDs(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        SsdFilter filter = new SsdFilter(params);
        List<SolidStateDrive> products = ssdService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    /**
     * Pobiera listę dysków HDD, filtrując je na podstawie parametrów zapytania.
     *
     * @param request Obiekt {@link HttpServletRequest} zawierający parametry zapytania użyte do filtrowania dysków HDD.
     *                Na podstawie przekazanych parametrów tworzony jest obiekt {@link HddFilter}
     *                służący do filtrowania wyników.
     * @return Odpowiedź HTTP zawierająca listę {@link ProductResponse}, czyli DTO mających uproszczone dane produktów.
     */
    @GetMapping(UrlPath.CATEGORY + UrlPath.HDD)
    public ResponseEntity<List<ProductResponse>> getFilteredHDDs(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        HddFilter filter = new HddFilter(params);
        List<HardDiskDrive> products = hddService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    /**
     * Pobiera listę chłodzeń powietrznych, filtrując je na podstawie parametrów zapytania.
     *
     * @param request Obiekt {@link HttpServletRequest} zawierający parametry zapytania użyte do filtrowania chłodzeń powietrznych.
     *                Na podstawie przekazanych parametrów tworzony jest obiekt {@link AirCoolerFilter}
     *                służący do filtrowania wyników.
     * @return Odpowiedź HTTP zawierająca listę {@link ProductResponse}, czyli DTO mających uproszczone dane produktów.
     */
    @GetMapping(UrlPath.CATEGORY + UrlPath.AIR_COOLER)
    public ResponseEntity<List<ProductResponse>> getFilteredAirCoolers(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        AirCoolerFilter filter = new AirCoolerFilter(params);
        List<AirCooler> products = airCoolerService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }

    /**
     * Pobiera listę chłodzeń cieczą, filtrując je na podstawie parametrów zapytania.
     *
     * @param request Obiekt {@link HttpServletRequest} zawierający parametry zapytania użyte do filtrowania chłodzeń cieczą.
     *                Na podstawie przekazanych parametrów tworzony jest obiekt {@link LiquidCoolerFilter}
     *                służący do filtrowania wyników.
     * @return Odpowiedź HTTP zawierająca listę {@link ProductResponse}, czyli DTO mających uproszczone dane produktów.
     */
    @GetMapping(UrlPath.CATEGORY + UrlPath.LIQUID_COOLER)
    public ResponseEntity<List<ProductResponse>> getFilteredLiquidCoolers(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        LiquidCoolerFilter filter = new LiquidCoolerFilter(params);
        List<LiquidCooler> products = liquidCoolerService.getFilteredProducts(filter);
        return ResponseBuilder.generateProductList(products);
    }
}
