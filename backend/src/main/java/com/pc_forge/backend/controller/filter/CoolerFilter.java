package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Klasa abstrakcyjna reprezentująca filtr układów chłodzenia procesora. Rozszerza klasę {@link ProductFilter}.
 * Zawiera parametry i metody wykorzystywane przez wszystkie typy układów chłodzenia procesora.
 */
@Getter
@Setter
public abstract class CoolerFilter extends ProductFilter {
    /**
     * Lista wybranych gniazd procesora
     */
    private List<String> selectedSockets;

    /**
     * Lista wybranych liczb wiatraków
     */
    private List<Integer> selectedFans;

    /**
     * Lista wybranych średnic wiatraków
     */
    private List<Integer> selectedFanDiameters;

    /**
     * Lista wybranych poziomów hałasu generowanych przez chłodzenie
     */
    private List<Double> noiseLevel;

    /**
     * Flaga określająca czy chłodzenie ma podświetlenie, czy nie
     */
    private Boolean lightning;

    /**
     * Konstruktor klasy filtra układów chłodzeń procesora
     *
     * @param requestParameters Mapa otrzymanych w zapytaniu parametrów
     */
    public CoolerFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
    }

    /**
     * Metoda sprawdzająca wspólne pola dla układów chłodzenia procesora.
     * Określa czy filtr jest pusty lub nie (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     * Wywołuje metodę {@link #checkCommonFilterFieldsIfEmpty()} z klasy nadrzędnej.
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    protected Boolean checkCommonCoolerFiltersIfEmpty() {
        boolean result = checkCommonFilterFieldsIfEmpty();
        result = result && (selectedSockets == null || selectedSockets.isEmpty());
        result = result && (selectedFans == null || selectedFans.isEmpty());
        result = result && (selectedFanDiameters == null || selectedFanDiameters.isEmpty());
        result = result && (noiseLevel == null || noiseLevel.isEmpty());
        result = result && (lightning == null);
        return result;
    }

    /**
     * Metoda ustawiająca wspólne pola filtra układów chłodzeń procesora, na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP i pobiera z niej pożądane wartości.
     * Wywołuje metodę {@link #setCommonFilters()} z klasy nadrzędnej,
     * ustawiającą pola wspólne dla wszystkich typów produktów.
     */
    protected void setCommonCoolerFilters() {
        setCommonFilters();
        selectedSockets = getStringListFromRequestParam(RequestParams.SOCKET);
        selectedFans = getIntegerListFromRequestParam(RequestParams.NUMBER_OF_FANS);
        selectedFanDiameters = getIntegerListFromRequestParam(RequestParams.FAN_DIAMETER);
        noiseLevel = getDoubleListFromRequestParam(RequestParams.NOISE_LEVEL);
        lightning = getBooleanFromRequestParam(RequestParams.LIGHTNING);
    }
}
