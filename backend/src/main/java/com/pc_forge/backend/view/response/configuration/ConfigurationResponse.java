package com.pc_forge.backend.view.response.configuration;

import com.pc_forge.backend.view.response.product.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) konfiguracji.
 * Obiekt tej klasy zwracany jest jako odpowiedź na żądanie pobrania danych o konkretnej konfiguracji.
 * Zawiera bardziej szczegółowe informacje niż te zawarte w klasie {@link ConfigurationShortResponse}.
 */
@Getter
@Setter
public class ConfigurationResponse {
    /**
     * Nazwa konfiguracji.
     */
    private String name;

    /**
     * Sumaryczna cena konfiguracji.
     */
    private Double price;

    /**
     * Obiekt procesora zapisanego w konfiguracji.
     */
    private ProcessorResponse processor;

    /**
     * Obiekt płyty głównej zapisanej w konfiguracji.
     */
    private MotherboardResponse motherboard;

    /**
     * Obiekt pamięci operacyjnej RAM zapisanej w konfiguracji.
     */
    private MemoryResponse memory;

    /**
     * Obiekt karty graficznej zapisanej w konfiguracji.
     */
    private GraphicsCardResponse graphicsCard;

    /**
     * Obiekt zasilacza zapisanego w konfiguracji.
     */
    private PowerSupplyResponse powerSupply;

    /**
     * Obiekt obudowy komputerowej zapisanej w konfiguracji.
     */
    private CaseResponse pcCase;

    /**
     * Obiekt układu chłodzenia zapisanego w konfiguracji.
     */
    private CoolerResponse cooler;

    /**
     * Obiekt dysku SSD zapisanego w konfiguracji.
     */
    private SsdResponse solidStateDrive;

    /**
     * Obiekt dysku HDD zapisanego w konfiguracji.
     */
    private HddResponse hardDiskDrive;
}
