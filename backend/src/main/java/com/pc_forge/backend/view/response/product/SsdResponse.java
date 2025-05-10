package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

/**
 * Finalna klasa będąca reprezentacją DTO (Data Transfer Object) dysków SSD. Rozszerza klasę {@link ProductResponse}.
 * Dodaje informacje specyficzne dla dysków SSD.
 */
@Getter
@Setter
public final class SsdResponse extends DriveResponse {

    /**
     * Prędkość odczytu dysku
     */
    private Integer readSpeed;

    /**
     * Prędkość zapisu dysku
     */
    private Integer writeSpeed;
}
