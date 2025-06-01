package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Finalna klasa DTO filtra płyt głównych, dziedzicząca po klasie {@link ProductFilterResponse}.
 * Zawiera dodatkowe pola specyficzne dla filtrowania płyt głównych.
 * Obiekty tej klasy służą do zwracania dostępnych opcji filtrowania dla płyt głównych
 * w odpowiedzi na odpowiednie żądania HTTP.
 */
@Getter
@Setter
public final class MotherboardFilterResponse extends ProductFilterResponse {

    /**
     * Lista par zawierających nazwy typów gniazda procesora na płycie głównej (socket)
     * i liczbę płyt głównych spełniających kryterium.
     */
    @JsonProperty(RequestParams.SOCKET)
    private List<Object[]> socket;

    /**
     * Lista par zawierających standardy płyt głównych (np. ATX, Micro-ATX)
     * i liczbę płyt głównych spełniających kryterium.
     */
    @JsonProperty(RequestParams.MOTHERBOARD_STANDARD)
    private List<Object[]> standard;

    /**
     * Lista par zawierających nazwy chipsetów płyt głównych
     * i liczbę płyt głównych spełniających kryterium.
     */
    @JsonProperty(RequestParams.CHIPSET)
    private List<Object[]> chipset;

    /**
     * Lista par zawierających typy pamięci RAM obsługiwane przez płyty główne
     * i liczbę płyt głównych spełniających kryterium.
     */
    @JsonProperty(RequestParams.RAM_TYPE)
    private List<Object[]> memoryStandard;

    /**
     * Lista par zawierających liczbę slotów na pamięć RAM na płytach głównych
     * i liczbę płyt głównych spełniających kryterium.
     */
    @JsonProperty(RequestParams.NUMBER_OF_MODULES)
    private List<Object[]> memorySlot;

    /**
     * Lista par zawierających maksymalną pojemność pamięci RAM obsługiwaną przez płyty główne
     * i liczbę płyt głównych spełniających kryterium.
     */
    @JsonProperty(RequestParams.RAM_CAPACITY)
    private List<Object[]> maxMemory;

    /**
     * Lista par zawierających częstotliwości pamięci RAM obsługiwane przez płyty główne
     * i liczbę płyt głównych spełniających kryterium.
     */
    @JsonProperty(RequestParams.RAM_FREQUENCY)
    private List<Object[]> frequency;

    /**
     * Lista par wskazujących obecność modułu Bluetooth na płytach głównych (tak/nie)
     * i liczbę płyt głównych spełniających kryterium.
     */
    @JsonProperty(RequestParams.BLUETOOTH)
    private List<Object[]> bluetooth;

    /**
     * Lista par wskazujących obecność modułu Wi-Fi na płytach głównych (tak/nie)
     * i liczbę płyt głównych spełniających kryterium.
     */
    @JsonProperty(RequestParams.WIFI)
    private List<Object[]> wifi;

    /**
     * Minimalna szerokość płyt głównych w milimetrach.
     */
    @JsonProperty(RequestParams.WIDTH_MINIMUM)
    private Double minWidth;

    /**
     * Maksymalna szerokość płyt głównych w milimetrach.
     */
    @JsonProperty(RequestParams.WIDTH_MAXIMUM)
    private Double maxWidth;

    /**
     * Minimalna głębokość płyt głównych w milimetrach.
     */
    @JsonProperty(RequestParams.DEPTH_MINIMUM)
    private Double minDepth;

    /**
     * Maksymalna głębokość płyt głównych w milimetrach.
     */
    @JsonProperty(RequestParams.DEPTH_MAXIMUM)
    private Double maxDepth;
}
