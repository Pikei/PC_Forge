package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.LiquidCoolerFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.entity.product.cooler.LiquidCooler;
import com.pc_forge.backend.model.repository.product.cooler.LiquidCoolerRepository;
import com.pc_forge.backend.model.repository.product.ProductRepository;
import com.pc_forge.backend.view.response.filter.LiquidCoolerFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa serwisu układu chłodzenia cieczą. Dziedziczy z klasy {@link AbstractProductService},
 * przekazując klasę {@link LiquidCooler} jako typ produktu w klasie nadrzędnej. Jest odpowiedzialna za pobieranie
 * dostępnych filtrów i stosowanie ich w zależności od otrzymanych parametrów w odebranym żądaniu HTTP.
 */
@Service
public final class LiquidCoolerService extends AbstractProductService<LiquidCooler> {

    /**
     * Repozytorium/DAO układu chłodzenia cieczą
     */
    private final LiquidCoolerRepository liquidCoolerRepository;

    /**
     * Konstruktor klasy serwisowej dla układu chłodzenia cieczą.
     * Wstrzykuje odpowiednie repozytoria do serwisu.
     *
     * @param productRepository      repozytorium produktu
     * @param liquidCoolerRepository repozytorium układu chłodzenia cieczą
     */
    public LiquidCoolerService(ProductRepository<LiquidCooler> productRepository, LiquidCoolerRepository liquidCoolerRepository) {
        super(productRepository);
        this.liquidCoolerRepository = liquidCoolerRepository;
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Odpowiada za pobieranie dostępnych filtrów dla układu chłodzenia cieczą.
     *
     * @param filter obiekt filtra produktu
     * @return Lista odfiltrowanych produktów
     */
    @Override
    public List<LiquidCooler> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.LIQUID_COOLER));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.LIQUID_COOLER);
        filterByProducers(ProductCategoryCode.LIQUID_COOLER, filter.getSelectedProducers());
        filterByListParam(((LiquidCoolerFilter) filter).getSelectedSockets(), liquidCoolerRepository::findBySocket);
        filterByListParam(((LiquidCoolerFilter) filter).getSelectedFans(), liquidCoolerRepository::findByFans);
        filterByListParam(((LiquidCoolerFilter) filter).getSelectedFanDiameters(), liquidCoolerRepository::findByFanDiameter);
        filterByListParam(((LiquidCoolerFilter) filter).getNoiseLevel(), liquidCoolerRepository::findByNoiseLevel);
        filterByBooleanParam(((LiquidCoolerFilter) filter).getLightning(), liquidCoolerRepository::findByLightning);
        filterByListParam(((LiquidCoolerFilter) filter).getSelectedCoolerSizes(), liquidCoolerRepository::findByCoolerSize);
        return applyFilters();
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Ustawia pola w klasie DTO filtra,
     * pobierając dane za pomocą kwerend zawartych w repozytorium produktu.
     *
     * @return Obiekt klasy {@link LiquidCoolerFilterResponse} zawierający informację o dostępnych filtrach
     */
    @Override
    @SuppressWarnings("unchecked")
    public LiquidCoolerFilterResponse getAvailableFilters() {
        LiquidCoolerFilterResponse response = new LiquidCoolerFilterResponse();
        response.setPriceMinimum(productRepository.getMinPriceFilter(ProductCategoryCode.LIQUID_COOLER));
        response.setPriceMaximum(productRepository.getMaxPriceFilter(ProductCategoryCode.LIQUID_COOLER));
        response.setProducers(productRepository.getProducerFilter(ProductCategoryCode.LIQUID_COOLER));
        response.setSocket(liquidCoolerRepository.getSocketFiler());
        response.setFans(liquidCoolerRepository.getNumOfFansFiler());
        response.setFanDiameters(liquidCoolerRepository.getFanDiameterFiler());
        response.setNoiseLevel(liquidCoolerRepository.getNoiseLvlFiler());
        response.setLightning(liquidCoolerRepository.getLightningFiler());
        response.setCoolerSize(liquidCoolerRepository.getCoolerSizeFiler());
        return response;
    }
}
