package com.pc_forge.backend.view.response.product;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.model.product.*;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ResponseBuilder {
    public static ResponseEntity<List<ProductResponse>> generateProductList(List<? extends Product> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(getProductResponse(product));
        }
        return ResponseEntity.ok(productResponses);
    }

    private static ProductResponse getProductResponse(Product product) {
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

    private static ProductResponse getCpuResponse(Processor product) {
        ProcessorResponse response = new ProcessorResponse();
        response.setId(product.getId());
        response.setCategory(product.getCategory());
        response.setName(product.getName());
        response.setProducer(product.getProducer());
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
        return response;
    }
}
