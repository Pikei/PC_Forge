package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Klasa serwisu konfiguracji. Implementuje logikę związaną z konfiguracją PC użytkownika.
 */
@Service
public class ConfigurationService {
    /**
     * Repozytorium/DAO konfiguracji. Służy do wywoływania kwerend związanych z konfiguracjami.
     */
    private final ConfigurationRepository configurationRepository;

    /**
     * Repozytorium/DAO produktu. Służy do wywoływania kwerend związanych z pobieraniem danych o produktach.
     */
    private final CommonProductRepository commonProductRepository;


    /**
     * Serwis koszyka zakupowego użytkownika. Odpowiada za dodanie produktów z konfiguracji do koszyka.
     */
    private final ShoppingCartService shoppingCartService;

    /**
     * Konstruktor konfiguracji. Wstrzykuje odpowiednie zależności do serwisu.
     *
     * @param configurationRepository Repozytorium/DAO konfiguracji
     * @param commonProductRepository Repozytorium/DAO produktu
     * @param shoppingCartService     Serwis koszyka zakupowego użytkownika
     */
    public ConfigurationService(ConfigurationRepository configurationRepository, CommonProductRepository commonProductRepository, ShoppingCartService shoppingCartService) {
        this.configurationRepository = configurationRepository;
        this.commonProductRepository = commonProductRepository;
        this.shoppingCartService = shoppingCartService;

    }


    /**
     * Metoda odpowiedzialna za pobranie wszystkich konfiguracji dla użytkownika w formie uproszczonego DTO.
     * W zwracanej odpowiedzi znajdują się wyłącznie podstawowe informacje o produktach.
     *
     * @param user obiekt zalogowanego użytkownika dostarczony przez Spring Security
     * @return Lista zawierająca skrócone informacje o konfiguracjach użytkownika.
     */
    public List<ConfigurationShortResponse> getAllConfigurations(User user) {
        List<ConfigurationShortResponse> configListResponse;
        List<Configuration> configurations = configurationRepository.findByUser_Id(user.getId());
        configListResponse = configurations.stream().map(this::getConfigurationShortResponse).collect(Collectors.toList());
        return configListResponse;
    }


    /**
     * Metoda tworząca krótką odpowiedź zawierającą podstawowe informacje o produktach zawartych w konfiguracji użytkownika.
     * Jest wywoływana przez metodę {@link #getAllConfigurations(User)}
     *
     * @param configuration Obiekt konfiguracji użytkownika
     * @return odpowiedź zawierająca podstawowe informacje o produktach w konkretnej konfiguracji
     */
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

    /**
     * Metoda odpowiedzialna za pobranie bardziej szczegółowych informacji o konfiguracji niż ma to miejsce w
     * {@link #getAllConfigurations(User)}. Zwraca podstawowe informacje o parametrach produktów.
     * Służy do pobierania danych o produktach w jednej konkretnej konfiguracji znalezionej po jej nazwie i użytkowniku.
     * Użytkownik może mieć jedną konfigurację zapisaną pod wskazaną nazwą.
     *
     * @param user Obiekt zalogowanego użytkownika dostarczony przez Spring Security.
     * @param name Nazwa konfiguracji
     * @return Obiekt konfiguracji użytkownika
     * @throws ConfigurationDoesNotExistException w przypadku, gdy nie istnieje konfiguracja o wskazanej nazwie.
     */
    public ConfigurationResponse getConfiguration(User user, String name) throws ConfigurationDoesNotExistException {
        var optionalConfiguration = configurationRepository.findByUser_IdAndConfigName(user.getId(), name);
        if (optionalConfiguration.isEmpty()) {
            throw new ConfigurationDoesNotExistException("Configuration " + name + " does not exist");
        }
        var configuration = optionalConfiguration.get();
        var response = new ConfigurationResponse();
        response.setName(configuration.getConfigName());
        response.setPrice(configuration.getTotalPrice());
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


    /**
     * Metoda dodająca produkty z konfiguracji do koszyka użytkownika.
     *
     * @param user Obiekt zalogowanego użytkownika dostarczony przez Spring Security.
     * @param name Nazwa konfiguracji
     * @throws ConfigurationDoesNotExistException w przypadku, gdy nie istnieje konfiguracja o wskazanej nazwie.
     */
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

    /**
     * Metoda usuwająca na stałe zapisaną konfigurację użytkownika.
     *
     * @param user Obiekt zalogowanego użytkownika dostarczony przez Spring Security.
     * @param name Nazwa konfiguracji
     * @throws ConfigurationDoesNotExistException w przypadku, gdy nie istnieje konfiguracja o wskazanej nazwie.
     */
    public void deleteConfiguration(User user, String name) throws ConfigurationDoesNotExistException {
        var optionalConfiguration = configurationRepository.findByUser_IdAndConfigName(user.getId(), name);
        if (optionalConfiguration.isEmpty()) {
            throw new ConfigurationDoesNotExistException("Configuration " + name + " does not exist");
        }
        configurationRepository.delete(optionalConfiguration.get());
    }

    /**
     * Metoda zapisująca nową lub edytująca istniejącą konfigurację. Pobiera nazwę z Request body i sprawdza,
     * czy konfiguracja o wskazanej nazwie już istnieje, czy jeszcze nie. Jeśli konfiguracja istnieje,
     * jej obiekt jest pobierany z bazy danych, a jeśli nie to tworzony jest nowy obiekt konfiguracji.
     * Następnie pobierane są identyfikatory produktów z request body i dodawane do obiektu konfiguracji.
     * Po dodaniu produktów są one walidowane pod kątem kompatybilności. Jeśli konfiguracja jest poprawna,
     * zostaje zapisana w bazie danych.
     *
     * @param user              Obiekt zalogowanego użytkownika dostarczony przez Spring Security.
     * @param configurationBody Obiekt zawierający identyfikatory produktów i nazwę konfiguracji. Dostarczany jest jako
     *                          request body.
     * @return obiekt klasy {@link CompatibilityResponse} zawierający informacje o tym, czy produkty są kompatybilne,
     * oraz dodatkowe informacje w przypadku braku zgodności produktów.
     * @throws ProductDoesNotExistException w przypadku próby odwołania się do identyfikatora produktu
     *                                      nieistniejącego w bazie danych
     * @throws ClassCastException           w przypadku, gdy otrzymane Request body jest błędne
     *                                      i posiada błędne identyfikatory produktów przypisane do złych pól
     */
    public CompatibilityResponse saveOrEditConfiguration(User user, ConfigurationBody configurationBody) throws ProductDoesNotExistException, ClassCastException {
        Optional<Configuration> optionalConfiguration = configurationRepository.findByUser_IdAndConfigName(user.getId(), configurationBody.getConfigName());
        Configuration configuration;
        if (optionalConfiguration.isEmpty()) {
            configuration = new Configuration();
            configuration.setUser(user);
        } else {
            configuration = optionalConfiguration.get();
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

    /**
     * Metoda pobierająca odpowiednią encję produktu, pobraną na podstawie jej identyfikatora.
     *
     * @param productId identyfikator produktu
     * @param <T>       typ generyczny, klasa dziedzicząca po {@link Product}
     * @return obiekt klasy dziedziczącej po {@link Product}
     * @throws ProductDoesNotExistException w przypadku próby odwołania się do identyfikatora produktu
     *                                      nieistniejącego w bazie danych
     */
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

    /**
     * Metoda walidująca kompatybilność produktów w konfiguracji.
     *
     * @param configuration obiekt konfiguracji
     * @return obiekt klasy {@link CompatibilityResponse}, zawierający informację o tym, czy produkty są ze sobą kompatybilne,
     * oraz dodatkowe informacje dla użytkownika w przypadku, gdy wystąpiła niezgodność.
     */
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
            response.setMessage("Moc zasilacza jest mniejsza niż minimalna zalecana dla tej karty graficznej " + "( " + configuration.getGraphicsCard().getRecommendedPsPower() + "W )");
            return response;
        }
        response.setCompatible(true);
        response.setFirstProductCategoryCode(null);
        response.setSecondProductCategoryCode(null);
        response.setMessage(null);
        return response;
    }

    /**
     * Metoda sprawdzająca, czy konfiguracja o wskazanej nazwie już istnieje, czy jeszcze nie. Używana w celu utwierdzenia,
     * że użytkownik chce nadpisać lub usunąć istniejącą konfigurację, bądź zapisać nową.
     *
     * @param user Obiekt zalogowanego użytkownika dostarczony przez Spring Security.
     * @param name Nazwa konfiguracji
     * @return Odpowiedź HTTP o statusie 200 (OK) zawierającą {@code true} jeśli konfiguracja o wskazanej nazwie
     * już istnieje i jest przypisana do konta zalogowanego użytkownika, {@code false} w przeciwnym razie.
     */
    public ResponseEntity<Boolean> check_if_exist(User user, String name) {
        Optional<Configuration> optionalConfiguration = configurationRepository.findByUser_IdAndConfigName(user.getId(), name);
        if (optionalConfiguration.isPresent()) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}
