package com.pc_forge.backend.view.response.configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) produktu.
 * Obiekt tej klasy zwracany jest jako odpowiedź na żądanie pobrania danych o liście konfiguracji użytkownika.
 * Zawiera podstawowe informacje o produktach zapisanych w konfiguracji.
 */
@Getter
@Setter
public class ProductConfigurationResponse {
    /**
     * Identyfikator produktu, umożliwiający odnalezienie później szczegółowych informacji o produkcie,
     * oraz znalezienie jego zdjęcia.
     */
    private Long productEan;

    /**
     * Nazwa produktu.
     */
    private String productName;

    /**
     * Cena produktu.
     */
    private Double productPrice;

    /**
     * Kategoria produktu. Umożliwia późniejsze zlokalizowanie zdjęcia produktu.
     */
    private String productCategory;

    /**
     * Nazwa producenta produktu. Umożliwia późniejsze zlokalizowanie zdjęcia produktu.
     */
    private String producer;
}
