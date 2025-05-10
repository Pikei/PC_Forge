package com.pc_forge.backend.view.util;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.model.entity.product.Product;
import com.pc_forge.backend.model.entity.product.cooler.Cooler;
import com.pc_forge.backend.model.entity.product.cpu.Processor;
import com.pc_forge.backend.model.entity.product.drive.HardDiskDrive;
import com.pc_forge.backend.model.entity.product.drive.SolidStateDrive;
import com.pc_forge.backend.model.entity.product.gpu.GraphicsCard;
import com.pc_forge.backend.model.entity.product.mb.Motherboard;
import com.pc_forge.backend.model.entity.product.pc_case.Case;
import com.pc_forge.backend.model.entity.product.ps.PowerSupply;
import com.pc_forge.backend.model.entity.product.ram.Memory;
import com.pc_forge.backend.view.response.configuration.ProductConfigurationResponse;
import com.pc_forge.backend.view.response.product.*;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zawierająca metody statyczne, służąca do budowania odpowiedzi zwracanych w komunikacji HTTP.
 */
@NoArgsConstructor
public class ResponseBuilder {

    /**
     * Metoda służąca do budowania odpowiedzi o produkcie znajdującym się w konfiguracji.
     *
     * @param product Obiekt produktu zawartego w konfiguracji.
     * @return Obiekt klasy {@link ProductConfigurationResponse},
     * zawierającego podstawowe informacje o produktach zapisanych w konfiguracji
     */
    public static ProductConfigurationResponse getProductConfigurationResponse(Product product) {
        ProductConfigurationResponse response = new ProductConfigurationResponse();
        response.setProductEan(product.getId());
        response.setProductName(product.getName());
        response.setProductPrice(product.getPrice());
        response.setProducer(product.getProducer());
        response.setProductCategory(product.getCategory());
        return response;
    }

    /**
     * Metoda służąca do budowania listy odpowiedzi o produktach na podstawie przekazanej listy produktów.
     *
     * @param products Lista produktów
     * @return Odpowiedź HTTP zawierająca listę uproszczonych informacji o produktach
     */
    public static ResponseEntity<List<ProductResponse>> generateProductList(List<? extends Product> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(getProductResponse(product));
        }
        return ResponseEntity.ok(productResponses);
    }

    /**
     * Metoda służąca do wywołania odpowiedniej metody budującej odpowiedź produktu, w zależności od jego kategorii.
     *
     * @param product Produkt, dla którego ma zostać stworzona odpowiedź.
     * @return Obiekt klasy dziedziczącej z {@link ProductResponse}, zawierający uproszczone informacje o produkcie.
     */
    public static ProductResponse getProductResponse(Product product) {
        if (product == null) {
            return null;
        }
        String category = product.getCategory();
        return switch (category) {
            case ProductCategoryCode.CASE -> getCaseResponse((Case) product);
            case ProductCategoryCode.GRAPHICS_CARD -> getGpuResponse((GraphicsCard) product);
            case ProductCategoryCode.SSD -> getSsdResponse((SolidStateDrive) product);
            case ProductCategoryCode.HDD -> getHddResponse((HardDiskDrive) product);
            case ProductCategoryCode.MOTHERBOARD -> getMbResponse((Motherboard) product);
            case ProductCategoryCode.POWER_SUPPLY -> getPsResponse((PowerSupply) product);
            case ProductCategoryCode.PROCESSOR -> getCpuResponse((Processor) product);
            case ProductCategoryCode.RAM -> getRamResponse((Memory) product);
            case ProductCategoryCode.AIR_COOLER, ProductCategoryCode.LIQUID_COOLER ->
                    getCoolerResponse((Cooler) product);
            default -> null;
        };
    }

    /**
     * Metoda służąca do budowania odpowiedzi zawierającej uproszczone dane o obudowie komputerowej.
     *
     * @param product Obiekt klasy {@link Case}, dla którego ma zostać wygenerowana odpowiedź
     * @return Obiekt klasy {@link CaseResponse}, zawierający uproszczone informacje o obudowie komputerowej.
     */
    private static ProductResponse getCaseResponse(Case product) {
        CaseResponse response = new CaseResponse();
        response.setId(product.getId());
        response.setCategory(product.getCategory());
        response.setName(product.getName());
        response.setProducer(product.getProducer());
        response.setPrice(product.getPrice());
        response.setCaseType(product.getCaseType());
        response.setColor(product.getColor());
        response.setHasWindow(product.getHasWindow());
        response.setLightning(product.getLightning());
        return response;
    }

    /**
     * Metoda służąca do budowania odpowiedzi zawierającej uproszczone dane o karcie graficznej.
     *
     * @param product Obiekt klasy {@link GraphicsCard}, dla którego ma zostać wygenerowana odpowiedź
     * @return Obiekt klasy {@link GraphicsCardResponse}, zawierający uproszczone informacje o karcie graficznej.
     */
    private static ProductResponse getGpuResponse(GraphicsCard product) {
        GraphicsCardResponse response = new GraphicsCardResponse();
        response.setId(product.getId());
        response.setCategory(product.getCategory());
        response.setName(product.getName());
        response.setProducer(product.getProducer());
        response.setPrice(product.getPrice());
        response.setChipsetProducer(product.getChipsetProducer());
        response.setChipset(product.getChipset());
        response.setCardLength(product.getCardLength());
        response.setRam(product.getRam());
        return response;
    }

    /**
     * Metoda służąca do budowania odpowiedzi zawierającej uproszczone dane o dysku SSD.
     *
     * @param product Obiekt klasy {@link SolidStateDrive}, dla którego ma zostać wygenerowana odpowiedź
     * @return Obiekt klasy {@link SsdResponse}, zawierający uproszczone informacje o dysku SSD.
     */
    private static ProductResponse getSsdResponse(SolidStateDrive product) {
        SsdResponse response = new SsdResponse();
        response.setId(product.getId());
        response.setCategory(product.getCategory());
        response.setName(product.getName());
        response.setProducer(product.getProducer());
        response.setPrice(product.getPrice());
        response.setStorage(product.getStorage());
        response.setDriveFormat(product.getDriveFormat());
        response.setDriveInterface(product.getDriveInterface());
        response.setReadSpeed(product.getReadSpeed());
        response.setWriteSpeed(product.getWriteSpeed());
        return response;
    }

    /**
     * Metoda służąca do budowania odpowiedzi zawierającej uproszczone dane o dysku HDD.
     *
     * @param product Obiekt klasy {@link HardDiskDrive}, dla którego ma zostać wygenerowana odpowiedź
     * @return Obiekt klasy {@link HddResponse}, zawierający uproszczone informacje o dysku HDD.
     */
    private static ProductResponse getHddResponse(HardDiskDrive product) {
        HddResponse response = new HddResponse();
        response.setId(product.getId());
        response.setCategory(product.getCategory());
        response.setName(product.getName());
        response.setProducer(product.getProducer());
        response.setPrice(product.getPrice());
        response.setStorage(product.getStorage());
        response.setDriveFormat(product.getDriveFormat());
        response.setDriveInterface(product.getDriveInterface());
        response.setRotationalSpeed(product.getRotationalSpeed());
        return response;
    }

    /**
     * Metoda służąca do budowania odpowiedzi zawierającej uproszczone dane o płycie głównej.
     *
     * @param product Obiekt klasy {@link Motherboard}, dla którego ma zostać wygenerowana odpowiedź
     * @return Obiekt klasy {@link MotherboardResponse}, zawierający uproszczone informacje o płycie głównej.
     */
    private static ProductResponse getMbResponse(Motherboard product) {
        MotherboardResponse response = new MotherboardResponse();
        response.setId(product.getId());
        response.setCategory(product.getCategory());
        response.setName(product.getName());
        response.setProducer(product.getProducer());
        response.setPrice(product.getPrice());
        response.setChipset(product.getChipset());
        response.setMemoryStandard(product.getMemoryStandard());
        response.setStandard(product.getStandard().getStandardName());
        response.setMemorySlots(product.getMemorySlots());
        response.setBluetooth(product.getBluetooth());
        response.setWifi(product.getWifi());
        return response;
    }

    /**
     * Metoda służąca do budowania odpowiedzi zawierającej uproszczone dane o zasilaczu.
     *
     * @param product Obiekt klasy {@link PowerSupply}, dla którego ma zostać wygenerowana odpowiedź
     * @return Obiekt klasy {@link PowerSupplyResponse}, zawierający uproszczone informacje o zasilaczu.
     */
    private static ProductResponse getPsResponse(PowerSupply product) {
        PowerSupplyResponse response = new PowerSupplyResponse();
        response.setId(product.getId());
        response.setCategory(product.getCategory());
        response.setName(product.getName());
        response.setProducer(product.getProducer());
        response.setPrice(product.getPrice());
        response.setStandard(product.getStandard());
        response.setPower(product.getPower());
        response.setEfficiencyCertificate(product.getEfficiencyCertificate());
        response.setEfficiency(product.getEfficiency());
        response.setModularCabling(product.getModularCabling());
        return response;
    }

    /**
     * Metoda służąca do budowania odpowiedzi zawierającej uproszczone dane o procesorze.
     *
     * @param product Obiekt klasy {@link Processor}, dla którego ma zostać wygenerowana odpowiedź
     * @return Obiekt klasy {@link ProcessorResponse}, zawierający uproszczone informacje o procesorze.
     */
    private static ProductResponse getCpuResponse(Processor product) {
        ProcessorResponse response = new ProcessorResponse();
        response.setId(product.getId());
        response.setCategory(product.getCategory());
        response.setName(product.getName());
        response.setProducer(product.getProducer());
        response.setPrice(product.getPrice());
        response.setSocket(product.getSocket().getSocketName());
        response.setCores(product.getCores());
        response.setThreads(product.getThreads());
        response.setUnlocked(product.getUnlocked());
        response.setFrequency(product.getFrequency());
        response.setIntegratedGraphicsUnit(product.getIntegratedGraphicsUnit());
        response.setCoolerIncluded(product.getCoolerIncluded());
        response.setUnlocked(product.getUnlocked());
        return response;
    }

    /**
     * Metoda służąca do budowania odpowiedzi zawierającej uproszczone dane o pamięci operacyjnej RAM.
     *
     * @param product Obiekt klasy {@link Memory}, dla którego ma zostać wygenerowana odpowiedź
     * @return Obiekt klasy {@link ProductResponse}, zawierający uproszczone informacje o pamięci operacyjnej RAM.
     */
    private static ProductResponse getRamResponse(Memory product) {
        MemoryResponse response = new MemoryResponse();
        response.setId(product.getId());
        response.setCategory(product.getCategory());
        response.setName(product.getName());
        response.setProducer(product.getProducer());
        response.setPrice(product.getPrice());
        response.setMemoryType(product.getMemoryType());
        response.setTotalCapacity(product.getTotalCapacity());
        response.setLatency(product.getLatency());
        response.setFrequency(product.getFrequency());
        response.setModules(product.getNumberOfModules());
        response.setLighting(product.getLighting());
        return response;
    }

    /**
     * Metoda służąca do budowania odpowiedzi zawierającej uproszczone dane o układzie chłodzenia procesora.
     *
     * @param product Obiekt klasy {@link Cooler}, dla którego ma zostać wygenerowana odpowiedź
     * @return Obiekt klasy {@link CoolerResponse}, zawierający uproszczone informacje o układzie chłodzenia procesora.
     */
    private static ProductResponse getCoolerResponse(Cooler product) {
        CoolerResponse response = new CoolerResponse();
        response.setId(product.getId());
        response.setCategory(product.getCategory());
        response.setName(product.getName());
        response.setProducer(product.getProducer());
        response.setPrice(product.getPrice());
        response.setLightning(product.getLightning());
        response.setFanDiameter(product.getFanDiameter());
        response.setNumberOfFans(product.getFans());
        response.setNoiseLevel(product.getNoiseLevel());
        return response;
    }
}
