package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.AirCoolerFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.entity.product.cooler.AirCooler;
import com.pc_forge.backend.model.repository.product.cooler.AirCoolerRepository;
import com.pc_forge.backend.model.repository.product.ProductRepository;
import com.pc_forge.backend.view.response.filter.AirCoolerFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class AirCoolerService extends AbstractProductService<AirCooler> {

    private final AirCoolerRepository airCoolerRepository;

    public AirCoolerService(ProductRepository<AirCooler> productRepository, AirCoolerRepository airCoolerRepository) {
        super(productRepository);
        this.airCoolerRepository = airCoolerRepository;
    }

    @Override
    public List<AirCooler> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.AIR_COOLER));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.AIR_COOLER);
        filterByProducers(ProductCategoryCode.AIR_COOLER, filter.getSelectedProducers());
        filterByListParam(((AirCoolerFilter) filter).getSelectedSockets(), airCoolerRepository::findBySocket);
        filterByListParam(((AirCoolerFilter) filter).getSelectedFans(), airCoolerRepository::findByFans);
        filterByListParam(((AirCoolerFilter) filter).getSelectedFanDiameters(), airCoolerRepository::findByFanDiameter);
        filterByListParam(((AirCoolerFilter) filter).getNoiseLevel(), airCoolerRepository::findByNoiseLevel);
        filterByBooleanParam(((AirCoolerFilter) filter).getLightning(), airCoolerRepository::findByLightning);
        filterByListParam(((AirCoolerFilter) filter).getSelectedBaseMaterials(), airCoolerRepository::findByBaseMaterial);
        filterByWidth(((AirCoolerFilter) filter).getMinWidth(), ((AirCoolerFilter) filter).getMaxWidth());
        filterByHeight(((AirCoolerFilter) filter).getMinHeight(), ((AirCoolerFilter) filter).getMaxHeight());
        filterByDepth(((AirCoolerFilter) filter).getMinDepth(), ((AirCoolerFilter) filter).getMaxDepth());
        filterByBooleanParam(((AirCoolerFilter) filter).getVerticalInstallation(), airCoolerRepository::findByVerticalInstallation);
        return applyFilters();
    }

    @Override
    @SuppressWarnings("unchecked")
    public AirCoolerFilterResponse getAvailableFilters() {
        AirCoolerFilterResponse response = new AirCoolerFilterResponse();
        response.setPriceMinimum(productRepository.getMinPriceFilter(ProductCategoryCode.AIR_COOLER));
        response.setPriceMaximum(productRepository.getMaxPriceFilter(ProductCategoryCode.AIR_COOLER));
        response.setProducers(productRepository.getProducerFilter(ProductCategoryCode.AIR_COOLER));
        response.setSocket(airCoolerRepository.getSocketFiler());
        response.setFans(airCoolerRepository.getNumOfFansFiler());
        response.setFanDiameters(airCoolerRepository.getFanDiameterFiler());
        response.setNoiseLevel(airCoolerRepository.getNoiseLvlFiler());
        response.setLightning(airCoolerRepository.getLightningFiler());
        response.setBaseMaterial(airCoolerRepository.getBaseMaterialFiler());
        response.setMinWidth(airCoolerRepository.getMinWidth());
        response.setMaxWidth(airCoolerRepository.getMaxWidth());
        response.setMinHeight(airCoolerRepository.getMinHeight());
        response.setMaxHeight(airCoolerRepository.getMaxHeight());
        response.setMinDepth(airCoolerRepository.getMinDepth());
        response.setMaxDepth(airCoolerRepository.getMaxDepth());
        response.setVerticalInstallation(airCoolerRepository.getVerticalInstallationFiler());
        return response;
    }

    private void filterByWidth(Integer min, Integer max) {
        if (min == null && max == null) {
            return;
        }
        if (min == null) {
            min = airCoolerRepository.getMinWidth();
        }
        if (max == null) {
            max = airCoolerRepository.getMaxWidth();
        }
        filteredProducts.add(airCoolerRepository.findByWidthBetween(min, max));
    }

    private void filterByHeight(Integer min, Integer max) {
        if (min == null && max == null) {
            return;
        }
        if (min == null) {
            min = airCoolerRepository.getMinHeight();
        }
        if (max == null) {
            max = airCoolerRepository.getMaxHeight();
        }
        filteredProducts.add(airCoolerRepository.findByHeightBetween(min, max));
    }

    private void filterByDepth(Integer min, Integer max) {
        if (min == null && max == null) {
            return;
        }
        if (min == null) {
            min = airCoolerRepository.getMinDepth();
        }
        if (max == null) {
            max = airCoolerRepository.getMaxDepth();
        }
        filteredProducts.add(airCoolerRepository.findByDepthBetween(min, max));
    }
}
