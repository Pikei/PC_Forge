package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

/**
 * Finalna klasa będąca reprezentacją DTO (Data Transfer Object) pamięci operacyjnej RAM. Rozszerza klasę {@link ProductResponse}.
 * Dodaje informacje specyficzne dla pamięci operacyjnej RAM.
 */
@Getter
@Setter
public final class MemoryResponse extends ProductResponse {

    /**
     * Typ pamięci RAM
     */
    private String memoryType;

    /**
     * Pojemność łączna zestawu
     */
    private Integer totalCapacity;

    /**
     * Opóźnienie zegara pamięci operacyjnej
     */
    private String latency;

    /**
     * Częstotliwość taktowania pamięci RAM
     */
    private Integer frequency;

    /**
     * Liczba modułów w zestawie
     */
    private Integer modules;

    /**
     * Flaga określająca czy, pamięć RAM posiada podświetlenie
     */
    private Boolean lighting;

}
