package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.filter.ProcessorFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.product.Processor;
import com.pc_forge.backend.model.product.repository.ProcessorRepository;
import com.pc_forge.backend.model.product.repository.ProductRepository;
import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.view.response.filter.ProcessorFilterResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public final class ProcessorService extends AbstractProductService<Processor> {
    private final ProcessorRepository processorRepository;

    public ProcessorService(ProductRepository<Processor> productRepository, ProcessorRepository processorRepository) {
        super(productRepository);
        this.processorRepository = processorRepository;
    }

    @Override
    public List<Processor> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.PROCESSOR));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.PROCESSOR);
        filterByProducers(ProductCategoryCode.PROCESSOR, filter.getSelectedProducers());
        filterByListParam(((ProcessorFilter) filter).getSelectedSockets(), processorRepository::findBySocket_Socket);
        filterByListParam(((ProcessorFilter) filter).getSelectedLines(), processorRepository::findByLine);
        filterByListParam(((ProcessorFilter) filter).getSelectedCores(), processorRepository::findByCores);
        filterByListParam(((ProcessorFilter) filter).getSelectedFrequencies(), processorRepository::findByFrequency);
        filterByListParam(((ProcessorFilter) filter).getSelectedGraphicsUnits(), processorRepository::findByIntegratedGraphicsUnit);
        filterByListParam(((ProcessorFilter) filter).getSelectedPackagingTypes(), processorRepository::findByPackaging);
        filterByBooleanParam(((ProcessorFilter) filter).getUnlocked(), processorRepository::findByUnlocked);
        filterByBooleanParam(((ProcessorFilter) filter).getCoolerIncluded(), processorRepository::findByCoolerIncluded);
        return applyFilters();
    }

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
