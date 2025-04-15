package com.pc_forge.backend.model.database.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pc_case", schema = "public")
public class Case {
    @Id
    @Column(name = "ean", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Product product;

    @Column(name = "color", nullable = false, length = 200)
    private String color;

    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    @Column(name = "height", nullable = false)
    private Double height;

    @Column(name = "width", nullable = false)
    private Double width;

    @Column(name = "depth", nullable = false)
    private Double depth;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "case_type", nullable = false, length = 200)
    private String caseType;

    @Column(name = "has_window", nullable = false)
    private Boolean hasWindow = false;

    @Column(name = "card_reader", nullable = false)
    private Boolean cardReader = false;

    @Column(name = "microphone_connector", nullable = false)
    private Boolean microphoneConnector = false;

    @Column(name = "headphones_connector", nullable = false)
    private Boolean headphonesConnector = false;

    @Column(name = "internal_35_bays", nullable = false)
    private Integer internal35Bays;

    @Column(name = "internal_25_bays", nullable = false)
    private Integer internal25Bays;

    @Column(name = "external_35_bays", nullable = false)
    private Integer external35Bays;

    @Column(name = "external_525_bays", nullable = false)
    private Integer external525Bays;

    @Column(name = "max_gpu_length", nullable = false)
    private Double maxGpuLength;

    @Column(name = "max_cpu_cooler_height", nullable = false)
    private Double maxCpuCoolerHeight;

    @Column(name = "usb_20", nullable = false)
    private Integer usb20;

    @Column(name = "extension_slots", nullable = false)
    private Integer extensionSlots;

    @Column(name = "front_fans", nullable = false, length = 200)
    private String frontFans;

    @Column(name = "back_fans", nullable = false, length = 200)
    private String backFans;

    @Column(name = "side_fans", nullable = false, length = 200)
    private String sideFans;

    @Column(name = "bottom_fans", nullable = false, length = 200)
    private String bottomFans;

    @Column(name = "top_fans", nullable = false, length = 200)
    private String topFans;

    @Column(name = "power_supply", nullable = false)
    private Boolean powerSupply = false;

    @Column(name = "ps_power")
    private Integer psPower;

    @Column(name = "usb_30", nullable = false)
    private Integer usb30;

    @Column(name = "usb_31", nullable = false)
    private Integer usb31;

    @Column(name = "usb_32", nullable = false)
    private Integer usb32;

    @Column(name = "usb_c", nullable = false)
    private Integer usbC;

}
