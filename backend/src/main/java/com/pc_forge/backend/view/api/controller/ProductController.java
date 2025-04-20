package com.pc_forge.backend.view.api.controller;

import com.pc_forge.backend.controller.service.product.ProcessorService;
import com.pc_forge.backend.controller.service.product.ProductService;
import com.pc_forge.backend.model.database.product.*;
import com.pc_forge.backend.view.api.ProductCategoryCode;
import com.pc_forge.backend.view.api.ProductCategoryUrl;
import com.pc_forge.backend.controller.filter.ProcessorFilter;
import com.pc_forge.backend.view.api.request.response.product.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProcessorService processorService;

    public ProductController(ProductService productService, ProcessorService processorService) {
        this.productService = productService;
        this.processorService = processorService;
    }

    @GetMapping("category-testfilter/cpu")
    public ResponseEntity<List<ProductResponse>> getFilteredProcessors(
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "producers", required = false) List<String> producers,
            @RequestParam(value = "sockets", required = false) List<String> sockets,
            @RequestParam(value = "lines", required = false) List<String> lines,
            @RequestParam(value = "cores", required = false) List<Integer> cores,
            @RequestParam(value = "freq", required = false) List<Double> freq,
            @RequestParam(value = "igu", required = false) List<String> igu,
            @RequestParam(value = "pack", required = false) List<String> pack,
            @RequestParam(value = "unlocked", required = false) Boolean unlocked,
            @RequestParam(value = "cooler", required = false) Boolean cooler
    ) {
        ProcessorFilter filter = new ProcessorFilter();
        filter.setPriceMaximum(maxPrice);
        filter.setPriceMinimum(minPrice);
        filter.setSelectedProducers(producers);
        filter.setSelectedSockets(sockets);
        filter.setSelectedLines(lines);
        filter.setSelectedCores(cores);
        filter.setSelectedFrequencies(freq);
        filter.setSelectedGraphicsUnits(igu);
        filter.setSelectedPackagingTypes(pack);
        filter.setUnlocked(unlocked);
        filter.setCoolerIncluded(cooler);
        List<Product> products = processorService.getFilteredProducts(filter);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(getProductResponse(product));
        }
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsInCategory(ProductCategoryUrl.valueOf(category).getCode());
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(getProductResponse(product));
        }
        if (productResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        try {
            Long productId = Long.parseLong(id);
            Product product = productService.getProductById(productId).orElse(null);
            return ResponseEntity.ok(product);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private ProductResponse getProductResponse(Product product) {
        if (product == null) {
            return null;
        }
        String category = product.getCategory();
        if (category.equals(ProductCategoryCode.CASE.getCode())) {
            return getCaseResponse((Case) product);
        } else if (category.equals(ProductCategoryCode.GRAPHICS_CARD.getCode())) {
            return getGpuResponse((GraphicsCard) product);
        } else if (category.equals(ProductCategoryCode.SSD.getCode())) {
            return getSsdResponse((SolidStateDrive) product);
        } else if (category.equals(ProductCategoryCode.HDD.getCode())) {
            return getHddResponse((HardDiskDrive) product);
        } else if (category.equals(ProductCategoryCode.MOTHERBOARD.getCode())) {
            return getMbResponse((Motherboard) product);
        } else if (category.equals(ProductCategoryCode.POWER_SUPPLY.getCode())) {
            return getPsResponse((PowerSupply) product);
        } else if (category.equals(ProductCategoryCode.PROCESSOR.getCode())) {
            return getCpuResponse((Processor) product);
        } else if (category.equals(ProductCategoryCode.RAM.getCode())) {
            return getRamResponse((Memory) product);
        } else if (category.equals(ProductCategoryCode.AIR_COOLER.getCode()) || category.equals(ProductCategoryCode.LIQUID_COOLER.getCode())) {
            return getCoolerResponse((Cooler) product);
        }
        return null;
    }

    private ProductResponse getCaseResponse(Case product) {
        CaseResponse response = new CaseResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setColor(product.getColor());
        response.setLightning(product.getLightning());
        response.setCaseType(product.getCaseType());
        response.setHasWindow(product.getHasWindow());
        return response;
    }

    private ProductResponse getGpuResponse(GraphicsCard product) {
        GraphicsCardResponse response = new GraphicsCardResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setChipsetProducer(product.getChipsetProducer());
        response.setChipset(product.getChipset());
        response.setCardLength(product.getCardLength());
        response.setRam(product.getRam());
        return response;
    }

    private ProductResponse getSsdResponse(SolidStateDrive product) {
        SsdResponse response = new SsdResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setStorage(product.getStorage());
        response.setDriveFormat(product.getDriveFormat());
        response.setDriveInterface(product.getDriveInterface());
        response.setReadSpeed(product.getReadSpeed());
        response.setWriteSpeed(product.getWriteSpeed());
        return response;
    }

    private ProductResponse getHddResponse(HardDiskDrive product) {
        HddResponse response = new HddResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setStorage(product.getStorage());
        response.setDriveFormat(product.getDriveFormat());
        response.setDriveInterface(product.getDriveInterface());
        response.setRotationalSpeed(product.getRotationalSpeed());
        return response;
    }

    private ProductResponse getMbResponse(Motherboard product) {
        MotherboardResponse response = new MotherboardResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setChipset(product.getChipset());
        response.setMemoryStandard(product.getMemoryStandard());
        response.setStandard(product.getStandard().getStandardName());
        response.setMemorySlots(product.getMemorySlots());
        response.setBluetooth(product.getBluetooth());
        response.setWifi(product.getWifi());
        return response;
    }

    private ProductResponse getPsResponse(PowerSupply product) {
        PowerSupplyResponse response = new PowerSupplyResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setStandard(product.getStandard());
        response.setPower(product.getPower());
        response.setEfficiencyCertificate(product.getEfficiencyCertificate());
        response.setEfficiency(product.getEfficiency());
        response.setModularCabling(product.getModularCabling());
        return response;
    }

    private ProductResponse getCpuResponse(Processor product) {
        ProcessorResponse response = new ProcessorResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setSocket(product.getSocket().getSocket());
        response.setCores(product.getCores());
        response.setThreads(product.getThreads());
        response.setUnlocked(product.getUnlocked());
        response.setFrequency(product.getFrequency());
        response.setIntegratedGraphicsUnit(product.getIntegratedGraphicsUnit());
        response.setCoolerIncluded(product.getCoolerIncluded());
        response.setUnlocked(product.getUnlocked());
        return response;
    }

    private ProductResponse getRamResponse(Memory product) {
        MemoryResponse response = new MemoryResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setMemoryType(product.getMemoryType());
        response.setTotalCapacity(product.getTotalCapacity());
        response.setLatency(product.getLatency());
        response.setFrequency(product.getFrequency());
        return response;
    }

    private ProductResponse getCoolerResponse(Cooler product) {
        CoolerResponse response = new CoolerResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setLightning(product.getLightning());
        response.setFanDiameter(product.getFanDiameter());
        return response;
    }
}
