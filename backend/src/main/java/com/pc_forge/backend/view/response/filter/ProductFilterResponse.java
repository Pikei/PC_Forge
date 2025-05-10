package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Data;

import java.util.List;

/**
 * Klasa abstrakcyjna DTO filtra produktu. Zawiera informacje o dostępnych opcjach filtrowania wspólnych dla wszystkich
 * typów produktów. Klasy z niej dziedziczące dodają kolejne opcje filtrowania. Obiekty tych klas są następnie zwracane
 * jako odpowiedź na żądanie pobrania dostępnych opcji filtrowania.
 */
@Data
public abstract class ProductFilterResponse {
    /**
     * Cena minimalna produktu.
     */
    @JsonProperty(RequestParams.PRICE_MINIMUM)
    private Double priceMinimum;

    /**
     * Cena maksymalna produktu.
     */
    @JsonProperty(RequestParams.PRICE_MAXIMUM)
    private Double priceMaximum;

    /**
     * Lista par zawierająca nazwy producentów i liczbę produktów spełniających kryterium.
     */
    @JsonProperty(RequestParams.PRODUCER)
    private List<Object[]> producers;
}
