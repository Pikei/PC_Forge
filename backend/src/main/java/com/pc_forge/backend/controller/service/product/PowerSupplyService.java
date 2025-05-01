package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.PowerSupplyFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.entity.product.ps.PowerSupply;
import com.pc_forge.backend.model.repository.product.ps.PowerSupplyRepository;
import com.pc_forge.backend.model.repository.product.ProductRepository;
import com.pc_forge.backend.view.response.filter.PsFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class PowerSupplyService extends AbstractProductService<PowerSupply> {

    private final PowerSupplyRepository powerSupplyRepository;

    public PowerSupplyService(ProductRepository<PowerSupply> productRepository, PowerSupplyRepository powerSupplyRepository) {
        super(productRepository);
        this.powerSupplyRepository = powerSupplyRepository;
    }

    @Override
    public List<PowerSupply> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.POWER_SUPPLY));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.POWER_SUPPLY);
        filterByProducers(ProductCategoryCode.POWER_SUPPLY, filter.getSelectedProducers());
        filterByListParam(((PowerSupplyFilter) filter).getSelectedPowers(), powerSupplyRepository::findByPower);
        filterByListParam(((PowerSupplyFilter) filter).getSelectedCertificates(), powerSupplyRepository::findByEfficiencyCertificate);
        filterByListParam(((PowerSupplyFilter) filter).getSelectedEfficiencies(), powerSupplyRepository::findByEfficiency);
        filterByListParam(((PowerSupplyFilter) filter).getSelectedCoolingTypes(), powerSupplyRepository::findByCoolingType);
        filterByListParam(((PowerSupplyFilter) filter).getSelectedFanDiameters(), powerSupplyRepository::findByFanDiameter);
        filterByListParam(((PowerSupplyFilter) filter).getSelectedProtections(), powerSupplyRepository::findByProtection);
        filterByBooleanParam(((PowerSupplyFilter) filter).getModularCabling(), powerSupplyRepository::findByModularCabling);
        filterByBooleanParam(((PowerSupplyFilter) filter).getLightning(), powerSupplyRepository::findByLightning);
        return applyFilters();

    }

    @Override
    @SuppressWarnings("unchecked")
    public PsFilterResponse getAvailableFilters() {
        PsFilterResponse response = new PsFilterResponse();
        response.setPriceMinimum(productRepository.getMinPriceFilter(ProductCategoryCode.POWER_SUPPLY));
        response.setPriceMaximum(productRepository.getMaxPriceFilter(ProductCategoryCode.POWER_SUPPLY));
        response.setProducers(productRepository.getProducerFilter(ProductCategoryCode.POWER_SUPPLY));
        response.setPower(powerSupplyRepository.getPowerFilter());
        response.setEfficiencyCertificate(powerSupplyRepository.getEfficiencyCertificateFilter());
        response.setEfficiency(powerSupplyRepository.getEfficiencyFilter());
        response.setProtections(powerSupplyRepository.getProtectionFilter());
        response.setCoolingType(powerSupplyRepository.getCoolingTypeFilter());
        response.setFanDiameter(powerSupplyRepository.getFanDiameterFilter());
        response.setModularCabling(powerSupplyRepository.getModularCablingFilter());
        response.setLightning(powerSupplyRepository.getLightningFilter());
        return response;
    }
}
