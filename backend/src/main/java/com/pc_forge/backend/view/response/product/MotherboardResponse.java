package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

/**
 * Finalna klasa będąca reprezentacją DTO (Data Transfer Object) płyty głównej. Rozszerza klasę {@link ProductResponse}.
 * Dodaje informacje specyficzne dla płyty głównej.
 */
@Getter
@Setter
public final class MotherboardResponse extends ProductResponse {

    /**
     * Chipset płyty głównej
     */
    private String chipset;

    /**
     * Obsługiwany standard pamięci operacyjnej RAM
     */
    private String memoryStandard;

    /**
     * Standard płyty głównej
     */
    private String standard;

    /**
     * Liczba slotów na pamięć operacyjną
     */
    private Integer memorySlots;

    /**
     * Flaga określająca czy płyta główna posiada moduł Bluetooth, czy nie
     */
    private Boolean bluetooth;

    /**
     * Flaga określająca czy płyta główna posiada moduł Wi-Fi, czy nie
     */
    private Boolean wifi;
}
