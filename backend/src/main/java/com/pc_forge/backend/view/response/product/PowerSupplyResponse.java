package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

/**
 * Finalna klasa będąca reprezentacją DTO (Data Transfer Object) zasilacza. Rozszerza klasę {@link ProductResponse}.
 * Dodaje informacje specyficzne dla zasilacza.
 */
@Getter
@Setter
public final class PowerSupplyResponse extends ProductResponse {

    /**
     * Standard zasilacza
     */
    private String standard;

    /**
     * Moc zasilacza w watach
     */
    private Integer power;

    /**
     * Certyfikat sprawności zasilacza
     */
    private String efficiencyCertificate;

    /**
     * Sprawność procentowa zasilacza
     */
    private Integer efficiency;

    /**
     * Flaga określająca, czy zasilacz ma modularne okablowanie
     */
    private Boolean modularCabling;
}
