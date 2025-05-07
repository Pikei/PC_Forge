package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Klasa filtra dysku SSD dziedzicząca z {@link CoolerFilter}. Zawiera wartości, po których będą filtrowane wyniki
 */
@Getter
@Setter
public final class SsdFilter extends DriveFilter {
    /**
     * Lista wybranych prędkości zapisu
     */
    private List<Integer> selectedWriteSpeeds;

    /**
     * Lista wybranych prędkości odczytu
     */
    private List<Integer> selectedReadSpeeds;

    /**
     * Konstruktor klasy filtra dysku SSD. Ustawia pola w klasie wywołując metodę {@link #setFilter()}
     *
     * @param requestParameters mapa parametrów zapytania HTTP
     */
    public SsdFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}.
     * Określa czy filtr dysku SSD jest pusty lub nie (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     * Wywołuje metodę {@link #checkCommonFiltersForDrives()} z klasy nadrzędnej.
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    @Override
    public Boolean empty() {
        boolean result = checkCommonFiltersForDrives();
        result = result && (selectedWriteSpeeds == null || selectedWriteSpeeds.isEmpty());
        result = result && (selectedReadSpeeds == null || selectedReadSpeeds.isEmpty());
        return result;
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}. Ustawia pola filtra dysku SSD na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP, pobierając z niej pożądane wartości.
     * Wywołuje metodę {@link #setCommonDriveFilters()} z klasy nadrzędnej,
     * ustawiającą pola wspólne dla wszystkich typów chłodzeń procesora.
     */
    @Override
    public void setFilter() {
        setCommonDriveFilters();
        selectedWriteSpeeds = getIntegerListFromRequestParam(RequestParams.WRITE_SPEED);
        selectedReadSpeeds = getIntegerListFromRequestParam(RequestParams.READ_SPEED);
    }
}
