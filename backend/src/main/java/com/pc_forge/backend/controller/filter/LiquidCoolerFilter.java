package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Klasa filtra układu chłodzenia cieczą dziedzicząca z {@link CoolerFilter}. Zawiera wartości, po których będą filtrowane wyniki
 */
@Getter
@Setter
public final class LiquidCoolerFilter extends CoolerFilter {
    /**
     * Lista wybranych rozmiarów chłodnic
     */
    private List<Integer> selectedCoolerSizes;

    /**
     * Konstruktor klasy filtra układu chłodzenia cieczą. Ustawia pola w klasie wywołując metodę {@link #setFilter()}
     *
     * @param requestParameters mapa parametrów zapytania HTTP
     */
    public LiquidCoolerFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}.
     * Określa czy filtr chłodzenia cieczą jest pusty lub nie (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     * Wywołuje metodę {@link #checkCommonCoolerFiltersIfEmpty()} z klasy nadrzędnej.
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    @Override
    public Boolean empty() {
        boolean result = checkCommonCoolerFiltersIfEmpty();
        result = result && (selectedCoolerSizes == null || selectedCoolerSizes.isEmpty());
        return result;
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}. Ustawia pola filtra chłodzenia cieczą na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP, pobierając z niej pożądane wartości.
     * Wywołuje metodę {@link #setCommonCoolerFilters()} z klasy nadrzędnej,
     * ustawiającą pola wspólne dla wszystkich typów chłodzeń procesora.
     */
    @Override
    public void setFilter() {
        setCommonCoolerFilters();
        selectedCoolerSizes = getIntegerListFromRequestParam(RequestParams.COOLER_SIZE);
    }
}
