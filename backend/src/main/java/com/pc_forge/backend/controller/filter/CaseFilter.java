package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Klasa filtra obudowy dziedzicząca z {@link ProductFilter}. Zawiera wartości, po których będą filtrowane wyniki
 */
@Getter
@Setter
public final class CaseFilter extends ProductFilter {
    /**
     * Lista wybranych kolorów obudowy
     */
    private List<String> selectedColors;

    /**
     * Lista wybranych typów obudowy
     */
    private List<String> selectedCaseTypes;

    /**
     * Lista wybranych standardów płyt głównych kompatybilnych z obudową
     */
    private List<String> selectedCompatibleMbStandards;

    /**
     * Minimalna wysokość obudowy
     */
    private Double minHeight;

    /**
     * Maksymalna wysokość obudowy
     */
    private Double maxHeight;

    /**
     * Minimalna szerokość obudowy
     */
    private Double minWidth;

    /**
     * Maksymalna szerokość obudowy
     */
    private Double maxWidth;

    /**
     * Minimalna głębokość obudowy
     */
    private Double minDepth;

    /**
     * Maksymalna głębokość obudowy
     */
    private Double maxDepth;

    /**
     * Flaga określająca czy obudowa ma okno, czy nie
     */
    private Boolean hasWindow;

    /**
     * Flaga określająca czy obudowa ma zasilacz, czy nie
     */
    private Boolean hasPowerSupply;

    /**
     * Lista wybranych mocy załączonego zasilacza
     */
    private List<Integer> selectedPsPowers;

    /**
     * Maksymalna długość karty graficznej
     */
    private Integer maxGpuLength;

    /**
     * Maksymalna wysokość układu chłodzenia procesora
     */
    private Integer maxCpuCoolerHeight;

    /**
     * Lista wybranych wiatraków na panelu przednim
     */
    private List<String> selectedFrontFans;

    /**
     * Lista wybranych wiatraków na panelu tylnym
     */
    private List<String> selectedBackFans;

    /**
     * Lista wybranych wiatraków na panelu bocznym
     */
    private List<String> selectedSideFans;

    /**
     * Lista wybranych wiatraków na panelu dolnym
     */
    private List<String> selectedBottomFans;

    /**
     * Lista wybranych wiatraków na panelu górnym
     */
    private List<String> selectedTopFans;

    /**
     * Lista wybranych liczb portów USB 2.0
     */
    private List<Integer> selectedUsb20;

    /**
     * Lista wybranych liczb portów USB 3.0
     */
    private List<Integer> selectedUsb30;

    /**
     * Lista wybranych liczb portów USB 3.1
     */
    private List<Integer> selectedUsb31;

    /**
     * Lista wybranych liczb portów USB 3.2
     */
    private List<Integer> selectedUsb32;

    /**
     * Lista wybranych liczb portów USB-C
     */
    private List<Integer> selectedUsbC;

    /**
     * Konstruktor klasy filtra obudowy. Ustawia pola w klasie wywołując metodę {@link #setFilter()}
     *
     * @param requestParameters mapa parametrów zapytania HTTP
     */
    public CaseFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}.
     * Określa czy filtr obudowy jest pusty lub nie (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     * Wywołuje metodę {@link #checkCommonFilterFieldsIfEmpty()} z klasy nadrzędnej.
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    @Override
    public Boolean empty() {
        boolean result = checkCommonFilterFieldsIfEmpty();
        result = result && (selectedColors == null || selectedColors.isEmpty());
        result = result && (selectedCaseTypes == null || selectedCaseTypes.isEmpty());
        result = result && (selectedCompatibleMbStandards == null || selectedCompatibleMbStandards.isEmpty());
        result = result && minHeight == null && maxHeight == null;
        result = result && minWidth == null && maxWidth == null;
        result = result && minDepth == null && maxDepth == null;
        result = result && (hasWindow == null);
        result = result && (hasPowerSupply == null);
        result = result && (selectedPsPowers == null || selectedPsPowers.isEmpty());
        result = result && (maxGpuLength == null);
        result = result && (maxCpuCoolerHeight == null);
        result = result && (selectedFrontFans == null || selectedFrontFans.isEmpty());
        result = result && (selectedBackFans == null || selectedBackFans.isEmpty());
        result = result && (selectedSideFans == null || selectedSideFans.isEmpty());
        result = result && (selectedBottomFans == null || selectedBottomFans.isEmpty());
        result = result && (selectedTopFans == null || selectedTopFans.isEmpty());
        result = result && (selectedUsb20 == null || selectedUsb20.isEmpty());
        result = result && (selectedUsb30 == null || selectedUsb30.isEmpty());
        result = result && (selectedUsb31 == null || selectedUsb31.isEmpty());
        result = result && (selectedUsb32 == null || selectedUsb32.isEmpty());
        return result;
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}. Ustawia pola filtra obudowy na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP, pobierając z niej pożądane wartości.
     * Wywołuje metodę {@link #setCommonFilters()} z klasy nadrzędnej,
     * ustawiającą pola wspólne dla wszystkich typów produktów.
     */
    @Override
    public void setFilter() {
        setCommonFilters();
        selectedColors = getStringListFromRequestParam(RequestParams.COLOR);
        selectedCaseTypes = getStringListFromRequestParam(RequestParams.CASE_TYPE);
        selectedCompatibleMbStandards = getStringListFromRequestParam(RequestParams.MOTHERBOARD_STANDARD);
        minHeight = getDoubleFromRequestParam(RequestParams.HEIGHT_MINIMUM);
        maxHeight = getDoubleFromRequestParam(RequestParams.HEIGHT_MAXIMUM);
        minWidth = getDoubleFromRequestParam(RequestParams.WIDTH_MINIMUM);
        maxWidth = getDoubleFromRequestParam(RequestParams.WIDTH_MAXIMUM);
        minDepth = getDoubleFromRequestParam(RequestParams.DEPTH_MINIMUM);
        maxDepth = getDoubleFromRequestParam(RequestParams.DEPTH_MAXIMUM);
        hasWindow = getBooleanFromRequestParam(RequestParams.HAS_WINDOW);
        hasPowerSupply = getBooleanFromRequestParam(RequestParams.HAS_POWER_SUPPLY);
        selectedPsPowers = getIntegerListFromRequestParam(RequestParams.PS_POWER);
        maxGpuLength = getIntegerFromRequestParam(RequestParams.MAX_GPU_LENGTH);
        maxCpuCoolerHeight = getIntegerFromRequestParam(RequestParams.MAX_CPU_HEIGHT);
        selectedFrontFans = getStringListFromRequestParam(RequestParams.FRONT_FANS);
        selectedBackFans = getStringListFromRequestParam(RequestParams.BACK_FANS);
        selectedSideFans = getStringListFromRequestParam(RequestParams.SIDE_FANS);
        selectedBottomFans = getStringListFromRequestParam(RequestParams.BOTTOM_FANS);
        selectedTopFans = getStringListFromRequestParam(RequestParams.TOP_FANS);
        selectedUsb20 = getIntegerListFromRequestParam(RequestParams.USB_20);
        selectedUsb30 = getIntegerListFromRequestParam(RequestParams.USB_30);
        selectedUsb31 = getIntegerListFromRequestParam(RequestParams.USB_31);
        selectedUsb32 = getIntegerListFromRequestParam(RequestParams.USB_32);
        selectedUsbC = getIntegerListFromRequestParam(RequestParams.USB_C);
    }
}
