package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Klasa filtra pamięci operacyjnej RAM dziedzicząca z {@link ProductFilter}. Zawiera wartości, po których będą filtrowane wyniki
 */
@Getter
@Setter
public final class MemoryFilter extends ProductFilter {

    /**
     * Lista wybranych typów pamięci RAM
     */
    private List<String> selectedTypes;

    /**
     * Lista wybranych pojemności pamięci RAM
     */
    private List<Integer> selectedCapacities;

    /**
     * Lista wybranych częstotliwości pamięci RAM
     */
    private List<Integer> selectedFrequencies;

    /**
     * Lista wybranych liczb modułów pamięci RAM
     */
    private List<Integer> selectedModules;

    /**
     * Lista wybranych opóźnień zegara pamięci RAM
     */
    private List<String> selectedLatencies;

    /**
     * Flaga określająca czy pamięć RAM ma podświetlenie, czy nie
     */
    private Boolean lightning;

    /**
     * Konstruktor klasy filtra pamięci operacyjnej RAM. Ustawia pola w klasie wywołując metodę {@link #setFilter()}
     *
     * @param requestParameters mapa parametrów zapytania HTTP
     */
    public MemoryFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}.
     * Określa czy filtr pamięci RAM jest pusty lub nie (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     * Wywołuje metodę {@link #checkCommonFilterFieldsIfEmpty()} z klasy nadrzędnej.
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    @Override
    public Boolean empty() {
        boolean result = checkCommonFilterFieldsIfEmpty();
        result = result && (selectedTypes == null || selectedTypes.isEmpty());
        result = result && (selectedCapacities == null || selectedCapacities.isEmpty());
        result = result && (selectedFrequencies == null || selectedFrequencies.isEmpty());
        result = result && (selectedModules == null || selectedModules.isEmpty());
        result = result && (selectedLatencies == null || selectedLatencies.isEmpty());
        result = result && (lightning == null);
        return result;
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}. Ustawia pola filtra pamięci RAM na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP, pobierając z niej pożądane wartości.
     * Wywołuje metodę {@link #setCommonFilters()} z klasy nadrzędnej,
     * ustawiającą pola wspólne dla wszystkich typów produktów.
     */
    @Override
    public void setFilter() {
        setCommonFilters();
        selectedTypes = getStringListFromRequestParam(RequestParams.RAM_TYPE);
        selectedCapacities = getIntegerListFromRequestParam(RequestParams.RAM_CAPACITY);
        selectedFrequencies = getIntegerListFromRequestParam(RequestParams.RAM_FREQUENCY);
        selectedModules = getIntegerListFromRequestParam(RequestParams.NUMBER_OF_MODULES);
        selectedLatencies = getStringListFromRequestParam(RequestParams.LATENCY);
        lightning = getBooleanFromRequestParam(RequestParams.LIGHTNING);
    }
}
