package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Klasa filtra układu chłodzenia powietrzem dziedzicząca z {@link CoolerFilter}. Zawiera wartości, po których będą filtrowane wyniki
 */
@Getter
@Setter
public final class
AirCoolerFilter extends CoolerFilter {
    /**
     * Lista wybranych materiałów podstawy chłodzenia
     */
    private List<String> selectedBaseMaterials;

    /**
     * Minimalna wysokość
     */
    private Integer minHeight;

    /**
     * Maksymalna wysokość
     */
    private Integer maxHeight;

    /**
     * Minimalna szerokość
     */
    private Integer minWidth;

    /**
     * Maksymalna szerokość
     */
    private Integer maxWidth;

    /**
     * Minimalna głębokość
     */
    private Integer minDepth;

    /**
     * Maksymalna głębokość
     */
    private Integer maxDepth;

    /**
     * Flaga określająca czy chłodzenie jest montowane w sposób wertykalny (pionowy) czy nie
     */
    private Boolean verticalInstallation;

    /**
     * Konstruktor klasy filtra układu chłodzenia powietrzem. Ustawia pola w klasie wywołując metodę {@link #setFilter()}
     *
     * @param requestParameters mapa parametrów zapytania HTTP
     */
    public AirCoolerFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}.
     * Określa czy filtr chłodzenia powietrzem jest pusty lub nie (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     * Wywołuje metodę {@link #checkCommonCoolerFiltersIfEmpty()} z klasy nadrzędnej.
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    @Override
    public Boolean empty() {
        boolean result = checkCommonCoolerFiltersIfEmpty();
        result = result && (selectedBaseMaterials == null || selectedBaseMaterials.isEmpty());
        result = result && minHeight == null && maxHeight == null;
        result = result && minWidth == null && maxWidth == null;
        result = result && minDepth == null && maxDepth == null;
        result = result && (verticalInstallation == null);
        return result;
    }

    /**
     * Metoda nadpisywana z {@link ProductFilter}. Ustawia pola filtra chłodzenia powietrzem na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP, pobierając z niej pożądane wartości.
     * Wywołuje metodę {@link #setCommonCoolerFilters()} z klasy nadrzędnej,
     * ustawiającą pola wspólne dla wszystkich typów chłodzeń procesora.
     */
    @Override
    public void setFilter() {
        setCommonCoolerFilters();
        selectedBaseMaterials = getStringListFromRequestParam(RequestParams.BASE_MATERIAL);
        minHeight = getIntegerFromRequestParam(RequestParams.HEIGHT_MINIMUM);
        maxHeight = getIntegerFromRequestParam(RequestParams.HEIGHT_MAXIMUM);
        minWidth = getIntegerFromRequestParam(RequestParams.WIDTH_MINIMUM);
        maxWidth = getIntegerFromRequestParam(RequestParams.WIDTH_MAXIMUM);
        minDepth = getIntegerFromRequestParam(RequestParams.DEPTH_MINIMUM);
        maxDepth = getIntegerFromRequestParam(RequestParams.DEPTH_MAXIMUM);
        verticalInstallation = getBooleanFromRequestParam(RequestParams.VERTICAL_INSTALLATION);
    }
}
