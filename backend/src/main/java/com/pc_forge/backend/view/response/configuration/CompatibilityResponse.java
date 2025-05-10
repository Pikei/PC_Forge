package com.pc_forge.backend.view.response.configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) wykorzystywanego podczas zapisywania konfiguracji użytkownika.
 * Obiekt tej klasy zwracany jest jako odpowiedź na żądanie zapisania konfiguracji. Zawiera informacje o tym,
 * czy produkty są ze sobą kompatybilne, oraz dodatkowe informacje w przypadku błędów kompatybilności.
 */
@Getter
@Setter
public class CompatibilityResponse {
    /**
     * Flaga określająca czy komponenty są ze sobą kompatybilne
     */
    private Boolean compatible;

    /**
     * Kod kategorii pierwszego niekompatybilnego produktu.
     */
    private String firstProductCategoryCode;

    /**
     * Kod kategorii drugiego niekompatybilnego produktu
     */
    private String secondProductCategoryCode;

    /**
     * Wiadomość informująca o błędach kompatybilności między produktami.
     */
    private String message;
}
