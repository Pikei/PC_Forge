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
@Table(name = "graphics_card", schema = "public")
public class GraphicsCard {
    @Id
    @Column(name = "ean", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Product product;

    @Column(name = "chipset_producer", nullable = false, length = 100)
    private String chipsetProducer;

    @Column(name = "chipset", nullable = false, length = 200)
    private String chipset;

    @Column(name = "core_frequency", nullable = false)
    private Integer coreFrequency;

    @Column(name = "max_core_frequency", nullable = false)
    private Integer maxCoreFrequency;

    @Column(name = "stream_processors", nullable = false)
    private Integer streamProcessors;

    @Column(name = "rop_units", nullable = false)
    private Integer ropUnits;

    @Column(name = "texturing_units", nullable = false)
    private Integer texturingUnits;

    @Column(name = "rt_cores", nullable = false)
    private Integer rtCores;

    @Column(name = "tensor_cores", nullable = false)
    private Integer tensorCores;

    @Column(name = "dlss", nullable = false, length = 100)
    private String dlss;

    @Column(name = "connector", nullable = false, length = 200)
    private String connector;

    @Column(name = "card_length", nullable = false)
    private Integer cardLength;

    @Column(name = "resolution", nullable = false, length = 100)
    private String resolution;

    @Column(name = "recommended_ps_power", nullable = false)
    private Integer recommendedPsPower;

    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    @Column(name = "ram", nullable = false)
    private Integer ram;

    @Column(name = "ram_type", nullable = false, length = 100)
    private String ramType;

    @Column(name = "data_bus", nullable = false)
    private Integer dataBus;

    @Column(name = "memory_frequency", nullable = false)
    private Integer memoryFrequency;

    @Column(name = "cooling_type", nullable = false, length = 200)
    private String coolingType;

    @Column(name = "number_of_fans", nullable = false)
    private Integer numberOfFans;

}
