package com.pc_forge.backend.model.entity.product.mb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import com.pc_forge.backend.model.entity.compatibility.CpuSocket;
import com.pc_forge.backend.model.entity.compatibility.MotherboardStandard;
import com.pc_forge.backend.model.entity.product.Product;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

/**
 * Klasa będąca reprezentacją encji motherboard z bazy danych. Dziedziczy ona po {@link Product},
 * dodając swoje własne pola.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "motherboard", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("MB")
public final class Motherboard extends Product {
    /**
     * Chipset płyty głównej
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "chipset", nullable = false, length = 100)
    private String chipset;

    /**
     * Obsługiwany standard pamięci operacyjnej RAM
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "memory_standard", nullable = false, length = 100)
    private String memoryStandard;

    /**
     * Liczba slotów na pamięć operacyjną
     */
    @NotNull
    @Column(name = "memory_slots", nullable = false)
    private Integer memorySlots;

    /**
     * Maksymalna pojemność pamięci RAM
     */
    @NotNull
    @Column(name = "max_memory_capacity", nullable = false)
    private Integer maxMemoryCapacity;

    /**
     * Zintegrowana karda dźwiękowa
     */
    @Size(max = 200)
    @Column(name = "integrated_audio_card", length = 200)
    private String integratedAudioCard;

    /**
     * Kanały audio
     */
    @NotNull
    @Column(name = "audio_channels", nullable = false)
    private Double audioChannels;

    /**
     * Zintegrowana karta sieciowa
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "integrated_network_card", nullable = false, length = 200)
    private String integratedNetworkCard;

    /**
     * Flaga określająca czy płyta główna posiada moduł Bluetooth, czy nie
     */
    @NotNull
    @Column(name = "bluetooth", nullable = false)
    private Boolean bluetooth = false;

    /**
     * Flaga określająca czy płyta główna posiada moduł Wi-Fi, czy nie
     */
    @NotNull
    @Column(name = "wifi", nullable = false)
    private Boolean wifi = false;

    /**
     * Szerokość płyty głównej
     */
    @NotNull
    @Column(name = "width", nullable = false)
    private Double width;

    /**
     * Głębokość płyty głównej
     */
    @NotNull
    @Column(name = "depth", nullable = false)
    private Double depth;

    /**
     * Obiekt standardu płyty głównej
     */
    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "standard_id", nullable = false)
    private MotherboardStandard standard;

    /**
     * Metoda pobierająca nazwę standardu z przypisanego obiektu {@link #standard}.
     *
     * @return nazwa standardu płyty głównej
     */
    @Transient
    @JsonProperty(RequestParams.MOTHERBOARD_STANDARD)
    public String getMotherboardStandardName() {
        return standard.getStandardName();
    }

    /**
     * Obiekt gniazda procesora znajdującego się na płycie głównej
     */
    @NotNull
    @JsonIgnore
    @JoinColumn(name = "socket_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private CpuSocket socket;

    /**
     * Metoda pobierająca nazwę gniazda procesora z przypisanego obiektu {@link #socket}.
     *
     * @return Nazwa gniazda procesora
     */
    @Transient
    @JsonProperty(RequestParams.SOCKET)
    public String getSocketName() {
        return socket.getSocketName();
    }

    /**
     * Lista slotów rozszerzeń
     */
    @Type(ListArrayType.class)
    @Column(name = "expansion_slots", nullable = false, columnDefinition = "_varchar (Types#ARRAY)")
    private List<String> expansionSlots;

    /**
     * Lista interfejsów dyskowych
     */
    @Type(ListArrayType.class)
    @Column(name = "drive_interfaces", nullable = false, columnDefinition = "_varchar (Types#ARRAY)")
    private List<String> driveInterfaces;

    /**
     * Lista złącz zewnętrznych
     */
    @Type(ListArrayType.class)
    @Column(name = "outside_connectors", nullable = false, columnDefinition = "_varchar (Types#ARRAY)")
    private List<String> outsideConnectors;

    /**
     * Lista wspieranych częstotliwości pamięci RAM
     */
    @Type(ListArrayType.class)
    @Column(name = "supported_memory_frequencies", nullable = false, columnDefinition = "_int4 (Types#ARRAY)")
    private List<Integer> supportedMemoryFrequencies;
}
