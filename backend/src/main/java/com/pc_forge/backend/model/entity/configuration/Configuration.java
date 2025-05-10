package com.pc_forge.backend.model.entity.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.model.entity.product.cooler.Cooler;
import com.pc_forge.backend.model.entity.product.cpu.Processor;
import com.pc_forge.backend.model.entity.product.drive.HardDiskDrive;
import com.pc_forge.backend.model.entity.product.drive.SolidStateDrive;
import com.pc_forge.backend.model.entity.product.gpu.GraphicsCard;
import com.pc_forge.backend.model.entity.product.mb.Motherboard;
import com.pc_forge.backend.model.entity.product.pc_case.Case;
import com.pc_forge.backend.model.entity.product.ps.PowerSupply;
import com.pc_forge.backend.model.entity.product.ram.Memory;
import com.pc_forge.backend.model.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji configuration z bazy danych.
 * Zawiera informacje o zapisanej przez użytkownika konfiguracji komputera.
 */
@Getter
@Setter
@Entity
@Table(name = "configuration", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "configuration_config_id_key", columnNames = {"config_id"})
})
public class Configuration {
    /**
     * Identyfikator konfiguracji.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "config_id", nullable = false)
    private Long id;

    /**
     * Nazwa konfiguracji unikalna dla użytkownika.
     */
    @Column(name = "config_name")
    private String configName;

    /**
     * Obiekt użytkownika, do którego przypisana jest konfiguracja.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Obiekt karty graficznej zawartej w konfiguracji. Pole to jest opcjonalne,
     * o ile wybrany procesor posiada zintegrowany układ graficzny.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gpu_id")
    private GraphicsCard graphicsCard;

    /**
     * Obiekt obudowy komputera zawartej w konfiguracji.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "case_id", nullable = false)
    private Case pcCase;

    /**
     * Obiekt płyty głównej zawartej w konfiguracji.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mb_id", nullable = false)
    private Motherboard motherboard;

    /**
     * Obiekt układu chłodzenia zawartego w konfiguracji. Pole to jest opcjonalne,
     * o ile wybrany procesor posiada dołączone chłodzenie w zestawie.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cooler_id")
    private Cooler cooler;

    /**
     * Obiekt zasilacza zawartego w konfiguracji. Pole to jest opcjonalne,
     * o ile wybrana obudowa posiada wbudowany zasilacz.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ps_id")
    private PowerSupply powerSupply;

    /**
     * Obiekt pamięci operacyjnej RAM zawartej w konfiguracji.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ram_id", nullable = false)
    private Memory memory;

    /**
     * Obiekt procesora zawartego w konfiguracji.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cpu_id", nullable = false)
    private Processor processor;

    /**
     * Obiekt dysku SSD zawartego w konfiguracji. Pole to jest opcjonalne, o ile wybrany został dysk HDD.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ssd_id")
    private SolidStateDrive solidStateDrive;

    /**
     * Obiekt dysku HDD zawartego w konfiguracji. Pole to jest opcjonalne, o ile wybrany został dysk SSD.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hdd_id")
    private HardDiskDrive hardDiskDrive;

    /**
     * Metoda sumująca cenę produktów zawartych w konfiguracji, która następnie jest zwracana
     * w odpowiedzi jako pole {@code totalPrice}.
     *
     * @return Sumaryczna cena produktów zawartych w konfiguracji.
     */
    @Transient
    @JsonProperty("totalPrice")
    public Double getTotalPrice() {
        Double totalPrice = 0.0;
        totalPrice += graphicsCard.getPrice();
        totalPrice += pcCase.getPrice();
        totalPrice += motherboard.getPrice();
        totalPrice += cooler.getPrice();
        totalPrice += powerSupply.getPrice();
        totalPrice += memory.getPrice();
        totalPrice += processor.getPrice();
        totalPrice += solidStateDrive.getPrice();
        totalPrice += hardDiskDrive.getPrice();
        return totalPrice;
    }
}
