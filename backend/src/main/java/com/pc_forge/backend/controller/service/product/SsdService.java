package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.controller.filter.SsdFilter;
import com.pc_forge.backend.model.product.SolidStateDrive;
import com.pc_forge.backend.model.product.repository.ProductRepository;
import com.pc_forge.backend.model.product.repository.SolidStateDriveRepository;
import com.pc_forge.backend.view.response.filter.SsdFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class SsdService extends AbstractProductService<SolidStateDrive> {
    private final SolidStateDriveRepository ssdRepository;

    public SsdService(ProductRepository<SolidStateDrive> productRepository, SolidStateDriveRepository ssdRepository) {
        super(productRepository);
        this.ssdRepository = ssdRepository;
    }

    @Override
    public List<SolidStateDrive> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.SSD));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.SSD);
        filterByProducers(ProductCategoryCode.SSD, filter.getSelectedProducers());
        filterByListParam(((SsdFilter) filter).getSelectedStorages(), ssdRepository::findByStorage);
        filterByListParam(((SsdFilter) filter).getSelectedFormats(), ssdRepository::findByDriveFormat);
        filterByListParam(((SsdFilter) filter).getSelectedInterfaces(), ssdRepository::findByDriveInterface);
        filterByListParam(((SsdFilter) filter).getSelectedReadSpeeds(), ssdRepository::findByReadSpeed);
        filterByListParam(((SsdFilter) filter).getSelectedWriteSpeeds(), ssdRepository::findByWriteSpeed);
        return applyFilters();
    }

    @Override
    @SuppressWarnings("unchecked")
    public SsdFilterResponse getAvailableFilters() {
        SsdFilterResponse response = new SsdFilterResponse();
        response.setPriceMinimum(productRepository.getMinPriceFilter(ProductCategoryCode.SSD));
        response.setPriceMaximum(productRepository.getMaxPriceFilter(ProductCategoryCode.SSD));
        response.setProducers(productRepository.getProducerFilter(ProductCategoryCode.SSD));
        response.setFormat(ssdRepository.getFormatFilter());
        response.setDriveInterface(ssdRepository.getInterfaceFilter());
        response.setStorage(ssdRepository.getStorageFilter());
        response.setReadSpeed(ssdRepository.getReadSpeedFilter());
        response.setWriteSpeed(ssdRepository.getWriteSpeedFilter());
        return response;
    }
}
