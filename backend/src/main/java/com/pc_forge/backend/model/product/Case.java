package com.pc_forge.backend.model.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pc_case", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("CASE")
public final class Case extends Product {

    @Size(max = 200)
    @NotNull
    @Column(name = "color", nullable = false, length = 200)
    private String color;

    @NotNull
    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    @NotNull
    @Column(name = "height", nullable = false)
    private Double height;

    @NotNull
    @Column(name = "width", nullable = false)
    private Double width;

    @NotNull
    @Column(name = "depth", nullable = false)
    private Double depth;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Double weight;

    @Size(max = 200)
    @NotNull
    @Column(name = "case_type", nullable = false, length = 200)
    private String caseType;

    @NotNull
    @Column(name = "has_window", nullable = false)
    private Boolean hasWindow = false;

    @NotNull
    @Column(name = "card_reader", nullable = false)
    private Boolean cardReader = false;

    @NotNull
    @Column(name = "microphone_connector", nullable = false)
    private Boolean microphoneConnector = false;

    @NotNull
    @Column(name = "headphones_connector", nullable = false)
    private Boolean headphonesConnector = false;

    @NotNull
    @Column(name = "internal_35_bays", nullable = false)
    private Integer internal35Bays;

    @NotNull
    @Column(name = "internal_25_bays", nullable = false)
    private Integer internal25Bays;

    @NotNull
    @Column(name = "external_35_bays", nullable = false)
    private Integer external35Bays;

    @NotNull
    @Column(name = "external_525_bays", nullable = false)
    private Integer external525Bays;

    @NotNull
    @Column(name = "max_gpu_length", nullable = false)
    private Double maxGpuLength;

    @NotNull
    @Column(name = "max_cpu_cooler_height", nullable = false)
    private Double maxCpuCoolerHeight;

    @NotNull
    @Column(name = "usb_20", nullable = false)
    private Integer usb20;

    @NotNull
    @Column(name = "extension_slots", nullable = false)
    private Integer extensionSlots;

    @Size(max = 200)
    @NotNull
    @Column(name = "front_fans", nullable = false, length = 200)
    private String frontFans;

    @Size(max = 200)
    @NotNull
    @Column(name = "back_fans", nullable = false, length = 200)
    private String backFans;

    @Size(max = 200)
    @NotNull
    @Column(name = "side_fans", nullable = false, length = 200)
    private String sideFans;

    @Size(max = 200)
    @NotNull
    @Column(name = "bottom_fans", nullable = false, length = 200)
    private String bottomFans;

    @Size(max = 200)
    @NotNull
    @Column(name = "top_fans", nullable = false, length = 200)
    private String topFans;

    @NotNull
    @Column(name = "power_supply", nullable = false)
    private Boolean powerSupply = false;

    @Column(name = "ps_power")
    private Integer psPower;

    @NotNull
    @Column(name = "usb_30", nullable = false)
    private Integer usb30;

    @NotNull
    @Column(name = "usb_31", nullable = false)
    private Integer usb31;

    @NotNull
    @Column(name = "usb_32", nullable = false)
    private Integer usb32;

    @NotNull
    @Column(name = "usb_c", nullable = false)
    private Integer usbC;

}