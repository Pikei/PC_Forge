package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

/**
 * Finalna klasa będąca reprezentacją DTO (Data Transfer Object) procesora. Rozszerza klasę {@link ProductResponse}.
 * Dodaje informacje specyficzne dla procesora.
 */
@Getter
@Setter
public final class ProcessorResponse extends ProductResponse {
    /**
     * Nazwa gniazda procesora.
     */
    private String socket;

    /**
     * Liczba rdzeni procesora.
     */
    private Integer cores;

    /**
     * Liczba wątków procesora.
     */
    private Integer threads;

    /**
     * Flaga określająca czy procesor ma odblokowany mnożnik, czy nie
     */
    private Boolean unlocked;

    /**
     * Częstotliwość pracy procesora
     */
    private Double frequency;

    /**
     * Zintegrowany układ graficzny
     */
    private String integratedGraphicsUnit;

    /**
     * Flaga określająca czy procesor ma dołączony w zestawie układ chłodzenia
     */
    private Boolean coolerIncluded;
}
