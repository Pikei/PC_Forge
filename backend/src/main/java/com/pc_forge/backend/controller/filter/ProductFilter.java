package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Klasa abstrakcyjna reprezentująca filtr produktów. Zawiera parametry i metody wykorzystywane przez wszystkie typy produktów.
 * Wszystkie pola w tej klasie i w klasach dziedziczących odbierane są z parametrów zapytania.
 */
@Getter
@Setter
public abstract class ProductFilter {

    /**
     * Nazwa produktu
     */
    private String name;

    /**
     * Minimalna cena produktu
     */
    private Double priceMinimum;

    /**
     * Maksymalna cena produktu
     */
    private Double priceMaximum;


    /**
     * Lista wybranych producentów
     */
    private List<String> selectedProducers;

    /**
     * Mapa otrzymanych w zapytaniu parametrów
     */
    protected Map<String, String[]> requestParameters;

    /**
     * Konstruktor klasy filtra produktów
     *
     * @param requestParameters Mapa otrzymanych w zapytaniu parametrów
     */
    public ProductFilter(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    /**
     * Metoda abstrakcyjna nadpisywana przez klasy dziedziczące. Określa czy filtr produktu jest pusty lub nie
     * (nie zostały przekazane żadne parametry filtrowania w żądaniu).
     *
     * @return Jeśli filtr jest pusty zwracane jest {@code true}, w przeciwnym razie {@code false}
     */
    public abstract Boolean empty();

    /**
     * Metoda abstrakcyjna nadpisywana przez klasy dziedziczące. Ustawia pola w klasach filtrów produktów na podstawie
     * otrzymanej w konstruktorze mapy parametrów HTTP, pobierając z niej pożądane wartości.
     */
    protected abstract void setFilter();

    /**
     * Metoda sprawdzająca, czy parametry filtrów wspólne dla wszystkich typów zostały przekazane lub są puste
     *
     * @return Jeśli parametry są puste lub nie zostały przekazane zwracane jest {@code true} w przeciwnym razie {@code false}
     */
    protected Boolean checkCommonFilterFieldsIfEmpty() {
        boolean result = priceMinimum == null && priceMaximum == null;
        result = result && (name == null || name.isEmpty());
        result = result && (selectedProducers == null || selectedProducers.isEmpty());
        return result;
    }

    /**
     * Metoda pobierająca wspólne parametry dla wszystkich typów produktów z mapy przekazanej w konstruktorze.
     * Metoda ta dodatkowo rozdziela przekazane parametry, dzieląc je w miejscu, gdzie znajduje się przecinek.
     * Dzięki temu parametry będące listą (składające się z wielu wartości, np. wybrani producenci)
     * mogą być przekazane w dwóch formach:
     * <div>
     *      <h6>Powielenie nazwy parametru:</h6>
     *      <p>{@code example.com/path?param=value1&param=value2}</p>
     * </div>
     * <div>
     *     <h6>Oddzielenie wartości poprzez przecinek:</h6>
     *     <p>{@code example.com/path?param=value1,value2}</p>
     * </div>
     */
    protected void setCommonFilters() {
        splitJoinedParams();
        if (requestParameters.containsKey(RequestParams.NAME)) {
            setName(requestParameters.get(RequestParams.NAME)[0]);
        }
        setPriceMinimum(getDoubleFromRequestParam(RequestParams.PRICE_MINIMUM));
        setPriceMaximum(getDoubleFromRequestParam(RequestParams.PRICE_MAXIMUM));
        setSelectedProducers(getStringListFromRequestParam(RequestParams.PRODUCER));
    }

    /**
     * Metoda rozdzielająca parametry będące listą w przypadku,
     * gdy parametr nie został powielony z kolejnymi wartościami, tylko zostały one rozdzielone przecinkami.
     * Dzięki temu parametry będące listą (składające się z wielu wartości, np. wybrani producenci)
     * mogą być przekazane w dwóch formach:
     * <div>
     *      <h6>Powielenie nazwy parametru:</h6>
     *      <p>{@code example.com/path?param=value1&param=value2}</p>
     * </div>
     * <div>
     *     <h6>Oddzielenie wartości poprzez przecinek:</h6>
     *     <p>{@code example.com/path?param=value1,value2}</p>
     * </div>
     */
    private void splitJoinedParams() {
        Map<String, String[]> params = new HashMap<>();
        for (String key : requestParameters.keySet()) {
            if (requestParameters.get(key).length == 1) {
                params.put(key, requestParameters.get(key)[0].split(","));
            } else {
                params.put(key, requestParameters.get(key));
            }
        }
        this.requestParameters = params;
    }

    /**
     * Metoda pobierająca wartość typu {@code true} lub {@code false} z mapy parametrów przekazanej w konstruktorze
     * na podstawie otrzymanego klucza. W przypadku duplikacji parametru będzie brany pod uwagę wyłącznie pierwszy przekazany.
     *
     * @param param nazwa parametru będąca kluczem w mapie
     * @return wartość przekazana w parametrze lub {@code null} jeśli parametr nie został przekazany
     */
    protected Boolean getBooleanFromRequestParam(String param) {
        if (requestParameters.containsKey(param)) {
            return Boolean.parseBoolean(requestParameters.get(param)[0]);
        }
        return null;
    }

    /**
     * Metoda pobierająca wartość typu liczby całkowitej z mapy parametrów przekazanej w konstruktorze
     * na podstawie otrzymanego klucza. W przypadku duplikacji parametru będzie brany pod uwagę wyłącznie pierwszy przekazany.
     *
     * @param param nazwa parametru będąca kluczem w mapie
     * @return wartość przekazana w parametrze lub {@code null} jeśli parametr nie został przekazany,
     * lub przekazany parametr jest nieprawidłowego typu
     */
    protected Integer getIntegerFromRequestParam(String param) {
        if (requestParameters.containsKey(param)) {
            try {
                return Integer.parseInt(requestParameters.get(param)[0]);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Metoda pobierająca wartość typu liczby zmiennoprzecinkowej z mapy parametrów przekazanej w konstruktorze
     * na podstawie otrzymanego klucza. W przypadku duplikacji parametru będzie brany pod uwagę wyłącznie pierwszy przekazany.
     *
     * @param param nazwa parametru będąca kluczem w mapie
     * @return wartość przekazana w parametrze lub {@code null} jeśli parametr nie został przekazany,
     * lub przekazany parametr jest nieprawidłowego typu
     */
    protected Double getDoubleFromRequestParam(String param) {
        if (requestParameters.containsKey(param)) {
            try {
                return Double.parseDouble(requestParameters.get(param)[0]);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Metoda pobierająca listę zawierającą wartości tekstowe z mapy parametrów przekazanej w konstruktorze
     * na podstawie otrzymanego klucza.
     *
     * @param param nazwa parametru będąca kluczem w mapie
     * @return lista wartości przekazanych w parametrze lub {@code null} jeśli parametr nie został przekazany
     */
    protected List<String> getStringListFromRequestParam(String param) {
        if (requestParameters.containsKey(param)) {
            return Arrays.stream(requestParameters.get(param)).toList();
        }
        return null;
    }

    /**
     * Metoda pobierająca listę zawierającą wartości liczbowe (całkowite) z mapy parametrów przekazanej w konstruktorze
     * na podstawie otrzymanego klucza.
     *
     * @param param nazwa parametru będąca kluczem w mapie
     * @return lista wartości przekazanych w parametrze lub {@code null} jeśli parametr nie został przekazany,
     * lub przekazana wartość jest nieprawidłowego typu
     */
    protected List<Integer> getIntegerListFromRequestParam(String param) {
        if (requestParameters.containsKey(param)) {
            try {
                return Arrays.stream(requestParameters.get(param)).map(Integer::valueOf).toList();
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Metoda pobierająca listę zawierającą wartości liczbowe (zmiennoprzecinkowe) z mapy parametrów
     * przekazanej w konstruktorze na podstawie otrzymanego klucza.
     *
     * @param param nazwa parametru będąca kluczem w mapie
     * @return lista wartości przekazanych w parametrze lub {@code null} jeśli parametr nie został przekazany,
     * lub przekazana wartość jest nieprawidłowego typu
     */
    protected List<Double> getDoubleListFromRequestParam(String param) {
        if (requestParameters.containsKey(param)) {
            return Arrays.stream(requestParameters.get(param)).map(Double::valueOf).toList();
        }
        return null;
    }
}
