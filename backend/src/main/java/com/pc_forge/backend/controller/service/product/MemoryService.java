package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.MemoryFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.entity.product.ram.Memory;
import com.pc_forge.backend.model.repository.product.ram.MemoryRepository;
import com.pc_forge.backend.model.repository.product.ProductRepository;
import com.pc_forge.backend.view.response.filter.MemoryFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa serwisu pamięci operacyjnej RAM. Dziedziczy z klasy {@link AbstractProductService},
 * przekazując klasę {@link Memory} jako typ produktu w klasie nadrzędnej. Jest odpowiedzialna za pobieranie
 * dostępnych filtrów i stosowanie ich w zależności od otrzymanych parametrów w odebranym żądaniu HTTP.
 */
@Service
public final class MemoryService extends AbstractProductService<Memory> {

    /**
     * Repozytorium/DAO pamięci operacyjnej RAM
     */
    private final MemoryRepository memoryRepository;

    /**
     * Konstruktor klasy serwisowej dla pamięci operacyjnej RAM.
     * Wstrzykuje odpowiednie repozytoria do serwisu.
     *
     * @param productRepository repozytorium produktu
     * @param memoryRepository  repozytorium pamięci operacyjnej RAM
     */
    public MemoryService(ProductRepository<Memory> productRepository, MemoryRepository memoryRepository) {
        super(productRepository);
        this.memoryRepository = memoryRepository;
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Odpowiada za pobieranie dostępnych filtrów dla pamięci operacyjnej RAM.
     *
     * @param filter obiekt filtra produktu
     * @return Lista odfiltrowanych produktów
     */
    @Override
    public List<Memory> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.RAM));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.RAM);
        filterByName(ProductCategoryCode.RAM, filter.getName());
        filterByProducers(ProductCategoryCode.RAM, filter.getSelectedProducers());
        filterByListParam(((MemoryFilter) filter).getSelectedTypes(), memoryRepository::findByMemoryType);
        filterByListParam(((MemoryFilter) filter).getSelectedCapacities(), memoryRepository::findByTotalCapacity);
        filterByListParam(((MemoryFilter) filter).getSelectedFrequencies(), memoryRepository::findByFrequency);
        filterByListParam(((MemoryFilter) filter).getSelectedModules(), memoryRepository::findByNumberOfModules);
        filterByListParam(((MemoryFilter) filter).getSelectedLatencies(), memoryRepository::findByLatency);
        filterByBooleanParam(((MemoryFilter) filter).getLightning(), memoryRepository::findByLighting);
        return applyFilters();
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Ustawia pola w klasie DTO filtra,
     * pobierając dane za pomocą kwerend zawartych w repozytorium produktu.
     *
     * @return Obiekt klasy {@link MemoryFilterResponse} zawierający informację o dostępnych filtrach
     */
    @Override
    @SuppressWarnings("unchecked")
    public MemoryFilterResponse getAvailableFilters() {
        MemoryFilterResponse response = new MemoryFilterResponse();
        response.setPriceMinimum(productRepository.getMinPriceFilter(ProductCategoryCode.RAM));
        response.setPriceMaximum(productRepository.getMaxPriceFilter(ProductCategoryCode.RAM));
        response.setProducers(productRepository.getProducerFilter(ProductCategoryCode.RAM));
        response.setMemoryType(memoryRepository.getMemoryTypeFilter());
        response.setTotalCapacity(memoryRepository.getTotalCapacityFilter());
        response.setFrequency(memoryRepository.getFrequencyFilter());
        response.setNumberOfModules(memoryRepository.getNumberOfModulesFilter());
        response.setLatency(memoryRepository.getLatencyFilter());
        response.setLightning(memoryRepository.getLightningFilter());
        return response;
    }
}
