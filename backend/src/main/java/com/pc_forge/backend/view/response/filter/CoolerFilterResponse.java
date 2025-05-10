package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Klasa abstrakcyjna DTO filtra układu chłodzenia. Rozszerza klasę {@link ProductFilterResponse}.
 * Zawiera informacje o dostępnych opcjach filtrowania wspólnych dla wszystkich typów układów chłodzeń.
 * Klasy z niej dziedziczące dodają kolejne opcje filtrowania. Obiekty tych klas są następnie zwracane
 * jako odpowiedź na żądanie pobrania dostępnych opcji filtrowania.
 */
@Getter
@Setter
public abstract class CoolerFilterResponse extends ProductFilterResponse {
    /**
     * Lista par zawierających dostępne gniazda procesora i liczbę układów chłodzenia procesora spełniających kryterium.
     */
    @JsonProperty(RequestParams.SOCKET)
    private List<Object[]> socket;

    /**
     * Lista par zawierających dostępne ilości wiatraków i liczbę układów chłodzenia procesora spełniających kryterium.
     */
    @JsonProperty(RequestParams.NUMBER_OF_FANS)
    private List<Object[]> fans;

    /**
     * Lista par zawierających dostępne średnice wiatraków i liczbę układów chłodzenia procesora spełniających kryterium.
     */
    @JsonProperty(RequestParams.FAN_DIAMETER)
    private List<Object[]> fanDiameters;

    /**
     * Lista par zawierających dostępne poziomy generowanego hałasu i liczbę układów chłodzenia procesora spełniających kryterium.
     */
    @JsonProperty(RequestParams.NOISE_LEVEL)
    private List<Object[]> noiseLevel;

    /**
     * Lista par wskazujących obecność podświetlenia i liczbę układów chłodzenia procesora spełniających kryterium.
     */
    @JsonProperty(RequestParams.LIGHTNING)
    private List<Object[]> lightning;
}
