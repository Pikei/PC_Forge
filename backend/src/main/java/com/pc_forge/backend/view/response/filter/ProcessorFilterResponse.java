package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Finalna klasa DTO filtra procesorów, dziedzicząca po klasie {@link ProductFilterResponse}.
 * Zawiera dodatkowe pola specyficzne dla filtrowania procesorów.
 * Obiekty tej klasy służą do zwracania dostępnych opcji filtrowania dla procesorów
 * w odpowiedzi na odpowiednie żądania HTTP.
 */
@Getter
@Setter
public final class ProcessorFilterResponse extends ProductFilterResponse {

    /**
     * Lista par zawierających nazwy typów gniazda procesora (socket)
     * i liczbę procesorów spełniających kryterium.
     */
    @JsonProperty(RequestParams.SOCKET)
    private List<Object[]> socket;

    /**
     * Lista par zawierających modele procesorów i liczbę procesorów spełniających kryterium.
     */
    @JsonProperty(RequestParams.MODEL)
    private List<Object[]> model;

    /**
     * Lista par zawierających liczbę rdzeni procesorów i liczbę procesorów spełniających kryterium.
     */
    @JsonProperty(RequestParams.NUMBER_OF_CORES)
    private List<Object[]> numberOfCores;

    /**
     * Lista par zawierających częstotliwości procesorów i liczbę procesorów spełniających kryterium.
     */
    @JsonProperty(RequestParams.FREQUENCY)
    private List<Object[]> frequency;

    /**
     * Lista par wskazujących obecność zintegrowanej jednostki graficznej (tak/nie)
     * i liczbę procesorów spełniających kryterium.
     */
    @JsonProperty(RequestParams.INTEGRATED_GRAPHICS_UNIT)
    private List<Object[]> integratedGraphicsUnit;

    /**
     * Lista par wskazujących obecność chłodzenia w zestawie (tak/nie)
     * i liczbę procesorów spełniających kryterium.
     */
    @JsonProperty(RequestParams.COOLER_INCLUDED)
    private List<Object[]> coolerIncluded;

    /**
     * Lista par zawierających typy opakowań procesorów i liczbę procesorów spełniających kryterium.
     */
    @JsonProperty(RequestParams.PACKAGING_TYPE)
    private List<Object[]> packaging;

    /**
     * Lista par wskazujących odblokowane rdzenie procesora (tak/nie)
     * i liczbę procesorów spełniających kryterium.
     */
    @JsonProperty(RequestParams.CORE_UNLOCKED)
    private List<Object[]> coreUnlocked;
}
