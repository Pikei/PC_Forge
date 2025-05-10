package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

/**
 * Klasa abstrakcyjna DTO dysków twardych. Rozszerza klasę {@link ProductResponse}.
 * Zawiera informacje o danych wspólnych dla dysków HDD i SSD. Klasy ją rozszerzające dodają własne pola.
 * Używane przy zwracaniu list dysków twardych jako odpowiedź na żądanie HTTP.
 */
@Getter
@Setter
public abstract class DriveResponse extends ProductResponse {

    /**
     * Format dysku
     */
    private String driveFormat;

    /**
     * Pojemność dysku w GB
     */
    private Integer storage;

    /**
     * Interfejs dysku
     */
    private String driveInterface;
}
