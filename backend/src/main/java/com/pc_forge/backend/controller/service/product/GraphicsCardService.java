package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.GpuFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.entity.product.gpu.GraphicsCard;
import com.pc_forge.backend.model.repository.product.gpu.GraphicsCardRepository;
import com.pc_forge.backend.model.repository.product.ProductRepository;
import com.pc_forge.backend.view.response.filter.GpuFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa serwisu karty graficznej. Dziedziczy z klasy {@link AbstractProductService},
 * przekazując klasę {@link GraphicsCardService} jako typ produktu w klasie nadrzędnej. Jest odpowiedzialna za pobieranie
 * dostępnych filtrów i stosowanie ich w zależności od otrzymanych parametrów w odebranym żądaniu HTTP.
 */
@Service
public final class GraphicsCardService extends AbstractProductService<GraphicsCard> {

    /**
     * Repozytorium/DAO karty graficznej
     */
    private final GraphicsCardRepository graphicsCardRepository;

    /**
     * Konstruktor klasy serwisowej dla karty graficznej.
     * Wstrzykuje odpowiednie repozytoria do serwisu.
     *
     * @param productRepository      repozytorium produktu
     * @param graphicsCardRepository repozytorium karty graficznej
     */
    public GraphicsCardService(ProductRepository<GraphicsCard> productRepository, GraphicsCardRepository graphicsCardRepository) {
        super(productRepository);
        this.graphicsCardRepository = graphicsCardRepository;
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Odpowiada za pobieranie dostępnych filtrów dla karty graficznej.
     *
     * @param filter obiekt filtra produktu
     * @return Lista odfiltrowanych produktów
     */
    @Override
    public List<GraphicsCard> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.GRAPHICS_CARD));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.GRAPHICS_CARD);
        filterByProducers(ProductCategoryCode.GRAPHICS_CARD, filter.getSelectedProducers());
        filterByListParam(((GpuFilter) filter).getSelectedChipsetProducers(), graphicsCardRepository::findByChipsetProducer);
        filterByListParam(((GpuFilter) filter).getSelectedChipsets(), graphicsCardRepository::findByChipset);
        filterByListParam(((GpuFilter) filter).getSelectedRamCapacities(), graphicsCardRepository::findByRam);
        filterByListParam(((GpuFilter) filter).getSelectedRamTypes(), graphicsCardRepository::findByRamType);
        filterByListParam(((GpuFilter) filter).getSelectedDlls(), graphicsCardRepository::findByDlss);
        filterByListParam(((GpuFilter) filter).getSelectedConnectors(), graphicsCardRepository::findByConnector);
        filterByLength(graphicsCardRepository.getMinLengthFilter(), graphicsCardRepository.getMaxLengthFilter());
        filterByListParam(((GpuFilter) filter).getSelectedResolutions(), graphicsCardRepository::findByResolution);
        filterByListParam(((GpuFilter) filter).getSelectedRecommendedPs(), graphicsCardRepository::findByRecommendedPsPower);
        filterByListParam(((GpuFilter) filter).getSelectedCoolingTypes(), graphicsCardRepository::findByCoolingType);
        filterByListParam(((GpuFilter) filter).getSelectedFans(), graphicsCardRepository::findByNumberOfFans);
        filterByBooleanParam(((GpuFilter) filter).getLightning(), graphicsCardRepository::findByLightning);
        return applyFilters();
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Ustawia pola w klasie DTO filtra,
     * pobierając dane za pomocą kwerend zawartych w repozytorium produktu.
     *
     * @return Obiekt klasy {@link GpuFilterResponse} zawierający informację o dostępnych filtrach
     */
    @Override
    @SuppressWarnings("unchecked")
    public GpuFilterResponse getAvailableFilters() {
        GpuFilterResponse response = new GpuFilterResponse();
        response.setPriceMinimum(productRepository.getMinPriceFilter(ProductCategoryCode.GRAPHICS_CARD));
        response.setPriceMaximum(productRepository.getMaxPriceFilter(ProductCategoryCode.GRAPHICS_CARD));
        response.setProducers(productRepository.getProducerFilter(ProductCategoryCode.GRAPHICS_CARD));
        response.setChipsetProducer(graphicsCardRepository.getChipsetProducerFilter());
        response.setChipset(graphicsCardRepository.getChipsetFilter());
        response.setRam(graphicsCardRepository.getRamFilter());
        response.setRamType(graphicsCardRepository.getRamTypeFilter());
        response.setDlss(graphicsCardRepository.getDlssFilter());
        response.setConnector(graphicsCardRepository.getConnectorFilter());
        response.setResolution(graphicsCardRepository.getResolutionFilter());
        response.setRecommendedPsPower(graphicsCardRepository.getPsPowerFilter());
        response.setCoolingType(graphicsCardRepository.getCoolingTypeFilter());
        response.setCoolingType(graphicsCardRepository.getCoolingTypeFilter());
        response.setNumberOfFans(graphicsCardRepository.getNumOfFansFilter());
        response.setLightning(graphicsCardRepository.getLightningFilter());
        response.setLengthMinimum(graphicsCardRepository.getMinLengthFilter());
        response.setLengthMaximum(graphicsCardRepository.getMaxLengthFilter());
        return response;
    }

    /**
     * Metoda filtruje produkty po długości, pobierając wartości pomiędzy przekazanym minimum a maksimum.
     *
     * @param minLength Długość minimalna odebrana z parametru HTTP.
     *                  W przypadku gdy nie podano długości minimalnej, ustawiana jest największa długość.
     * @param maxLength Długość minimalna maksymalna odebrana z parametru HTTP.
     *                  W przypadku gdy nie podano długości maksymalnej, ustawiana jest największa długość.
     */
    private void filterByLength(Integer minLength, Integer maxLength) {
        if (minLength == null) {
            minLength = graphicsCardRepository.getMinLengthFilter();
        }
        if (maxLength == null) {
            maxLength = graphicsCardRepository.getMaxLengthFilter();
        }
        filteredProducts.add(graphicsCardRepository.findByCardLengthBetween(minLength, maxLength));
    }
}
