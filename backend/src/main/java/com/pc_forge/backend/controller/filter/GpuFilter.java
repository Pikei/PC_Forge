package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Klasa filtra karty graficznej dziedzicząca z {@link ProductFilter}. Zawiera wartości, po których będą filtrowane wyniki
 */
@Getter
@Setter
public final class GpuFilter extends ProductFilter {
    /**
     * Lista wybranych producentów chipsetów karty graficznej
     */
    private List<String> selectedChipsetProducers;

    /**
     * Lista wybranych chipsetów karty graficznej
     */
    private List<String> selectedChipsets;

    /**
     * Lista wybranych pojemności pamięci RAM karty graficznej
     */
    private List<Integer> selectedRamCapacities;

    /**
     * Lista wybranych typów pamięci RAM karty graficznej
     */
    private List<String> selectedRamTypes;

    /**
     * Lista wybranych technologii DLSS
     */
    private List<String> selectedDlls;

    /**
     * Lista wybranych typów złącz karty graficznej
     */
    private List<String> selectedConnectors;

    /**
     * Lista wybranych rozdzielczości generowanego obrazu przez kartę graficzną
     */
    private List<String> selectedResolutions;

    /**
     * Lista wybranych rekomendowanych mocy zasilaczy
     */
    private List<Integer> selectedRecommendedPs;

    /**
     * Lista wybranych typów chłodzenia
     */
    private List<String> selectedCoolingTypes;

    /**
     * Lista wybranych liczb wiatraków karty graficznej
     */
    private List<Integer> selectedFans;

    /**
     * Minimalna długość karty graficznej
     */
    private Double minLength;

    /**
     * Maksymalna długość karty graficznej
     */
    private Double maxLength;

    /**
     * Flaga określająca czy karta graficzna ma podświetlenie, czy nie
     */
    private Boolean lightning;

    /**
     * Konstruktor klasy filtra karty graficznej. Ustawia pola w klasie wywołując metodę {@link #setFilter()}
     *
     * @param requestParameters mapa parametrów zapytania HTTP
     */
    public GpuFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}.
     * Określa czy filtr karty graficznej jest pusty lub nie (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     * Wywołuje metodę {@link #checkCommonFilterFieldsIfEmpty()} z klasy nadrzędnej.
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    @Override
    public Boolean empty() {
        boolean result = checkCommonFilterFieldsIfEmpty();
        result = result && (selectedChipsetProducers == null || selectedChipsetProducers.isEmpty());
        result = result && (selectedChipsets == null || selectedChipsets.isEmpty());
        result = result && (selectedRamCapacities == null || selectedRamCapacities.isEmpty());
        result = result && (selectedRamTypes == null || selectedRamTypes.isEmpty());
        result = result && (selectedDlls == null || selectedDlls.isEmpty());
        result = result && (selectedConnectors == null || selectedConnectors.isEmpty());
        result = result && (selectedResolutions == null || selectedResolutions.isEmpty());
        result = result && (selectedRecommendedPs == null || selectedRecommendedPs.isEmpty());
        result = result && (selectedCoolingTypes == null || selectedCoolingTypes.isEmpty());
        result = result && (selectedFans == null || selectedFans.isEmpty());
        result = result && minLength == null && maxLength == null;
        result = result && (lightning == null);
        return result;
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}. Ustawia pola filtra karty graficznej na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP, pobierając z niej pożądane wartości.
     * Wywołuje metodę {@link #setCommonFilters()} z klasy nadrzędnej,
     * ustawiającą pola wspólne dla wszystkich typów produktów.
     */
    @Override
    public void setFilter() {
        setCommonFilters();
        selectedChipsetProducers = getStringListFromRequestParam(RequestParams.CHIPSET_PRODUCER);
        selectedChipsets = getStringListFromRequestParam(RequestParams.CHIPSET);
        selectedRamCapacities = getIntegerListFromRequestParam(RequestParams.VRAM_CAPACITY);
        selectedRamTypes = getStringListFromRequestParam(RequestParams.RAM_TYPE);
        selectedDlls = getStringListFromRequestParam(RequestParams.DLSS);
        selectedConnectors = getStringListFromRequestParam(RequestParams.CONNECTOR);
        selectedResolutions = getStringListFromRequestParam(RequestParams.RESOLUTION);
        selectedRecommendedPs = getIntegerListFromRequestParam(RequestParams.PS_POWER);
        selectedCoolingTypes = getStringListFromRequestParam(RequestParams.COOLING_TYPE);
        selectedFans = getIntegerListFromRequestParam(RequestParams.NUMBER_OF_FANS);
        minLength = getDoubleFromRequestParam(RequestParams.LENGTH_MINIMUM);
        maxLength = getDoubleFromRequestParam(RequestParams.LENGTH_MAXIMUM);
        lightning = getBooleanFromRequestParam(RequestParams.LIGHTNING);
    }
}
