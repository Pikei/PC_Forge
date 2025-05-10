package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

/**
 * Finalna klasa będąca reprezentacją DTO (Data Transfer Object) karty graficznej. Rozszerza klasę {@link ProductResponse}.
 * Dodaje informacje specyficzne dla karty graficznej.
 */
@Getter
@Setter
public final class GraphicsCardResponse extends ProductResponse {

    /**
     * Producent chipsetu układu graficznego
     */
    private String chipsetProducer;

    /**
     * Chipset karty graficznej
     */
    private String chipset;

    /**
     * Długość karty graficznej
     */
    private Integer cardLength;

    /**
     * Ilość pamięci VRAM karty graficznej w GB
     */
    private Integer ram;
}
