package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.filter.MotherboardFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.product.Motherboard;
import com.pc_forge.backend.model.product.repository.MotherboardRepository;
import com.pc_forge.backend.model.product.repository.ProductRepository;
import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.view.response.filter.MotherboardFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class MotherboardService extends AbstractProductService<Motherboard> {
    private final MotherboardRepository motherboardRepository;

    public MotherboardService(ProductRepository<Motherboard> productRepository, MotherboardRepository motherboardRepository) {
        super(productRepository);
        this.motherboardRepository = motherboardRepository;
    }

    @Override
    public List<Motherboard> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.MOTHERBOARD));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.MOTHERBOARD);
        filterByProducers(ProductCategoryCode.MOTHERBOARD, filter.getSelectedProducers());
        filterByListParam(((MotherboardFilter) filter).getSelectedSockets(), motherboardRepository::findBySocket_SocketName);
        filterByListParam(((MotherboardFilter) filter).getSelectedStandards(), motherboardRepository::findByStandard_StandardName);
        filterByListParam(((MotherboardFilter) filter).getSelectedChipsets(), motherboardRepository::findByChipset);
        filterByListParam(((MotherboardFilter) filter).getSelectedMemoryStandards(), motherboardRepository::findByMemoryStandard);
        filterByListParam(((MotherboardFilter) filter).getSelectedMemorySlots(), motherboardRepository::findByMemorySlots);
        filterByListParam(((MotherboardFilter) filter).getSelectedMaxMemoryCapacity(), motherboardRepository::findByMaxMemoryCapacity);
        filterByListParam(((MotherboardFilter) filter).getSelectedFrequencies(), motherboardRepository::findBySupportedMemoryFrequency);
        filterByBooleanParam(((MotherboardFilter) filter).getBluetooth(), motherboardRepository::findByBluetooth);
        filterByBooleanParam(((MotherboardFilter) filter).getWifi(), motherboardRepository::findByWifi);
        filterByWidth(((MotherboardFilter) filter).getMinWidth(), ((MotherboardFilter) filter).getMaxWidth());
        filterByDepth(((MotherboardFilter) filter).getMinDepth(), ((MotherboardFilter) filter).getMaxDepth());
        return applyFilters();
    }

    @Override
    @SuppressWarnings("unchecked")
    public MotherboardFilterResponse getAvailableFilters() {
        MotherboardFilterResponse response = new MotherboardFilterResponse();
        response.setPriceMinimum(productRepository.getMinPriceFilter(ProductCategoryCode.MOTHERBOARD));
        response.setPriceMaximum(productRepository.getMaxPriceFilter(ProductCategoryCode.MOTHERBOARD));
        response.setProducers(productRepository.getProducerFilter(ProductCategoryCode.MOTHERBOARD));
        response.setSocket(motherboardRepository.getSocketFilter());
        response.setStandard(motherboardRepository.getStandardFilter());
        response.setChipset(motherboardRepository.getChipsetFilter());
        response.setMemoryStandard(motherboardRepository.getMemoryStandardFilter());
        response.setMemorySlot(motherboardRepository.getMemorySlotFilter());
        response.setMaxMemory(motherboardRepository.getMaxMemoryFilter());
        response.setFrequency(motherboardRepository.getFrequencyFilter());
        response.setBluetooth(motherboardRepository.getBluetoothFilter());
        response.setWifi(motherboardRepository.getWiFiFilter());
        response.setMinWidth(motherboardRepository.getMinWidth());
        response.setMaxWidth(motherboardRepository.getMaxWidth());
        response.setMinDepth(motherboardRepository.getMinDepth());
        response.setMaxDepth(motherboardRepository.getMaxDepth());
        return response;
    }

    private void filterByWidth(Double min, Double max) {
        if (min == null) {
            min = motherboardRepository.getMinWidth();
        }
        if (max == null) {
            max = motherboardRepository.getMaxWidth();
        }
        filteredProducts.add(motherboardRepository.findByWidthBetween(min, max));
    }

    private void filterByDepth(Double min, Double max) {
        if (min == null) {
            min = motherboardRepository.getMinDepth();
        }
        if (max == null) {
            max = motherboardRepository.getMaxDepth();
        }
        filteredProducts.add(motherboardRepository.findByDepthBetween(min, max));
    }

}
