package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.controller.exceptions.ConfigurationAlreadyExists;
import com.pc_forge.backend.controller.exceptions.ConfigurationDoesNotExistException;
import com.pc_forge.backend.controller.exceptions.ProductDoesNotExistException;
import com.pc_forge.backend.controller.exceptions.ProductNotCompatibleException;
import com.pc_forge.backend.model.entity.configuration.Configuration;
import com.pc_forge.backend.model.entity.product.Product;
import com.pc_forge.backend.model.entity.product.cooler.AirCooler;
import com.pc_forge.backend.model.entity.product.mb.Motherboard;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.model.repository.configuration.ConfigurationRepository;
import com.pc_forge.backend.model.repository.product.CommonProductRepository;
import com.pc_forge.backend.model.repository.product.cpu.ProcessorRepository;
import com.pc_forge.backend.view.body.configuration.ConfigurationBody;
import com.pc_forge.backend.view.response.configuration.ConfigurationResponse;
import com.pc_forge.backend.view.response.configuration.ConfigurationShortResponse;
import com.pc_forge.backend.view.response.configuration.ProductConfigurationResponse;
import com.pc_forge.backend.view.response.product.*;
import com.pc_forge.backend.view.util.ResponseBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;
    private final ShoppingCartService shoppingCartService;
    private final CommonProductRepository commonProductRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository, ShoppingCartService shoppingCartService, CommonProductRepository commonProductRepository) {
        this.configurationRepository = configurationRepository;
        this.shoppingCartService = shoppingCartService;
        this.commonProductRepository = commonProductRepository;

    }

    public List<ConfigurationShortResponse> getAllConfigurations(User user) {
        List<ConfigurationShortResponse> configListResponse;
        List<Configuration> configurations = configurationRepository.findByUser_Id(user.getId());
        configListResponse = configurations.stream().map(this::getConfigurationShortResponse).collect(Collectors.toList());
        return configListResponse;
    }

    private ConfigurationShortResponse getConfigurationShortResponse(Configuration configuration) {
        ConfigurationShortResponse response = new ConfigurationShortResponse();
        response.setName(configuration.getConfigName());
        response.setPrice(configuration.getTotalPrice());
        List<ProductConfigurationResponse> products = new ArrayList<>();
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getProcessor()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getMotherboard()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getMemory()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getGraphicsCard()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getPowerSupply()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getCooler()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getPcCase()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getHardDiskDrive()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getSolidStateDrive()));
        response.setProducts(products);
        return response;
    }

    public ConfigurationResponse getConfiguration(User user, String name) {
        var optionalConfiguration = configurationRepository.findByUser_IdAndConfigName(user.getId(), name);
        if (optionalConfiguration.isEmpty()) {
            throw new ConfigurationDoesNotExistException("Configuration " + name + " does not exist");
        }
        var configuration = optionalConfiguration.get();
        var response = new ConfigurationResponse();
        response.setName(configuration.getConfigName());
        response.setProcessor((ProcessorResponse) ResponseBuilder.getProductResponse(configuration.getProcessor()));
        response.setMotherboard((MotherboardResponse) ResponseBuilder.getProductResponse(configuration.getMotherboard()));
        response.setMemory((MemoryResponse) ResponseBuilder.getProductResponse(configuration.getMemory()));
        response.setGraphicsCard((GraphicsCardResponse) ResponseBuilder.getProductResponse(configuration.getGraphicsCard()));
        response.setPowerSupply((PowerSupplyResponse) ResponseBuilder.getProductResponse(configuration.getPowerSupply()));
        response.setCooler((CoolerResponse) ResponseBuilder.getProductResponse(configuration.getCooler()));
        response.setPcCase((CaseResponse) ResponseBuilder.getProductResponse(configuration.getPcCase()));
        response.setSolidStateDrive((SsdResponse) ResponseBuilder.getProductResponse(configuration.getSolidStateDrive()));
        response.setHardDiskDrive((HddResponse) ResponseBuilder.getProductResponse(configuration.getHardDiskDrive()));
        return response;
    }

    public void addConfigurationToCart(User user, String name) throws ConfigurationDoesNotExistException {
        var optionalConfiguration = configurationRepository.findByUser_IdAndConfigName(user.getId(), name);
        if (optionalConfiguration.isEmpty()) {
            throw new ConfigurationDoesNotExistException("Configuration " + name + " does not exist");
        }
        var configuration = optionalConfiguration.get();
        try {
            shoppingCartService.addProductToCart(user, configuration.getMemory().getId());
            shoppingCartService.addProductToCart(user, configuration.getProcessor().getId());
            shoppingCartService.addProductToCart(user, configuration.getMotherboard().getId());
            shoppingCartService.addProductToCart(user, configuration.getGraphicsCard().getId());
            shoppingCartService.addProductToCart(user, configuration.getPowerSupply().getId());
            shoppingCartService.addProductToCart(user, configuration.getCooler().getId());
            shoppingCartService.addProductToCart(user, configuration.getPcCase().getId());
            shoppingCartService.addProductToCart(user, configuration.getHardDiskDrive().getId());
            shoppingCartService.addProductToCart(user, configuration.getSolidStateDrive().getId());
        } catch (ConfigurationDoesNotExistException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteConfiguration(User user, String name) throws ConfigurationDoesNotExistException {
        var optionalConfiguration = configurationRepository.findByUser_IdAndConfigName(user.getId(), name);
        if (optionalConfiguration.isEmpty()) {
            throw new ConfigurationDoesNotExistException("Configuration " + name + " does not exist");
        }
        configurationRepository.delete(optionalConfiguration.get());
    }

    public void addConfiguration(User user, @Valid ConfigurationBody configurationBody) throws ConfigurationAlreadyExists {
        var configuration = new Configuration();
        configuration.setUser(user);
        var name = configurationBody.getConfigName();
        if (configurationRepository.findByUser_IdAndConfigName(user.getId(), name).isEmpty()) {
            configuration.setConfigName(name);
        } else {
            throw new ConfigurationAlreadyExists("Configuration " + name + " already exists");
        }
        configuration.setConfigName(configurationBody.getConfigName());
        configuration.setMotherboard(getProductFromConfigBody(configurationBody.getMotherboardId()));
        configuration.setProcessor(getProductFromConfigBody(configurationBody.getProcessorId()));
        configuration.setMemory(getProductFromConfigBody(configurationBody.getMemoryId()));
        configuration.setGraphicsCard(getProductFromConfigBody(configurationBody.getGraphicsCardId()));
        configuration.setPowerSupply(getProductFromConfigBody(configurationBody.getPowerSupplyId()));
        configuration.setCooler(getProductFromConfigBody(configurationBody.getCoolerId()));
        configuration.setPcCase(getProductFromConfigBody(configurationBody.getCaseId()));
        configuration.setHardDiskDrive(getProductFromConfigBody(configurationBody.getHardDiskDriveId()));
        configuration.setSolidStateDrive(getProductFromConfigBody(configurationBody.getSolidStateDriveId()));
        validateConfigCompatibility(configuration);
        configurationRepository.save(configuration);
    }

    @SuppressWarnings("unchecked")
    private <T extends Product> T getProductFromConfigBody(Long productId) throws ProductDoesNotExistException {
        var optionalProduct = commonProductRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductDoesNotExistException("Product " + productId + " does not exist");
        }
        return (T) optionalProduct.get();
    }

    private void validateConfigCompatibility(Configuration configuration) throws ProductNotCompatibleException {
        String message;
        if (!configuration.getMotherboard().getSupportedMemoryFrequencies().contains(configuration.getMemory().getFrequency())) {
            message = "Memory frequency " + configuration.getMemory().getFrequency() + " is not supported by motherboard";
            throw new ProductNotCompatibleException(message);
        }
        if (!configuration.getMotherboard().getMemoryStandard().equals(configuration.getMemory().getMemoryType())) {
            message = "Memory type " + configuration.getMemory().getMemoryType() + " is not supported by motherboard";
            throw new ProductNotCompatibleException(message);
        }
        if (configuration.getMotherboard().getMemorySlots() < configuration.getMemory().getNumberOfModules()) {
            message = "Number of memory modules: " + configuration.getMemory().getNumberOfModules() +
                    " is greater than number of memory slots on motherboard (" +
                    configuration.getMotherboard().getMemorySlots() + ")";
            throw new ProductNotCompatibleException(message);
        }
        if (configuration.getMotherboard().getMaxMemoryCapacity() < configuration.getMemory().getTotalCapacity()) {
            message = "Memory capacity: " + configuration.getMemory().getTotalCapacity() + " is greater than maximum supported by motherboard";
            throw new ProductNotCompatibleException(message);
        }
        if (!configuration.getMotherboard().getSocket().equals(configuration.getProcessor().getSocket())) {
            message = "Socket mismatch. Motherboard socket: " + configuration.getMotherboard().getSocketName()
                    + " CPU socket: " + configuration.getProcessor().getSocketName();
            throw new ProductNotCompatibleException(message);
        }
        if (!configuration.getPcCase().getSupportedMbStandards().contains(configuration.getMotherboard().getStandard())) {
            message = "Motherboard standard " + configuration.getMotherboard().getStandard().getStandardName() + " not compatible with pc case";
            throw new ProductNotCompatibleException(message);
        }
        if (configuration.getCooler() == null) {
            if (configuration.getProcessor().getCoolerIncluded() == false) {
                message = "The processor does not include cooling, nor was it selected in the configuration";
                throw new ProductNotCompatibleException(message);
            }
        }
        assert configuration.getCooler() != null;
        if (!configuration.getCooler().getCompatibleSockets().contains(configuration.getMotherboard().getSocket())) {
            message = "Socket " + configuration.getMotherboard().getSocketName() + " is not compatible with Cooler";
            throw new ProductNotCompatibleException(message);
        }
        if (configuration.getCooler() instanceof AirCooler) {
            if (configuration.getPcCase().getMaxCpuCoolerHeight() < ((AirCooler) configuration.getCooler()).getHeight()) {
                message = "Cooler height is greater than maximum on this pc case";
                throw new ProductNotCompatibleException(message);
            }
        }

        if (configuration.getSolidStateDrive() == null && configuration.getHardDiskDrive() == null) {
            message = "Drive not selected";
            throw new ProductNotCompatibleException(message);
        }

        Integer psPower;
        if (configuration.getPowerSupply() == null) {
            if (configuration.getPcCase().getPowerSupply() != null || configuration.getPcCase().getPsPower() == 0) {
                message = "Case does not include a power supply, nor is one selected in the configuration";
                throw new ProductNotCompatibleException(message);
            }
            psPower = configuration.getPcCase().getPsPower();
        } else {
            psPower = configuration.getPowerSupply().getPower();
        }
        assert configuration.getPowerSupply() != null;

        if (configuration.getGraphicsCard() == null) {
            if (configuration.getProcessor().getIntegratedGraphicsUnit() != null && !configuration.getProcessor().getIntegratedGraphicsUnit().isEmpty()) {
                message = "Graphics card is not selected and the processor does not have an integrated graphics unit";
                throw new ProductNotCompatibleException(message);
            }
        }

        assert configuration.getGraphicsCard() != null;
        if (configuration.getPcCase().getMaxGpuLength() < configuration.getGraphicsCard().getCardLength()) {
            message = "Graphics card length is greater than maximum on this pc case";
            throw new ProductNotCompatibleException(message);
        }

        if (psPower < configuration.getGraphicsCard().getRecommendedPsPower()) {
            message = "Power supply power is less than recommended for the selected graphics card";
            throw new ProductNotCompatibleException(message);
        }
    }

}
