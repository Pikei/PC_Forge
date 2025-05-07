package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Klasa filtra procesora dziedzicząca z {@link ProductFilter}. Zawiera wartości, po których będą filtrowane wyniki
 */
@Getter
@Setter
public final class ProcessorFilter extends ProductFilter {
    /**
     * Lista wybranych gniazd procesora
     */
    private List<String> selectedSockets;

    /**
     * Lista wybranych gniazd procesora
     */
    private List<String> selectedLines;

    /**
     * Lista wybranych liczb rdzeni procesora
     */
    private List<Integer> selectedCores;


    /**
     * Lista wybranych częstotliwości procesora
     */
    private List<Double> selectedFrequencies;

    /**
     * Lista wybranych zintegrowanych układów graficznych
     */
    private List<String> selectedGraphicsUnits;

    /**
     * Lista wybranych typów opakowań
     */
    private List<String> selectedPackagingTypes;

    /**
     * Flaga określająca czy rdzenie procesora mają być odblokowane, czy nie
     */
    private Boolean unlocked;

    /**
     * Flaga określająca czy procesor ma mieć załączone chłodzenie, czy nie
     */
    private Boolean coolerIncluded;

    /**
     * Konstruktor klasy filtra procesora. Ustawia pola w klasie wywołując metodę {@link #setFilter()}
     *
     * @param requestParameters mapa parametrów zapytania HTTP
     */
    public ProcessorFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}.
     * Określa czy filtr procesora jest pusty lub nie (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     * Wywołuje metodę {@link #checkCommonFilterFieldsIfEmpty()} z klasy nadrzędnej.
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    @Override
    public Boolean empty() {
        boolean result = checkCommonFilterFieldsIfEmpty();
        result = result && (selectedSockets == null || selectedSockets.isEmpty());
        result = result && (selectedLines == null || selectedLines.isEmpty());
        result = result && (selectedCores == null || selectedCores.isEmpty());
        result = result && (selectedFrequencies == null || selectedFrequencies.isEmpty());
        result = result && (selectedGraphicsUnits == null || selectedGraphicsUnits.isEmpty());
        result = result && (selectedPackagingTypes == null || selectedPackagingTypes.isEmpty());
        result = result && (unlocked == null);
        result = result && (coolerIncluded == null);
        return result;
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}. Ustawia pola filtra procesora na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP, pobierając z niej pożądane wartości.
     * Wywołuje metodę {@link #setCommonFilters()} z klasy nadrzędnej,
     * ustawiającą pola wspólne dla wszystkich typów produktów.
     */
    @Override
    public void setFilter() {
        setCommonFilters();
        selectedSockets = getStringListFromRequestParam(RequestParams.SOCKET);
        selectedLines = getStringListFromRequestParam(RequestParams.MODEL);
        selectedCores = getIntegerListFromRequestParam(RequestParams.NUMBER_OF_CORES);
        selectedFrequencies = getDoubleListFromRequestParam(RequestParams.FREQUENCY);
        selectedGraphicsUnits = getStringListFromRequestParam(RequestParams.INTEGRATED_GRAPHICS_UNIT);
        selectedPackagingTypes = getStringListFromRequestParam(RequestParams.PACKAGING_TYPE);
        unlocked = getBooleanFromRequestParam(RequestParams.CORE_UNLOCKED);
        coolerIncluded = getBooleanFromRequestParam(RequestParams.COOLER_INCLUDED);
    }
}
