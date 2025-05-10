package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

/**
 * Finalna klasa będąca reprezentacją DTO (Data Transfer Object) dysków HDD. Rozszerza klasę {@link ProductResponse}.
 * Dodaje informacje specyficzne dla dysków HDD.
 */
@Getter
@Setter
public class HddResponse extends DriveResponse {

    /**
     * Prędkość obrotowa dysku
     */
    private Integer rotationalSpeed;
}
