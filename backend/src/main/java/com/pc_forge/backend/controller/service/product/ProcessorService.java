package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.filter.ProcessorFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.entity.product.cpu.Processor;
import com.pc_forge.backend.model.repository.product.cpu.ProcessorRepository;
import com.pc_forge.backend.model.repository.product.ProductRepository;
import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.view.response.filter.ProcessorFilterResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa serwisu procesora. Dziedziczy z klasy {@link AbstractProductService},
 * przekazując klasę {@link Processor} jako typ produktu w klasie nadrzędnej. Jest odpowiedzialna za pobieranie
 * dostępnych filtrów i stosowanie ich w zależności od otrzymanych parametrów w odebranym żądaniu HTTP.
 */
@Getter
@Setter
@Service
public final class ProcessorService extends AbstractProductService<Processor> {

    /**
     * Repozytorium/DAO procesora
     */
    private final ProcessorRepository processorRepository;

    /**
     * Konstruktor klasy serwisowej dla procesora.
     * Wstrzykuje odpowiednie repozytoria do serwisu.
     *
     * @param productRepository   repozytorium produktu
     * @param processorRepository repozytorium procesora
     */
    public ProcessorService(ProductRepository<Processor> productRepository, ProcessorRepository processorRepository) {
        super(productRepository);
        this.processorRepository = processorRepository;
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Odpowiada za pobieranie dostępnych filtrów dla procesora.
     *
     * @param filter obiekt filtra produktu
     * @return Lista odfiltrowanych produktów
     */
    @Override
    public List<Processor> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.PROCESSOR));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.PROCESSOR);
        filterByProducers(ProductCategoryCode.PROCESSOR, filter.getSelectedProducers());
        filterByName(ProductCategoryCode.PROCESSOR, filter.getName());
        filterByListParam(((ProcessorFilter) filter).getSelectedSockets(), processorRepository::findBySocket_SocketName);
        filterByListParam(((ProcessorFilter) filter).getSelectedLines(), processorRepository::findByLine);
        filterByListParam(((ProcessorFilter) filter).getSelectedCores(), processorRepository::findByCores);
        filterByListParam(((ProcessorFilter) filter).getSelectedFrequencies(), processorRepository::findByFrequency);
        filterByListParam(((ProcessorFilter) filter).getSelectedGraphicsUnits(), processorRepository::findByIntegratedGraphicsUnit);
        filterByListParam(((ProcessorFilter) filter).getSelectedPackagingTypes(), processorRepository::findByPackaging);
        filterByBooleanParam(((ProcessorFilter) filter).getUnlocked(), processorRepository::findByUnlocked);
        filterByBooleanParam(((ProcessorFilter) filter).getCoolerIncluded(), processorRepository::findByCoolerIncluded);
        return applyFilters();
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Ustawia pola w klasie DTO filtra,
     * pobierając dane za pomocą kwerend zawartych w repozytorium produktu.
     *
     * @return Obiekt klasy {@link ProcessorFilterResponse} zawierający informację o dostępnych filtrach
     */
    @Override
    @SuppressWarnings("unchecked")
    public ProcessorFilterResponse getAvailableFilters() {
        ProcessorFilterResponse response = new ProcessorFilterResponse();
        response.setPriceMinimum(productRepository.getMinPriceFilter(ProductCategoryCode.PROCESSOR));
        response.setPriceMaximum(productRepository.getMaxPriceFilter(ProductCategoryCode.PROCESSOR));
        response.setProducers(productRepository.getProducerFilter(ProductCategoryCode.PROCESSOR));
        response.setSocket(processorRepository.getSocketFilter());
        response.setModel(processorRepository.getLineFilter());
        response.setNumberOfCores(processorRepository.getCoresFilter());
        response.setFrequency(processorRepository.getFrequencyFilter());
        response.setIntegratedGraphicsUnit(processorRepository.getIntegratedGraphicsUnitFilter());
        response.setCoolerIncluded(processorRepository.getCoolerIncludedFilter());
        response.setPackaging(processorRepository.getPackagingFilter());
        response.setCoreUnlocked(processorRepository.getUnlockedFilter());
        return response;
    }
}
