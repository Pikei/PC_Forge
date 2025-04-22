package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.GpuFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.database.product.GraphicsCard;
import com.pc_forge.backend.model.database.product.repository.GraphicsCardRepository;
import com.pc_forge.backend.model.database.product.repository.ProductRepository;
import com.pc_forge.backend.view.api.request.response.filter.GpuFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class GraphicsCardService extends AbstractProductService<GraphicsCard> {
    private final GraphicsCardRepository graphicsCardRepository;

    public GraphicsCardService(ProductRepository<GraphicsCard> productRepository, GraphicsCardRepository repository, GraphicsCardRepository graphicsCardRepository) {
        super(productRepository);
        this.graphicsCardRepository = graphicsCardRepository;
    }

    @Override
    public List<GraphicsCard> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.GRAPHICS_CARD.getCode()));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.GRAPHICS_CARD.getCode());
        filterByProducers(ProductCategoryCode.GRAPHICS_CARD.getCode(), filter.getSelectedProducers());
        filterByListParam(((GpuFilter) filter).getSelectedChipsetProducers(), graphicsCardRepository::findByChipsetProducer);
        filterByListParam(((GpuFilter) filter).getSelectedChipsets(), graphicsCardRepository::findByChipset);
        filterByListParam(((GpuFilter) filter).getSelectedRamCapacities(), graphicsCardRepository::findByRam);
        filterByListParam(((GpuFilter) filter).getSelectedRamTypes(), graphicsCardRepository::findByRamType);
        filterByListParam(((GpuFilter) filter).getSelectedDlls(), graphicsCardRepository::findByDlss);
        filterByListParam(((GpuFilter) filter).getSelectedConnectors(), graphicsCardRepository::findByConnector);
        filterByListParam(((GpuFilter) filter).getSelectedLengths(), graphicsCardRepository::findByCardLength);
        filterByListParam(((GpuFilter) filter).getSelectedResolutions(), graphicsCardRepository::findByResolution);
        filterByListParam(((GpuFilter) filter).getSelectedRecommendedPs(), graphicsCardRepository::findByRecommendedPsPower);
        filterByListParam(((GpuFilter) filter).getSelectedCoolingTypes(), graphicsCardRepository::findByCoolingType);
        filterByListParam(((GpuFilter) filter).getSelectedFans(), graphicsCardRepository::findByNumberOfFans);
        filterByBooleanParam(((GpuFilter) filter).getLightning(), graphicsCardRepository::findByLightning);
        return applyFilters();
    }

    @Override
    @SuppressWarnings("unchecked")
    public GpuFilterResponse getAvailableFilters() {
        GpuFilterResponse response = new GpuFilterResponse();
        response.setPriceMinimum(productRepository.getMinPriceFilter(ProductCategoryCode.GRAPHICS_CARD.getCode()));
        response.setPriceMaximum(productRepository.getMaxPriceFilter(ProductCategoryCode.GRAPHICS_CARD.getCode()));
        response.setProducers(productRepository.getProducerFilter(ProductCategoryCode.GRAPHICS_CARD.getCode()));
        response.setChipsetProducer(graphicsCardRepository.getChipsetProducerFilter());
        response.setChipset(graphicsCardRepository.getChipsetFilter());
        response.setRam(graphicsCardRepository.getRamFilter());
        response.setRamType(graphicsCardRepository.getRamTypeFilter());
        response.setDlss(graphicsCardRepository.getDlssFilter());
        response.setConnector(graphicsCardRepository.getConnectorFilter());
        response.setCardLength(graphicsCardRepository.getLengthFilter());
        response.setResolution(graphicsCardRepository.getResolutionFilter());
        response.setRecommendedPsPower(graphicsCardRepository.getPsPowerFilter());
        response.setCoolingType(graphicsCardRepository.getCoolingTypeFilter());
        response.setCoolingType(graphicsCardRepository.getCoolingTypeFilter());
        response.setLightning(graphicsCardRepository.getLightningFilter());
        return null;
    }
}
