package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Finalna klasa DTO filtra zasilaczy, dziedzicząca po klasie {@link ProductFilterResponse}.
 * Zawiera dodatkowe pola specyficzne dla filtrowania zasilaczy.
 * Obiekty tej klasy służą do zwracania dostępnych opcji filtrowania dla zasilaczy
 * w odpowiedzi na odpowiednie żądania HTTP.
 */
@Getter
@Setter
public final class PsFilterResponse extends ProductFilterResponse {

    /**
     * Lista par zawierających moce zasilaczy w watach i liczbę zasilaczy spełniających kryterium.
     */
    @JsonProperty(RequestParams.PS_POWER)
    private List<Object[]> power;

    /**
     * Lista par zawierających dostępne certyfikaty sprawności i liczbę zasilaczy spełniających kryterium.
     */
    @JsonProperty(RequestParams.CERTIFICATE)
    private List<Object[]> efficiencyCertificate;

    /**
     * Lista par zawierających dostępne sprawności i liczbę zasilaczy spełniających kryterium.
     */
    @JsonProperty(RequestParams.EFFICIENCY)
    private List<Object[]> efficiency;

    /**
     * Lista par zawierających dostępne zabezpieczenia i liczbę zasilaczy spełniających kryterium.
     */
    @JsonProperty(RequestParams.PROTECTIONS)
    private List<Object[]> protections;

    /**
     * Lista par zawierających dostępne rodzaje chłodzenia i liczbę zasilaczy spełniających kryterium.
     */
    @JsonProperty(RequestParams.COOLING_TYPE)
    private List<Object[]> coolingType;

    /**
     * Lista par zawierających dostępne średnice wiatraków i liczbę zasilaczy spełniających kryterium.
     */
    @JsonProperty(RequestParams.FAN_DIAMETER)
    private List<Object[]> fanDiameter;

    /**
     * Lista par wskazujących obecność modularnego okablowania i liczbę zasilaczy spełniających kryterium.
     */
    @JsonProperty(RequestParams.MODULAR_CABLING)
    private List<Object[]> modularCabling;

    /**
     * Lista par wskazujących obecność podświetlenia i liczbę zasilaczy spełniających kryterium.
     */
    @JsonProperty(RequestParams.LIGHTNING)
    private List<Object[]> lightning;
}
