package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.filter.ProcessorFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.database.product.Product;
import com.pc_forge.backend.model.database.product.repository.ProcessorRepository;
import com.pc_forge.backend.model.database.product.repository.ProductRepository;
import com.pc_forge.backend.view.api.ProductCategoryCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class ProcessorService extends ProductService {
    private final ProcessorRepository processorRepository;

    public ProcessorService(ProductRepository productRepository, ProcessorRepository processorRepository) {
        super(productRepository);
        this.processorRepository = processorRepository;
    }

    @Override
    public List<Product> getFilteredProducts(ProductFilter filter) {
        filteredProducts = new ArrayList<>();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.PROCESSOR.getCode()));
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.PROCESSOR.getCode());
        filterByProducers(filter.getSelectedProducers());
        filterBySockets(((ProcessorFilter) filter).getSelectedSockets());
        filterByLines(((ProcessorFilter) filter).getSelectedLines());
        filterByCores(((ProcessorFilter) filter).getSelectedCores());
        filterByFrequencies(((ProcessorFilter) filter).getSelectedFrequencies());
        filterByGraphicsUnits(((ProcessorFilter) filter).getSelectedGraphicsUnits());
        filterByPackagingTypes(((ProcessorFilter) filter).getSelectedPackagingTypes());
        filterByUnlocked(filter);
        filterByCooler(filter);
        return applyFilters();
    }


    private void filterByProducers(List<String> producers) {
        if (producers != null && !producers.isEmpty()) {
            List<Product> filteredByProducers = new ArrayList<>();
            for (String producer : producers) {
                filteredByProducers.addAll(getProductsInCategoryByProducers(ProductCategoryCode.PROCESSOR.getCode(), producer));
            }
            filteredProducts.add(filteredByProducers);
        }
    }

    private void filterBySockets(List<String> selectedSockets) {
        if (selectedSockets != null && !selectedSockets.isEmpty()) {
            List<Product> filteredBySockets = new ArrayList<>();
            for (String socket : selectedSockets) {
                filteredBySockets.addAll(processorRepository.findBySocket_Socket(socket));
            }
            filteredProducts.add(filteredBySockets);
        }
    }

    private void filterByLines(List<String> selectedLines) {
        if (selectedLines != null && !selectedLines.isEmpty()) {
            List<Product> filteredByLines = new ArrayList<>();
            for (String line : selectedLines) {
                filteredByLines.addAll(processorRepository.findByLine(line));
            }
            filteredProducts.add(filteredByLines);
        }
    }

    private void filterByCores(List<Integer> selectedCores) {
        if (selectedCores != null && !selectedCores.isEmpty()) {
            List<Product> filteredByCores = new ArrayList<>();
            for (Integer numOfCores : selectedCores) {
                filteredByCores.addAll(processorRepository.findByCores(numOfCores));
            }
            filteredProducts.add(filteredByCores);
        }
    }

    private void filterByFrequencies(List<Double> selectedFrequencies) {
        if (selectedFrequencies != null && !selectedFrequencies.isEmpty()) {
            List<Product> filteredByFrequencies = new ArrayList<>();
            for (Double frequency : selectedFrequencies) {
                filteredByFrequencies.addAll(processorRepository.findByFrequency(frequency));
            }
            filteredProducts.add(filteredByFrequencies);
        }
    }

    private void filterByGraphicsUnits(List<String> selectedGraphicsUnits) {
        if (selectedGraphicsUnits != null && !selectedGraphicsUnits.isEmpty()) {
            List<Product> filteredByGraphicsUnits = new ArrayList<>();
            for (String igu : selectedGraphicsUnits) {
                filteredByGraphicsUnits.addAll(processorRepository.findByIntegratedGraphicsUnit(igu));
            }
            filteredProducts.add(filteredByGraphicsUnits);
        }
    }

    private void filterByPackagingTypes(List<String> selectedPackagingTypes) {
        if (selectedPackagingTypes != null && !selectedPackagingTypes.isEmpty()) {
            for (String pack : selectedPackagingTypes) {
                filteredProducts.add(processorRepository.findByPackaging(pack));
            }
        }
    }

    private void filterByUnlocked(ProductFilter filter) {
        if (((ProcessorFilter) filter).getUnlocked() != null) {
            filteredProducts.add(processorRepository.findByUnlocked(((ProcessorFilter) filter).getUnlocked()));
        }
    }

    private void filterByCooler(ProductFilter filter) {
        if (((ProcessorFilter) filter).getCoolerIncluded() != null) {
            filteredProducts.add(processorRepository.findByCoolerIncluded(((ProcessorFilter) filter).getCoolerIncluded()));
        }
    }

}
