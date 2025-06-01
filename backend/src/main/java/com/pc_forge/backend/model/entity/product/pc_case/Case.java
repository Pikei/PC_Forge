package com.pc_forge.backend.model.entity.product.pc_case;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import com.pc_forge.backend.model.entity.compatibility.MotherboardStandard;
import com.pc_forge.backend.model.entity.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa będąca reprezentacją encji pc_case z bazy danych. Dziedziczy ona po {@link Product},
 * dodając swoje własne pola.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pc_case", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("CASE")
public final class Case extends Product {

    /**
     * Kolor obudowy
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "color", nullable = false, length = 200)
    private String color;

    /**
     * Flaga określająca czy obudowa posiada podświetlenie
     */
    @NotNull
    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    /**
     * Wysokość obudowy
     */
    @NotNull
    @Column(name = "height", nullable = false)
    private Double height;

    /**
     * Szerokość obudowy
     */
    @NotNull
    @Column(name = "width", nullable = false)
    private Double width;

    /**
     * Głębokość obudowy
     */
    @NotNull
    @Column(name = "depth", nullable = false)
    private Double depth;

    /**
     * Waga obudowy
     */
    @NotNull
    @Column(name = "weight", nullable = false)
    private Double weight;

    /**
     * Typ obudowy
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "case_type", nullable = false, length = 200)
    private String caseType;

    /**
     * Flaga określająca, czy obudowa ma okno
     */
    @NotNull
    @Column(name = "has_window", nullable = false)
    private Boolean hasWindow = false;

    /**
     * Flaga określająca, czy obudowa ma czytnik kart
     */
    @NotNull
    @Column(name = "card_reader", nullable = false)
    private Boolean cardReader = false;

    /**
     * Flaga określająca, czy obudowa ma złącze mikrofonowe
     */
    @NotNull
    @Column(name = "microphone_connector", nullable = false)
    private Boolean microphoneConnector = false;

    /**
     * Flaga określająca, czy obudowa ma złącze słuchawkowe/głośnikowe
     */
    @NotNull
    @Column(name = "headphones_connector", nullable = false)
    private Boolean headphonesConnector = false;

    /**
     * Liczba wewnętrznych wnęk 3,5 cala
     */
    @NotNull
    @Column(name = "internal_35_bays", nullable = false)
    private Integer internal35Bays;

    /**
     * Liczba wewnętrznych wnęk 2,5 cala
     */
    @NotNull
    @Column(name = "internal_25_bays", nullable = false)
    private Integer internal25Bays;

    /**
     * Liczba zewnętrznych wnęk 3,5 cala
     */
    @NotNull
    @Column(name = "external_35_bays", nullable = false)
    private Integer external35Bays;

    /**
     * Liczba zewnętrznych wnęk 5,25 cala
     */
    @NotNull
    @Column(name = "external_525_bays", nullable = false)
    private Integer external525Bays;

    /**
     * Maksymalna długość karty graficznej
     */
    @NotNull
    @Column(name = "max_gpu_length", nullable = false)
    private Integer maxGpuLength;

    /**
     * Maksymalna wysokość układu chłodzenia procesora
     */
    @NotNull
    @Column(name = "max_cpu_cooler_height", nullable = false)
    private Integer maxCpuCoolerHeight;

    /**
     * Liczba slotów rozszerzeń
     */
    @NotNull
    @Column(name = "extension_slots", nullable = false)
    private Integer extensionSlots;

    /**
     * Wiatraki na panelu przednim
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "front_fans", nullable = false, length = 200)
    private String frontFans;

    /**
     * Wiatraki na panelu tylnym
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "back_fans", nullable = false, length = 200)
    private String backFans;

    /**
     * Wiatraki na panelu bocznym
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "side_fans", nullable = false, length = 200)
    private String sideFans;

    /**
     * Wiatraki na panelu dolnym
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "bottom_fans", nullable = false, length = 200)
    private String bottomFans;

    /**
     * Wiatraki na panelu górnym
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "top_fans", nullable = false, length = 200)
    private String topFans;

    /**
     * Flaga określająca, czy obudowa posiada wbudowany zasilacz
     */
    @NotNull
    @Column(name = "power_supply", nullable = false)
    private Boolean powerSupply = false;

    /**
     * Moc wbudowanego zasilacza
     */
    @Column(name = "ps_power")
    private Integer psPower;

    /**
     * Liczba portów USB 2.0
     */
    @NotNull
    @Column(name = "usb_20", nullable = false)
    private Integer usb20;

    /**
     * Liczba portów USB 3.0
     */
    @NotNull
    @Column(name = "usb_30", nullable = false)
    private Integer usb30;

    /**
     * Liczba portów USB 3.1
     */
    @NotNull
    @Column(name = "usb_31", nullable = false)
    private Integer usb31;

    /**
     * Liczba portów USB 3.2
     */
    @NotNull
    @Column(name = "usb_32", nullable = false)
    private Integer usb32;

    /**
     * Liczba portów USB-C
     */
    @NotNull
    @Column(name = "usb_c", nullable = false)
    private Integer usbC;

    /**
     * Lista zmapowanych standardów płyt głównych kompatybilnych z obudową
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "case_mb_compatibility",
            joinColumns = @JoinColumn(name = "ean"),
            inverseJoinColumns = @JoinColumn(name = "standard_id")
    )
    private List<MotherboardStandard> supportedMbStandards;

    /**
     * Metoda pobierająca listę nazw standardów płyt głównych, kompatybilnych z obudową.
     *
     * @return Sformatowana lista zawierająca nazwy kompatybilnych standardów płyt głównych.
     */
    @Transient
    @JsonProperty(RequestParams.MOTHERBOARD_STANDARD)
    public List<String> getSupportedStandardNames() {
        return supportedMbStandards.stream()
                .map(MotherboardStandard::getStandardName)
                .collect(Collectors.toList());
    }
}
