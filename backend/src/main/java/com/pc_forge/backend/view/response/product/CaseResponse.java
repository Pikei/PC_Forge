package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Finalna klasa będąca reprezentacją DTO (Data Transfer Object) obudowy. Rozszerza klasę {@link ProductResponse}.
 * Dodaje informacje specyficzne dla obudowy.
 */
@Getter
@Setter
public final class CaseResponse extends ProductResponse {

    /**
     * Typ obudowy
     */
    private String caseType;

    /**
     * Kolor obudowy
     */
    private String color;

    /**
     * Flaga określająca, czy obudowa ma okno
     */
    private Boolean hasWindow;

    /**
     * Flaga określająca czy obudowa posiada podświetlenie
     */
    private Boolean lightning;
}
