package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Klasa abstrakcyjna reprezentująca filtr dysków pamięci. Rozszerza klasę {@link ProductFilter}.
 * Zawiera parametry i metody wykorzystywane przez wszystkie typy układów dysków pamięci.
 */
@Getter
@Setter
public abstract class DriveFilter extends ProductFilter {
    /**
     * Lista wybranych formatów dysków
     */
    protected List<String> selectedFormats;

    /**
     * Lista wybranych interfejsów dysków
     */
    protected List<String> selectedInterfaces;

    /**
     * Lista wybranych pojemności dysków
     */
    protected List<Integer> selectedStorages;

    /**
     * Konstruktor klasy filtra dysków pamięci
     *
     * @param requestParameters Mapa otrzymanych w zapytaniu parametrów
     */
    public DriveFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
    }

    /**
     * Metoda sprawdzająca wspólne pola dla dysków pamięci.
     * Określa czy filtr jest pusty lub nie (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     * Wywołuje metodę {@link #checkCommonFilterFieldsIfEmpty()} z klasy nadrzędnej.
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    protected Boolean checkCommonFiltersForDrives() {
        boolean result = checkCommonFilterFieldsIfEmpty();
        result = result && (selectedFormats == null || selectedFormats.isEmpty());
        result = result && (selectedInterfaces == null || selectedInterfaces.isEmpty());
        result = result && (selectedStorages == null || selectedStorages.isEmpty());
        return result;
    }

    /**
     * Metoda ustawiająca wspólne pola filtra dysków pamięci, na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP i pobiera z niej pożądane wartości.
     * Wywołuje metodę {@link #setCommonFilters()} z klasy nadrzędnej,
     * ustawiającą pola wspólne dla wszystkich typów produktów.
     */
    protected void setCommonDriveFilters() {
        setCommonFilters();
        selectedFormats = getStringListFromRequestParam(RequestParams.FORMAT);
        selectedInterfaces = getStringListFromRequestParam(RequestParams.INTERFACE);
        selectedStorages = getIntegerListFromRequestParam(RequestParams.STORAGE);
    }
}
