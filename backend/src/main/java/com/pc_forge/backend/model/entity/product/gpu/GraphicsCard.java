package com.pc_forge.backend.model.entity.product.gpu;

import com.pc_forge.backend.model.entity.product.Product;
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
@Table(name = "graphics_card", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("GPU")
public final class GraphicsCard extends Product {
    @Size(max = 100)
    @NotNull
    @Column(name = "chipset_producer", nullable = false, length = 100)
    private String chipsetProducer;

    @Size(max = 200)
    @NotNull
    @Column(name = "chipset", nullable = false, length = 200)
    private String chipset;

    @NotNull
    @Column(name = "core_frequency", nullable = false)
    private Integer coreFrequency;

    @NotNull
    @Column(name = "max_core_frequency", nullable = false)
    private Integer maxCoreFrequency;

    @NotNull
    @Column(name = "stream_processors", nullable = false)
    private Integer streamProcessors;

    @NotNull
    @Column(name = "rop_units", nullable = false)
    private Integer ropUnits;

    @NotNull
    @Column(name = "texturing_units", nullable = false)
    private Integer texturingUnits;

    @NotNull
    @Column(name = "rt_cores", nullable = false)
    private Integer rtCores;

    @NotNull
    @Column(name = "tensor_cores", nullable = false)
    private Integer tensorCores;

    @Size(max = 100)
    @NotNull
    @Column(name = "dlss", nullable = false, length = 100)
    private String dlss;

    @Size(max = 200)
    @NotNull
    @Column(name = "connector", nullable = false, length = 200)
    private String connector;

    @NotNull
    @Column(name = "card_length", nullable = false)
    private Integer cardLength;

    @Size(max = 100)
    @NotNull
    @Column(name = "resolution", nullable = false, length = 100)
    private String resolution;

    @NotNull
    @Column(name = "recommended_ps_power", nullable = false)
    private Integer recommendedPsPower;

    @NotNull
    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    @NotNull
    @Column(name = "ram", nullable = false)
    private Integer ram;

    @Size(max = 100)
    @NotNull
    @Column(name = "ram_type", nullable = false, length = 100)
    private String ramType;

    @NotNull
    @Column(name = "data_bus", nullable = false)
    private Integer dataBus;

    @NotNull
    @Column(name = "memory_frequency", nullable = false)
    private Integer memoryFrequency;

    @Size(max = 200)
    @NotNull
    @Column(name = "cooling_type", nullable = false, length = 200)
    private String coolingType;

    @NotNull
    @Column(name = "number_of_fans", nullable = false)
    private Integer numberOfFans;

}