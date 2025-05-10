package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Finalna klasa DTO filtra kart graficznych, dziedzicząca po klasie {@link ProductFilterResponse}.
 * Zawiera dodatkowe pola specyficzne dla filtrowania kart graficznych.
 * Obiekty tej klasy służą do zwracania dostępnych opcji filtrowania dla kart graficznych
 * w odpowiedzi na odpowiednie żądania HTTP.
 */
@Getter
@Setter
public final class GpuFilterResponse extends ProductFilterResponse {

    /**
     * Lista par zawierających producentów chipsetów graficznych
     * i liczbę kart graficznych spełniających kryterium.
     */
    @JsonProperty(RequestParams.CHIPSET_PRODUCER)
    private List<Object[]> chipsetProducer;

    /**
     * Lista par zawierających chipsety graficzne
     * i liczbę kart graficznych spełniających kryterium.
     */
    @JsonProperty(RequestParams.CHIPSET)
    private List<Object[]> chipset;

    /**
     * Lista par zawierających pojemności pamięci RAM kart graficznych
     * i liczbę kart graficznych spełniających kryterium.
     */
    @JsonProperty(RequestParams.RAM_CAPACITY)
    private List<Object[]> ram;

    /**
     * Lista par zawierających typy pamięci RAM kart graficznych
     * i liczbę kart graficznych spełniających kryterium.
     */
    @JsonProperty(RequestParams.RAM_TYPE)
    private List<Object[]> ramType;

    /**
     * Lista par zawierających obsługiwaną technologię DLSS
     * i liczbę kart graficznych spełniających kryterium.
     */
    @JsonProperty(RequestParams.DLSS)
    private List<Object[]> dlss;

    /**
     * Lista par zawierających typy magistrali kart graficznych
     * i liczbę kart graficznych spełniających kryterium.
     */
    @JsonProperty(RequestParams.CONNECTOR)
    private List<Object[]> connector;

    /**
     * Lista par zawierających obsługiwane rozdzielczości wyjściowe
     * i liczbę kart graficznych spełniających kryterium.
     */
    @JsonProperty(RequestParams.RESOLUTION)
    private List<Object[]> resolution;

    /**
     * Lista par zawierających rekomendowane moce zasilaczy dla kart graficznych
     * i liczbę kart graficznych spełniających kryterium.
     */
    @JsonProperty(RequestParams.PS_POWER)
    private List<Object[]> recommendedPsPower;

    /**
     * Lista par zawierających typy układów chłodzenia kart graficznych
     * i liczbę kart graficznych spełniających kryterium.
     */
    @JsonProperty(RequestParams.COOLING_TYPE)
    private List<Object[]> coolingType;

    /**
     * Lista par zawierających liczbę wentylatorów na kartach graficznych
     * i liczbę kart graficznych spełniających kryterium.
     */
    @JsonProperty(RequestParams.NUMBER_OF_FANS)
    private List<Object[]> numberOfFans;

    /**
     * Lista par wskazujących obecność podświetlenia w kartach graficznych (tak/nie)
     * i liczbę kart graficznych spełniających kryterium.
     */
    @JsonProperty(RequestParams.LIGHTNING)
    private List<Object[]> lightning;

    /**
     * Minimalna długość kart graficznych w milimetrach.
     */
    @JsonProperty(RequestParams.LENGTH_MINIMUM)
    private Integer lengthMinimum;

    /**
     * Maksymalna długość kart graficznych w milimetrach.
     */
    @JsonProperty(RequestParams.LENGTH_MAXIMUM)
    private Integer lengthMaximum;
}