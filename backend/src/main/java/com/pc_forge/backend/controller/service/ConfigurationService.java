package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.controller.exceptions.ConfigurationAlreadyExists;
import com.pc_forge.backend.controller.exceptions.ConfigurationDoesNotExistException;
import com.pc_forge.backend.controller.exceptions.ProductDoesNotExistException;
import com.pc_forge.backend.model.entity.configuration.Configuration;
import com.pc_forge.backend.model.entity.product.Product;
import com.pc_forge.backend.model.entity.product.cooler.AirCooler;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.model.repository.configuration.ConfigurationRepository;
import com.pc_forge.backend.model.repository.product.CommonProductRepository;
import com.pc_forge.backend.view.body.configuration.ConfigurationBody;
import com.pc_forge.backend.view.response.configuration.CompatibilityResponse;
import com.pc_forge.backend.view.response.configuration.ConfigurationResponse;
import com.pc_forge.backend.view.response.configuration.ConfigurationShortResponse;
import com.pc_forge.backend.view.response.configuration.ProductConfigurationResponse;
import com.pc_forge.backend.view.response.product.*;
import com.pc_forge.backend.view.util.ResponseBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public ConfigurationResponse getConfiguration(User user, String name) throws ConfigurationDoesNotExistException {
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
        } catch (ProductDoesNotExistException e) {
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

    public CompatibilityResponse addConfiguration(User user, ConfigurationBody configurationBody) throws ConfigurationAlreadyExists, ProductDoesNotExistException {
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
        CompatibilityResponse response = validateConfigCompatibility(configuration);
        if (response.getCompatible()) {
            configurationRepository.save(configuration);
        }
        return response;
    }

    @SuppressWarnings("unchecked")
    private <T extends Product> T getProductFromConfigBody(Long productId) throws ProductDoesNotExistException {
        if (productId == null) {
            return null;
        }
        var optionalProduct = commonProductRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductDoesNotExistException("Product " + productId + " does not exist");
        }
        return (T) optionalProduct.get();
    }

    private CompatibilityResponse validateConfigCompatibility(Configuration configuration) {
        CompatibilityResponse response = new CompatibilityResponse();
        if (!configuration.getMotherboard().getSupportedMemoryFrequencies().contains(configuration.getMemory().getFrequency())) {
            response.setCompatible(false);
            response.setFirstProductCategoryCode(ProductCategoryCode.MOTHERBOARD);
            response.setSecondProductCategoryCode(ProductCategoryCode.RAM);
            response.setMessage("Częstotliwość pamięci " + configuration.getMemory().getFrequency() + "MHz nie jest wspierana przez tą płytę główną");
            return response;
        }
        if (!configuration.getMotherboard().getMemoryStandard().equals(configuration.getMemory().getMemoryType())) {
            response.setCompatible(false);
            response.setFirstProductCategoryCode(ProductCategoryCode.MOTHERBOARD);
            response.setSecondProductCategoryCode(ProductCategoryCode.RAM);
            response.setMessage("Typ pamięci " + configuration.getMemory().getMemoryType() + " nie jest wspierany przez tą płytę główną");
            return response;
        }
        if (configuration.getMotherboard().getMemorySlots() < configuration.getMemory().getNumberOfModules()) {
            response.setCompatible(false);
            response.setFirstProductCategoryCode(ProductCategoryCode.MOTHERBOARD);
            response.setSecondProductCategoryCode(ProductCategoryCode.RAM);
            response.setMessage("Liczba wybranych modułów pamięci przekracza liczbę slotów na płycie głównej");
            return response;
        }
        if (configuration.getMotherboard().getMaxMemoryCapacity() < configuration.getMemory().getTotalCapacity()) {
            response.setCompatible(false);
            response.setFirstProductCategoryCode(ProductCategoryCode.MOTHERBOARD);
            response.setSecondProductCategoryCode(ProductCategoryCode.RAM);
            response.setMessage("Pojemność łączna pamięci przekracza maksymalną pojemność dla tej płyty głównej");
            return response;
        }
        if (!configuration.getMotherboard().getSocket().equals(configuration.getProcessor().getSocket())) {
            response.setCompatible(false);
            response.setFirstProductCategoryCode(ProductCategoryCode.MOTHERBOARD);
            response.setSecondProductCategoryCode(ProductCategoryCode.PROCESSOR);
            response.setMessage("Procesor ma inne gniazdo niż wybrana płyta główna");
            return response;
        }
        if (!configuration.getPcCase().getSupportedMbStandards().contains(configuration.getMotherboard().getStandard())) {
            response.setCompatible(false);
            response.setFirstProductCategoryCode(ProductCategoryCode.CASE);
            response.setSecondProductCategoryCode(ProductCategoryCode.MOTHERBOARD);
            response.setMessage("Wybrana płyta główna nie pasuje do obudowy");
            return response;
        }
        if (configuration.getCooler() == null) {
            if (configuration.getProcessor().getCoolerIncluded() == false) {
                response.setCompatible(false);
                response.setFirstProductCategoryCode(ProductCategoryCode.PROCESSOR);
                response.setSecondProductCategoryCode(ProductCategoryCode.AIR_COOLER);
                response.setMessage("Procesor nie zawiera załączonego chłodzenia ani nie zostało ono wybrane w konfiguracji");
                return response;
            }
        }
        assert configuration.getCooler() != null;
        if (!configuration.getCooler().getCompatibleSockets().contains(configuration.getMotherboard().getSocket())) {
            response.setCompatible(false);
            response.setFirstProductCategoryCode(configuration.getCooler().getCategory());
            response.setSecondProductCategoryCode(ProductCategoryCode.MOTHERBOARD);
            response.setMessage("Wybrane chłodzenie pasuje do gniazda procesora na tej płycie głównej");
            return response;
        }
        if (configuration.getCooler() instanceof AirCooler) {
            if (configuration.getPcCase().getMaxCpuCoolerHeight() < ((AirCooler) configuration.getCooler()).getHeight()) {
                response.setCompatible(false);
                response.setFirstProductCategoryCode(ProductCategoryCode.CASE);
                response.setSecondProductCategoryCode(ProductCategoryCode.AIR_COOLER);
                response.setMessage("Wybrany układ chłodzenia jest zbyt wysoki dla tej obudowy");
                return response;
            }
        }

        if (configuration.getSolidStateDrive() == null && configuration.getHardDiskDrive() == null) {
            response.setCompatible(false);
            response.setFirstProductCategoryCode(ProductCategoryCode.SSD);
            response.setSecondProductCategoryCode(ProductCategoryCode.HDD);
            response.setMessage("Nie wybrano żadnego dysku");
            return response;
        }

        Integer psPower;
        if (configuration.getPowerSupply() == null) {
            if (configuration.getPcCase().getPowerSupply() != null || configuration.getPcCase().getPsPower() == 0) {
                response.setCompatible(false);
                response.setFirstProductCategoryCode(ProductCategoryCode.POWER_SUPPLY);
                response.setSecondProductCategoryCode(ProductCategoryCode.CASE);
                response.setMessage("Obudowa nie zawiera załączonego zasilacza ani nie został on wybrany w konfiguracji");
                return response;
            }
            psPower = configuration.getPcCase().getPsPower();
        } else {
            psPower = configuration.getPowerSupply().getPower();
        }
        assert configuration.getPowerSupply() != null;

        if (configuration.getGraphicsCard() == null) {
            if (configuration.getProcessor().getIntegratedGraphicsUnit() != null && !configuration.getProcessor().getIntegratedGraphicsUnit().isEmpty()) {
                response.setCompatible(false);
                response.setFirstProductCategoryCode(ProductCategoryCode.GRAPHICS_CARD);
                response.setSecondProductCategoryCode(ProductCategoryCode.PROCESSOR);
                response.setMessage("Procesor nie posiada zintegrowanego układu graficznego ani nie wybrano karty graficznej");
                return response;
            }
        }

        assert configuration.getGraphicsCard() != null;
        if (configuration.getPcCase().getMaxGpuLength() < configuration.getGraphicsCard().getCardLength()) {
            response.setCompatible(false);
            response.setFirstProductCategoryCode(ProductCategoryCode.GRAPHICS_CARD);
            response.setSecondProductCategoryCode(ProductCategoryCode.CASE);
            response.setMessage("Karta graficzna jest zbyt długa dla tej obudowy");
            return response;
        }

        if (psPower < configuration.getGraphicsCard().getRecommendedPsPower()) {
            response.setCompatible(false);
            response.setFirstProductCategoryCode(ProductCategoryCode.GRAPHICS_CARD);
            response.setSecondProductCategoryCode(ProductCategoryCode.POWER_SUPPLY);
            response.setMessage("Moc zasilacza jest mniejsza niż minimalna zalecana dla tej karty graficznej " +
                    "( " + configuration.getGraphicsCard().getRecommendedPsPower() + "W )");
            return response;
        }
        response.setCompatible(true);
        response.setFirstProductCategoryCode(null);
        response.setSecondProductCategoryCode(null);
        response.setMessage(null);
        return response;
    }

}
