package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

/**
 * Finalna klasa będąca reprezentacją DTO (Data Transfer Object) układów chłodzenia procesora. Rozszerza klasę {@link ProductResponse}.
 * Dodaje informacje specyficzne dla układów chłodzenia procesora.
 */
@Getter
@Setter
public final class CoolerResponse extends ProductResponse {

    /**
     * Flaga określająca czy chłodzenie posiada podświetlenie
     */
    private Boolean lightning;

    /**
     * Liczba wiatraków
     */
    private Integer numberOfFans;

    /**
     * Średnica wiatraków
     */
    private Integer fanDiameter;

    /**
     * Poziom głośności generowany przez układ chłodzenia
     */
    private Double noiseLevel;
}
