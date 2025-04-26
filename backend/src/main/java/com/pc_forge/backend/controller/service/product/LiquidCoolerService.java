package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.LiquidCoolerFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.product.LiquidCooler;
import com.pc_forge.backend.model.product.repository.LiquidCoolerRepository;
import com.pc_forge.backend.model.product.repository.ProductRepository;
import com.pc_forge.backend.view.response.filter.LiquidCoolerFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class LiquidCoolerService extends AbstractProductService<LiquidCooler> {

    private final LiquidCoolerRepository liquidCoolerRepository;

    public LiquidCoolerService(ProductRepository<LiquidCooler> productRepository, LiquidCoolerRepository repository, LiquidCoolerRepository liquidCoolerRepository) {
        super(productRepository);
        this.liquidCoolerRepository = liquidCoolerRepository;
    }

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
