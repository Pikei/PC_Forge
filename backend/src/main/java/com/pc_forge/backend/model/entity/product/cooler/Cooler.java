package com.pc_forge.backend.model.entity.product.cooler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import com.pc_forge.backend.model.entity.compatibility.CpuSocket;
import com.pc_forge.backend.model.entity.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa abstrakcyjna dziedzicząca po {@link Product}, będąca reprezentacją encji cooler z bazy danych.
 * Jest to klasa bazowa dla klasy {@link LiquidCooler} oraz {@link AirCooler} i zawiera informacje wspólne
 * dla obydwu typów układów chłodzenia procesora.
 */
@Getter
@Setter
@Entity
@Table(name = "cooler", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cooler extends Product {
    /**
     * Flaga określająca czy chłodzenie posiada podświetlenie
     */
    @NotNull
    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    /**
     * Liczba wiatraków
     */
    @NotNull
    @Column(name = "fans", nullable = false)
    private Integer fans;

    /**
     * Średnica wiatraków
     */
    @NotNull
    @Column(name = "fan_diameter", nullable = false)
    private Integer fanDiameter;

    /**
     * Prędkość obrotowa wiatraków
     */
    @Column(name = "fan_speed")
    private Integer fanSpeed;

    /**
     * Poziom głośności generowany przez układ chłodzenia
     */
    @Column(name = "noise_level")
    private Double noiseLevel;

    /**
     * Zmapowana lista kompatybilnych gniazd procesora.
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "cooler_socket_compatibility",
            joinColumns = @JoinColumn(name = "ean"),
            inverseJoinColumns = @JoinColumn(name = "socket_id")
    )
    private List<CpuSocket> compatibleSockets;

    /**
     * Metoda służąca do zwracania w odpowiedzi sformatowanej listy gniazd procesora zawierającą wyłącznie ich nazwy.
     *
     * @return sformatowana lista gniazd procesora
     */
    @Transient
    @JsonProperty(RequestParams.SOCKET)
    public List<String> getCompatibleSocketsNames() {
        return compatibleSockets.stream()
                .map(CpuSocket::getSocketName)
                .collect(Collectors.toList());
    }

}