package com.pc_forge.backend.view.response.product;

import lombok.Data;

/**
 * Klasa abstrakcyjna DTO produktu. Zawiera informacje o danych wspólnych dla wszystkich typów produktów.
 * Klasy ją rozszerzające dodają własne pola. Używane przy zwracaniu list produktów jako odpowiedź na żądanie HTTP.
 */
@Data
public abstract class ProductResponse {
    /**
     * Identyfikator produktu.
     */
    private Long id;

    /**
     * Kod kategorii produktu.
     */
    private String category;

    /**
     * Nazwa produktu.
     */
    private String name;

    /**
     * Cena produktu.
     */
    private Double price;

    /**
     * Nazwa producenta.
     */
    private String producer;
}
