package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Klasa filtra zasilacza dziedzicząca z {@link ProductFilter}. Zawiera wartości, po których będą filtrowane wyniki
 */
@Getter
@Setter
public final class PowerSupplyFilter extends ProductFilter {
    /**
     * Lista wybranych mocy zasilacza
     */
    private List<Integer> selectedPowers;

    /**
     * Lista wybranych certyfikatów sprawności
     */
    private List<String> selectedCertificates;

    /**
     * Lista wybranych sprawności
     */
    private List<Integer> selectedEfficiencies;

    /**
     * Lista wybranych typów chłodzenia zasilacza
     */
    private List<String> selectedCoolingTypes;

    /**
     * Lista wybranych średnic wiatraków zasilacza
     */
    private List<Integer> selectedFanDiameters;

    /**
     * Lista wybranych typów zabezpieczeń
     */
    private List<String> selectedProtections;

    /**
     * Flaga określająca czy zasilacz ma modularne okablowanie, czy nie
     */
    private Boolean modularCabling;

    /**
     * Flaga określająca czy zasilacz ma podświetlenie
     */
    private Boolean lightning;

    /**
     * Konstruktor klasy filtra zasilacza. Ustawia pola w klasie wywołując metodę {@link #setFilter()}
     *
     * @param requestParameters mapa parametrów zapytania HTTP
     */
    public PowerSupplyFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}.
     * Określa czy filtr zasilacza jest pusty lub nie (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     * Wywołuje metodę {@link #checkCommonFilterFieldsIfEmpty()} z klasy nadrzędnej.
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    @Override
    public Boolean empty() {
        boolean result = checkCommonFilterFieldsIfEmpty();
        result = result && (selectedPowers == null || selectedPowers.isEmpty());
        result = result && (selectedCertificates == null || selectedCertificates.isEmpty());
        result = result && (selectedEfficiencies == null || selectedEfficiencies.isEmpty());
        result = result && (selectedCoolingTypes == null || selectedCoolingTypes.isEmpty());
        result = result && (selectedFanDiameters == null || selectedFanDiameters.isEmpty());
        result = result && (selectedProtections == null || selectedProtections.isEmpty());
        result = result && (modularCabling == null);
        result = result && (lightning == null);
        return result;
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}. Ustawia pola filtra zasilacza na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP, pobierając z niej pożądane wartości.
     * Wywołuje metodę {@link #setCommonFilters()} z klasy nadrzędnej,
     * ustawiającą pola wspólne dla wszystkich typów produktów.
     */
    @Override
    public void setFilter() {
        setCommonFilters();
        selectedPowers = getIntegerListFromRequestParam(RequestParams.PS_POWER);
        selectedCertificates = getStringListFromRequestParam(RequestParams.CERTIFICATE);
        selectedEfficiencies = getIntegerListFromRequestParam(RequestParams.EFFICIENCY);
        selectedCoolingTypes = getStringListFromRequestParam(RequestParams.COOLING_TYPE);
        selectedFanDiameters = getIntegerListFromRequestParam(RequestParams.FAN_DIAMETER);
        selectedProtections = getStringListFromRequestParam(RequestParams.PROTECTIONS);
        modularCabling = getBooleanFromRequestParam(RequestParams.MODULAR_CABLING);
        lightning = getBooleanFromRequestParam(RequestParams.LIGHTNING);
    }
}
