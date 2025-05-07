package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Klasa filtra dysku HDD dziedzicząca z {@link CoolerFilter}. Zawiera wartości, po których będą filtrowane wyniki
 */
@Getter
@Setter
public final class HddFilter extends DriveFilter {
    /**
     * Lista wybranych prędkości obrotowych dysku
     */
    private List<Integer> selectedRotationalSpeeds;

    /**
     * Konstruktor klasy filtra dysku HDD. Ustawia pola w klasie wywołując metodę {@link #setFilter()}
     *
     * @param requestParameters mapa parametrów zapytania HTTP
     */
    public HddFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}.
     * Określa czy filtr dysku HDD jest pusty lub nie (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     * Wywołuje metodę {@link #checkCommonFiltersForDrives()} z klasy nadrzędnej.
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    @Override
    public Boolean empty() {
        boolean result = checkCommonFiltersForDrives();
        result = result && (selectedRotationalSpeeds == null || selectedRotationalSpeeds.isEmpty());
        return result;
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}. Ustawia pola filtra dysku HDD na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP, pobierając z niej pożądane wartości.
     * Wywołuje metodę {@link #setCommonDriveFilters()} z klasy nadrzędnej,
     * ustawiającą pola wspólne dla wszystkich typów chłodzeń procesora.
     */
    @Override
    public void setFilter() {
        setCommonDriveFilters();
        selectedRotationalSpeeds = getIntegerListFromRequestParam(RequestParams.ROTATIONAL_SPEED);
    }
}
