package com.pc_forge.backend.view.body.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) wykorzystywanego podczas zapisywania konfiguracji użytkownika.
 * Odbierane jest przez kontroler jako Request Body.
 */
@Getter
@Setter
public class ConfigurationBody {
    /**
     * Nazwa konfiguracji.
     */
    @NotNull
    @NotBlank
    private String configName;

    /**
     * Identyfikator procesora.
     */
    @NotNull
    private Long processorId;

    /**
     * Identyfikator płyty głównej.
     */
    @NotNull
    private Long motherboardId;

    /**
     * Identyfikator pamięci operacyjnej RAM.
     */
    @NotNull
    private Long memoryId;

    /**
     * Identyfikator obudowy komputerowej.
     */
    @NotNull
    private Long caseId;

    /**
     * Identyfikator karty graficznej.
     */
    private Long graphicsCardId;

    /**
     * Identyfikator układu chłodzenia.
     */
    private Long coolerId;

    /**
     * Identyfikator zasilacza komputerowego.
     */
    private Long powerSupplyId;

    /**
     * Identyfikator dysku SSD.
     */
    private Long solidStateDriveId;

    /**
     * Identyfikator dysku HDD.
     */
    private Long hardDiskDriveId;
}
