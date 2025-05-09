package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.api.constants.ProductCategoryCode;
import com.pc_forge.backend.controller.filter.CaseFilter;
import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.entity.product.pc_case.Case;
import com.pc_forge.backend.model.repository.product.pc_case.CaseRepository;
import com.pc_forge.backend.model.repository.product.ProductRepository;
import com.pc_forge.backend.view.response.filter.CaseFilterResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

/**
 * Klasa serwisu obudowy. Dziedziczy z klasy {@link AbstractProductService},
 * przekazując klasę {@link Case} jako typ produktu w klasie nadrzędnej. Jest odpowiedzialna za pobieranie
 * dostępnych filtrów i stosowanie ich w zależności od otrzymanych parametrów w odebranym żądaniu HTTP.
 */
@Service
public final class CaseService extends AbstractProductService<Case> {

    /**
     * Repozytorium/DAO obudowy
     */
    private final CaseRepository caseRepository;

    /**
     * Konstruktor klasy serwisowej dla obudowy.
     * Wstrzykuje odpowiednie repozytoria do serwisu.
     *
     * @param productRepository repozytorium produktu
     * @param caseRepository    repozytorium obudowy
     */
    public CaseService(ProductRepository<Case> productRepository, CaseRepository caseRepository) {
        super(productRepository);
        this.caseRepository = caseRepository;
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Odpowiada za pobieranie dostępnych filtrów dla obudowy.
     *
     * @param filter obiekt filtra produktu
     * @return Lista odfiltrowanych produktów
     */
    @Override
    public List<Case> getFilteredProducts(ProductFilter filter) {
        filteredProducts.clear();
        filteredProducts.add(getProductsInCategory(ProductCategoryCode.CASE));
        if (filter.empty()) {
            return applyFilters();
        }
        filterByPrice(filter.getPriceMinimum(), filter.getPriceMaximum(), ProductCategoryCode.CASE);
        filterByProducers(ProductCategoryCode.CASE, filter.getSelectedProducers());
        filterByListParam(((CaseFilter) filter).getSelectedColors(), caseRepository::findByColor);
        filterByListParam(((CaseFilter) filter).getSelectedCaseTypes(), caseRepository::findByCaseType);
        filterByListParam(((CaseFilter) filter).getSelectedCompatibleMbStandards(), caseRepository::findByCompatibleMbStandard);
        filterByWidth(((CaseFilter) filter).getMinWidth(), ((CaseFilter) filter).getMaxWidth());
        filterByHeight(((CaseFilter) filter).getMinHeight(), ((CaseFilter) filter).getMaxHeight());
        filterByDepth(((CaseFilter) filter).getMinDepth(), ((CaseFilter) filter).getMaxDepth());
        filterByBooleanParam(((CaseFilter) filter).getHasWindow(), caseRepository::findByHasWindow);
        filterByBooleanParam(((CaseFilter) filter).getHasPowerSupply(), caseRepository::findByPowerSupply);
        filterByListParam(((CaseFilter) filter).getSelectedPsPowers(), caseRepository::findByPsPower);
        filterByMaxIntegerParam(((CaseFilter) filter).getMaxGpuLength(), caseRepository::findByMaxGpuLengthLessThanEqual);
        filterByMaxIntegerParam(((CaseFilter) filter).getMaxCpuCoolerHeight(), caseRepository::findByMaxCpuCoolerHeightLessThanEqual);
        filterByListParam(((CaseFilter) filter).getSelectedFrontFans(), caseRepository::findByFrontFans);
        filterByListParam(((CaseFilter) filter).getSelectedBackFans(), caseRepository::findByBackFans);
        filterByListParam(((CaseFilter) filter).getSelectedSideFans(), caseRepository::findBySideFans);
        filterByListParam(((CaseFilter) filter).getSelectedBottomFans(), caseRepository::findByBottomFans);
        filterByListParam(((CaseFilter) filter).getSelectedTopFans(), caseRepository::findByTopFans);
        filterByListParam(((CaseFilter) filter).getSelectedUsb20(), caseRepository::findByUsb20);
        filterByListParam(((CaseFilter) filter).getSelectedUsb30(), caseRepository::findByUsb30);
        filterByListParam(((CaseFilter) filter).getSelectedUsb31(), caseRepository::findByUsb31);
        filterByListParam(((CaseFilter) filter).getSelectedUsb32(), caseRepository::findByUsb32);
        filterByListParam(((CaseFilter) filter).getSelectedUsbC(), caseRepository::findByUsbC);
        return applyFilters();
    }

    /**
     * Metoda nadpisywana z klasy nadrzędnej. Ustawia pola w klasie DTO filtra,
     * pobierając dane za pomocą kwerend zawartych w repozytorium produktu.
     *
     * @return Obiekt klasy {@link CaseFilterResponse} zawierający informację o dostępnych filtrach
     */
    @Override
    @SuppressWarnings("unchecked")
    public CaseFilterResponse getAvailableFilters() {
        CaseFilterResponse response = new CaseFilterResponse();
        response.setPriceMinimum(productRepository.getMinPriceFilter(ProductCategoryCode.POWER_SUPPLY));
        response.setPriceMaximum(productRepository.getMaxPriceFilter(ProductCategoryCode.POWER_SUPPLY));
        response.setProducers(productRepository.getProducerFilter(ProductCategoryCode.POWER_SUPPLY));
        response.setColor(caseRepository.getColorFilter());
        response.setCaseType(caseRepository.getCaseTypeFilter());
        response.setMbStandards(caseRepository.getMbStandardFilter());
        response.setWidthMinimum(caseRepository.getMinWidth());
        response.setWidthMaximum(caseRepository.getMaxWidth());
        response.setHeightMaximum(caseRepository.getMinHeight());
        response.setHeightMaximum(caseRepository.getMaxHeight());
        response.setDepthMaximum(caseRepository.getMinDepth());
        response.setDepthMaximum(caseRepository.getMaxDepth());
        response.setHasWindow(caseRepository.getWindowFilter());
        response.setHasPowerSupply(caseRepository.getPsIncludedFilter());
        response.setPsPower(caseRepository.getPsPowerFilter());
        response.setMaxGpuLength(caseRepository.getMaxGpuLengthFilter());
        response.setMaxCpuCoolerHeight(caseRepository.getMaxCpuHeightFilter());
        response.setFrontFans(caseRepository.getFrontFansFilter());
        response.setBackFans(caseRepository.getBackFansFilter());
        response.setSideFans(caseRepository.getSideFansFilter());
        response.setBottomFans(caseRepository.getBottomFansFilter());
        response.setTopFans(caseRepository.getTopFansFilter());
        response.setUsb20(caseRepository.getUsb20Filter());
        response.setUsb30(caseRepository.getUsb30Filter());
        response.setUsb31(caseRepository.getUsb31Filter());
        response.setUsb32(caseRepository.getUsb32Filter());
        response.setUsbC(caseRepository.getUsbCFilter());
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
        if (min == null && max == null) {
            return;
        }
        if (min == null) {
            min = caseRepository.getMinWidth();
        }
        if (max == null) {
            max = caseRepository.getMaxWidth();
        }
        filteredProducts.add(caseRepository.findByWidthBetween(min, max));
    }

    /**
     * Metoda filtruje produkty po wysokości, pobierając wartości pomiędzy przekazanym minimum a maksimum.
     *
     * @param min Wysokość minimalna odebrana z parametru HTTP.
     *            W przypadku gdy nie podano wysokości minimalnej, ustawiana jest największa wysokość.
     * @param max Wysokość minimalna maksymalna odebrana z parametru HTTP.
     *            W przypadku gdy nie podano wysokości maksymalnej, ustawiana jest największa wysokość.
     */
    private void filterByHeight(Double min, Double max) {
        if (min == null && max == null) {
            return;
        }
        if (min == null) {
            min = caseRepository.getMinHeight();
        }
        if (max == null) {
            max = caseRepository.getMaxHeight();
        }
        filteredProducts.add(caseRepository.findByHeightBetween(min, max));
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
        if (min == null && max == null) {
            return;
        }
        if (min == null) {
            min = caseRepository.getMinDepth();
        }
        if (max == null) {
            max = caseRepository.getMaxDepth();
        }
        filteredProducts.add(caseRepository.findByDepthBetween(min, max));
    }

    private void filterByMaxIntegerParam(Integer max, Function<Integer, List<Case>> function) {
        if (max == null) {
            return;
        }
        filteredProducts.add(function.apply(max));
    }
}
