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

/**
 * Klasa serwisu zasilacza. Dziedziczy z klasy {@link AbstractProductService},
 * przekazując klasę {@link PowerSupply} jako typ produktu w klasie nadrzędnej. Jest odpowiedzialna za pobieranie
 * dostępnych filtrów i stosowanie ich w zależności od otrzymanych parametrów w odebranym żądaniu HTTP.
 */
@Service
public final class PowerSupplyService extends AbstractProductService<PowerSupply> {

    /**
     * Repozytorium/DAO zasilacza
     */
    private final PowerSupplyRepository powerSupplyRepository;

    /**
     * Konstruktor klasy serwisowej dla zasilacza.
     * Wstrzykuje odpowiednie repozytoria do serwisu.
     *
     * @param productRepository     repozytorium produktu
     * @param powerSupplyRepository repozytorium zasilacza
     */
    public PowerSupplyService(ProductRepository<PowerSupply> productRepository, PowerSupplyRepository powerSupplyRepository) {
        super(productRepository);
        this.powerSupplyRepository = powerSupplyRepository;
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Odpowiada za pobieranie dostępnych filtrów dla zasilacza.
     *
     * @param filter obiekt filtra produktu
     * @return Lista odfiltrowanych produktów
     */
    @Override
    public List<PowerSupply> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.POWER_SUPPLY));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.POWER_SUPPLY);
        filterByProducers(ProductCategoryCode.POWER_SUPPLY, filter.getSelectedProducers());
        filterByName(ProductCategoryCode.POWER_SUPPLY, filter.getName());
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

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Ustawia pola w klasie DTO filtra,
     * pobierając dane za pomocą kwerend zawartych w repozytorium produktu.
     *
     * @return Obiekt klasy {@link PsFilterResponse} zawierający informację o dostępnych filtrach
     */
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
