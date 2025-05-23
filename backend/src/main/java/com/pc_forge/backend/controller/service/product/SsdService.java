package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.controller.filter.SsdFilter;
import com.pc_forge.backend.model.entity.product.cpu.Processor;
import com.pc_forge.backend.model.entity.product.drive.SolidStateDrive;
import com.pc_forge.backend.model.repository.product.ProductRepository;
import com.pc_forge.backend.model.repository.product.drive.SolidStateDriveRepository;
import com.pc_forge.backend.view.response.filter.ProcessorFilterResponse;
import com.pc_forge.backend.view.response.filter.SsdFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa serwisu dysku SSD. Dziedziczy z klasy {@link AbstractProductService},
 * przekazując klasę {@link SolidStateDrive} jako typ produktu w klasie nadrzędnej. Jest odpowiedzialna za pobieranie
 * dostępnych filtrów i stosowanie ich w zależności od otrzymanych parametrów w odebranym żądaniu HTTP.
 */
@Service
public final class SsdService extends AbstractProductService<SolidStateDrive> {
    /**
     * Repozytorium/DAO dysku SSD
     */
    private final SolidStateDriveRepository ssdRepository;

    /**
     * Konstruktor klasy serwisowej dla dysku SSD.
     * Wstrzykuje odpowiednie repozytoria do serwisu.
     *
     * @param productRepository repozytorium produktu
     * @param ssdRepository     repozytorium dysku SSD
     */
    public SsdService(ProductRepository<SolidStateDrive> productRepository, SolidStateDriveRepository ssdRepository) {
        super(productRepository);
        this.ssdRepository = ssdRepository;
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Odpowiada za pobieranie dostępnych filtrów dla dysku SSD.
     *
     * @param filter obiekt filtra produktu
     * @return Lista odfiltrowanych produktów
     */
    @Override
    public List<SolidStateDrive> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.SSD));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.SSD);
        filterByProducers(ProductCategoryCode.SSD, filter.getSelectedProducers());
        filterByName(ProductCategoryCode.SSD, filter.getName());
        filterByListParam(((SsdFilter) filter).getSelectedStorages(), ssdRepository::findByStorage);
        filterByListParam(((SsdFilter) filter).getSelectedFormats(), ssdRepository::findByDriveFormat);
        filterByListParam(((SsdFilter) filter).getSelectedInterfaces(), ssdRepository::findByDriveInterface);
        filterByListParam(((SsdFilter) filter).getSelectedReadSpeeds(), ssdRepository::findByReadSpeed);
        filterByListParam(((SsdFilter) filter).getSelectedWriteSpeeds(), ssdRepository::findByWriteSpeed);
        return applyFilters();
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Ustawia pola w klasie DTO filtra,
     * pobierając dane za pomocą kwerend zawartych w repozytorium produktu.
     *
     * @return Obiekt klasy {@link SsdFilterResponse} zawierający informację o dostępnych filtrach
     */
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
