package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.HddFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.entity.product.drive.HardDiskDrive;
import com.pc_forge.backend.model.repository.product.drive.HardDiskDriveRepository;
import com.pc_forge.backend.model.repository.product.ProductRepository;
import com.pc_forge.backend.view.response.filter.HddFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa serwisu dysku HDD. Dziedziczy z klasy {@link AbstractProductService},
 * przekazując klasę {@link HardDiskDrive} jako typ produktu w klasie nadrzędnej. Jest odpowiedzialna za pobieranie
 * dostępnych filtrów i stosowanie ich w zależności od otrzymanych parametrów w odebranym żądaniu HTTP.
 */
@Service
public final class HddService extends AbstractProductService<HardDiskDrive> {
    /**
     * Repozytorium/DAO dysku HDD
     */
    private final HardDiskDriveRepository hddRepository;

    /**
     * Konstruktor klasy serwisowej dla dysku HDD.
     * Wstrzykuje odpowiednie repozytoria do serwisu.
     *
     * @param productRepository repozytorium produktu
     * @param hddRepository     repozytorium dysku HDD
     */
    public HddService(ProductRepository<HardDiskDrive> productRepository, HardDiskDriveRepository hddRepository) {
        super(productRepository);
        this.hddRepository = hddRepository;
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Odpowiada za pobieranie dostępnych filtrów dla dysku HDD.
     *
     * @param filter obiekt filtra produktu
     * @return Lista odfiltrowanych produktów
     */
    @Override
    public List<HardDiskDrive> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.HDD));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.HDD);
        filterByProducers(ProductCategoryCode.HDD, filter.getSelectedProducers());
        filterByListParam(((HddFilter) filter).getSelectedStorages(), hddRepository::findByStorage);
        filterByListParam(((HddFilter) filter).getSelectedFormats(), hddRepository::findByDriveFormat);
        filterByListParam(((HddFilter) filter).getSelectedInterfaces(), hddRepository::findByDriveInterface);
        filterByListParam(((HddFilter) filter).getSelectedRotationalSpeeds(), hddRepository::findByRotationalSpeed);
        return applyFilters();
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Ustawia pola w klasie DTO filtra,
     * pobierając dane za pomocą kwerend zawartych w repozytorium produktu.
     *
     * @return Obiekt klasy {@link HddFilterResponse} zawierający informację o dostępnych filtrach
     */
    @Override
    @SuppressWarnings("unchecked")
    public HddFilterResponse getAvailableFilters() {
        HddFilterResponse response = new HddFilterResponse();
        response.setPriceMinimum(productRepository.getMinPriceFilter(ProductCategoryCode.HDD));
        response.setPriceMaximum(productRepository.getMaxPriceFilter(ProductCategoryCode.HDD));
        response.setProducers(productRepository.getProducerFilter(ProductCategoryCode.HDD));
        response.setFormat(hddRepository.getFormatFilter());
        response.setDriveInterface(hddRepository.getInterfaceFilter());
        response.setStorage(hddRepository.getStorageFilter());
        response.setRotationalSpeed(hddRepository.getReadSpeedFilter());
        return response;
    }
}
