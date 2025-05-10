package com.pc_forge.backend.model.entity.product.cpu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import com.pc_forge.backend.model.entity.compatibility.CpuSocket;
import com.pc_forge.backend.model.entity.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji processor z bazy danych. Dziedziczy ona po {@link Product},
 * dodając swoje własne pola.
 */
@Getter
@Setter
@Entity
@Table(name = "processor", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("CPU")
public final class Processor extends Product {
    /**
     * Linia procesora (np. Core i5, Ryzen 7)
     */
    @NotNull
    @Column(name = "line", nullable = false, length = Integer.MAX_VALUE)
    private String line;

    /**
     * Model procesora (np. 1135G7, 5600G)
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "model", nullable = false, length = 200)
    private String model;

    /**
     * Liczba rdzeni procesora
     */
    @NotNull
    @Column(name = "cores", nullable = false)
    private Integer cores;

    /**
     * Liczba wątków procesora
     */
    @NotNull
    @Column(name = "threads", nullable = false)
    private Integer threads;

    /**
     * Flaga określająca czy procesor ma odblokowany mnożnik, czy nie
     */
    @NotNull
    @Column(name = "unlocked", nullable = false)
    private Boolean unlocked = false;

    /**
     * Częstotliwość pracy procesora
     */
    @NotNull
    @Column(name = "frequency", nullable = false)
    private Double frequency;

    /**
     * Maksymalna częstotliwość pracy procesora
     */
    @NotNull
    @Column(name = "max_frequency", nullable = false)
    private Double maxFrequency;

    /**
     * Zintegrowany układ graficzny
     */
    @Column(name = "integrated_graphics_unit", length = Integer.MAX_VALUE)
    private String integratedGraphicsUnit;

    /**
     * TDP procesora (Thermal Design Power)
     */
    @NotNull
    @Column(name = "tdp", nullable = false)
    private Integer tdp;

    /**
     * Flaga określająca czy procesor ma dołączony w zestawie układ chłodzenia
     */
    @NotNull
    @Column(name = "cooler_included", nullable = false)
    private Boolean coolerIncluded = false;

    /**
     * Opakowanie procesora (np. BOX, OEM)
     */
    @Size(max = 10)
    @NotNull
    @Column(name = "packaging", nullable = false, length = 10)
    private String packaging;

    /**
     * Obiekt gniazda procesora, do którego pasuje ten procesor
     */
    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "socket_id", nullable = false)
    private CpuSocket socket;

    /**
     * Metoda pobierająca nazwę gniazda procesora z przypisanego do procesora obiektu {@link CpuSocket}.
     *
     * @return nazwa gniazda procesora
     */
    @Transient
    @JsonProperty(RequestParams.SOCKET)
    public String getSocketName() {
        return socket.getSocketName();
    }
}
