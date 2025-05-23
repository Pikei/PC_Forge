package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.filter.MotherboardFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.entity.product.mb.Motherboard;
import com.pc_forge.backend.model.entity.product.ram.Memory;
import com.pc_forge.backend.model.repository.product.mb.MotherboardRepository;
import com.pc_forge.backend.model.repository.product.ProductRepository;
import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.view.response.filter.MemoryFilterResponse;
import com.pc_forge.backend.view.response.filter.MotherboardFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klasa serwisu płyty głównej. Dziedziczy z klasy {@link AbstractProductService},
 * przekazując klasę {@link Motherboard} jako typ produktu w klasie nadrzędnej. Jest odpowiedzialna za pobieranie
 * dostępnych filtrów i stosowanie ich w zależności od otrzymanych parametrów w odebranym żądaniu HTTP.
 */
@Service
public final class MotherboardService extends AbstractProductService<Motherboard> {
    /**
     * Repozytorium/DAO płyty głównej
     */
    private final MotherboardRepository motherboardRepository;

    /**
     * Konstruktor klasy serwisowej dla płyty głównej.
     * Wstrzykuje odpowiednie repozytoria do serwisu.
     *
     * @param productRepository     repozytorium produktu
     * @param motherboardRepository repozytorium płyty głównej
     */
    public MotherboardService(ProductRepository<Motherboard> productRepository, MotherboardRepository motherboardRepository) {
        super(productRepository);
        this.motherboardRepository = motherboardRepository;
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Odpowiada za pobieranie dostępnych filtrów dla płyty głównej.
     *
     * @param filter obiekt filtra produktu
     * @return Lista odfiltrowanych produktów
     */
    @Override
    public List<Motherboard> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.MOTHERBOARD));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.MOTHERBOARD);
        filterByProducers(ProductCategoryCode.MOTHERBOARD, filter.getSelectedProducers());
        filterByName(ProductCategoryCode.MOTHERBOARD, filter.getName());
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

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Ustawia pola w klasie DTO filtra,
     * pobierając dane za pomocą kwerend zawartych w repozytorium produktu.
     *
     * @return Obiekt klasy {@link MotherboardFilterResponse} zawierający informację o dostępnych filtrach
     */
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

    /**
     * Metoda filtruje produkty po szerokości, pobierając wartości pomiędzy przekazanym minimum a maksimum.
     *
     * @param min Szerokość minimalna odebrana z parametru HTTP.
     *            W przypadku gdy nie podano szerokości minimalnej, ustawiana jest największa szerokość.
     * @param max Szerokość minimalna maksymalna odebrana z parametru HTTP.
     *            W przypadku gdy nie podano szerokości maksymalnej, ustawiana jest największa szerokość.
     */
    private void filterByWidth(Double min, Double max) {
        if (min == null) {
            min = motherboardRepository.getMinWidth();
        }
        if (max == null) {
            max = motherboardRepository.getMaxWidth();
        }
        filteredProducts.add(motherboardRepository.findByWidthBetween(min, max));
    }

    /**
     * Metoda filtruje produkty po głębokości, pobierając wartości pomiędzy przekazanym minimum a maksimum.
     *
     * @param min Głębokość minimalna odebrana z parametru HTTP.
     *            W przypadku gdy nie podano głębokości minimalnej, ustawiana jest największa głębokość.
     * @param max Głębokość minimalna maksymalna odebrana z parametru HTTP.
     *            W przypadku gdy nie podano głębokości maksymalnej, ustawiana jest największa głębokość.
     */
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
