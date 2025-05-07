package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Klasa filtra płyty głównej dziedzicząca z {@link ProductFilter}. Zawiera wartości, po których będą filtrowane wyniki
 */
@Getter
@Setter
public final class MotherboardFilter extends ProductFilter {
    /**
     * Lista wybranych gniazd procesora
     */
    private List<String> selectedSockets;

    /**
     * Lista wybranych standardów płyty głównej
     */
    private List<String> selectedStandards;

    /**
     * Lista wybranych chipsetów płyty głównej
     */
    private List<String> selectedChipsets;

    /**
     * Lista wybranych standardów pamięci RAM
     */
    private List<String> selectedMemoryStandards;

    /**
     * Lista wybranych liczb slotów pamięci RAM
     */
    private List<Integer> selectedMemorySlots;

    /**
     * Lista wybranych maksymalnych pojemności pamięci RAM
     */
    private List<Integer> selectedMaxMemoryCapacity;

    /**
     * Lista wybranych częstotliwości pamięci RAM
     */
    private List<Integer> selectedFrequencies;

    /**
     * Flaga określająca czy płyta główna ma moduł Bluetooth, czy nie
     */
    private Boolean bluetooth;

    /**
     * Flaga określająca czy płyta główna ma moduł Wi-Fi, czy nie
     */
    private Boolean wifi;

    /**
     * Minimalna szerokość płyty głównej
     */
    private Double minWidth;

    /**
     * Maksymalna szerokość płyty głównej
     */
    private Double maxWidth;

    /**
     * Minimalna głębokość płyty głównej
     */
    private Double minDepth;

    /**
     * Maksymalna głębokość płyty głównej
     */
    private Double maxDepth;

    /**
     * Konstruktor klasy filtra płyty głównej. Ustawia pola w klasie wywołując metodę {@link #setFilter()}
     *
     * @param requestParameters mapa parametrów zapytania HTTP
     */
    public MotherboardFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}.
     * Określa czy filtr płyty głównej jest pusty lub nie (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     * Wywołuje metodę {@link #checkCommonFilterFieldsIfEmpty()} z klasy nadrzędnej.
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    @Override
    public Boolean empty() {
        boolean result = checkCommonFilterFieldsIfEmpty();
        result = result && (selectedSockets == null || selectedSockets.isEmpty());
        result = result && (selectedStandards == null || selectedStandards.isEmpty());
        result = result && (selectedChipsets == null || selectedChipsets.isEmpty());
        result = result && (selectedMemoryStandards == null || selectedMemoryStandards.isEmpty());
        result = result && (selectedMemorySlots == null || selectedMemorySlots.isEmpty());
        result = result && (selectedMaxMemoryCapacity == null || selectedMaxMemoryCapacity.isEmpty());
        result = result && (selectedFrequencies == null || selectedFrequencies.isEmpty());
        result = result && bluetooth == null;
        result = result && wifi == null;
        result = result && minWidth == null && maxWidth == null;
        result = result && minDepth == null && maxDepth == null;
        return result;
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}. Ustawia pola filtra płyty głównej na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP, pobierając z niej pożądane wartości.
     * Wywołuje metodę {@link #setCommonFilters()} z klasy nadrzędnej,
     * ustawiającą pola wspólne dla wszystkich typów produktów.
     */
    @Override
    public void setFilter() {
        setCommonFilters();
        selectedSockets = getStringListFromRequestParam(RequestParams.SOCKET);
        selectedStandards = getStringListFromRequestParam(RequestParams.MOTHERBOARD_STANDARD);
        selectedChipsets = getStringListFromRequestParam(RequestParams.CHIPSET);
        selectedMemoryStandards = getStringListFromRequestParam(RequestParams.RAM_TYPE);
        selectedMemorySlots = getIntegerListFromRequestParam(RequestParams.RAM_SLOTS);
        selectedMaxMemoryCapacity = getIntegerListFromRequestParam(RequestParams.RAM_CAPACITY);
        selectedFrequencies = getIntegerListFromRequestParam(RequestParams.FREQUENCY);
        bluetooth = getBooleanFromRequestParam(RequestParams.BLUETOOTH);
        wifi = getBooleanFromRequestParam(RequestParams.WIFI);
        minWidth = getDoubleFromRequestParam(RequestParams.WIDTH_MINIMUM);
        maxWidth = getDoubleFromRequestParam(RequestParams.WIDTH_MAXIMUM);
        minDepth = getDoubleFromRequestParam(RequestParams.DEPTH_MINIMUM);
        maxDepth = getDoubleFromRequestParam(RequestParams.DEPTH_MAXIMUM);
    }
}
