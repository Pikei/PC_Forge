package com.pc_forge.backend.view.response.configuration;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) konfiguracji.
 * Obiekt tej klasy zwracany jest jako odpowiedź na żądanie pobrania listy konfiguracji użytkownika.
 * Zawiera uproszczone dane o konfiguracji niż te zawarte w klasie {@link ConfigurationResponse}.
 */
@Getter
@Setter
public class ConfigurationShortResponse {
    /**
     * Nazwa konfiguracji.
     */
    private String name;

    /**
     * Sumaryczna cena konfiguracji.
     */
    private Double price;

    /**
     * Lista uproszczonych danych o produkcie zapisanych w konfiguracji.
     */
    private List<ProductConfigurationResponse> products;
}
