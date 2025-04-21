package com.pc_forge.backend.model.database.product;

import com.pc_forge.backend.model.database.product.compatibility.CpuSocket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "processor", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("CPU")
public final class Processor extends Product {
    @NotNull
    @Column(name = "line", nullable = false, length = Integer.MAX_VALUE)
    private String line;

    @Size(max = 200)
    @NotNull
    @Column(name = "model", nullable = false, length = 200)
    private String model;

    @NotNull
    @Column(name = "cores", nullable = false)
    private Integer cores;

    @NotNull
    @Column(name = "threads", nullable = false)
    private Integer threads;

    @NotNull
    @Column(name = "unlocked", nullable = false)
    private Boolean unlocked = false;

    @NotNull
    @Column(name = "frequency", nullable = false)
    private Double frequency;

    @NotNull
    @Column(name = "max_frequency", nullable = false)
    private Double maxFrequency;

    @Column(name = "integrated_graphics_unit", length = Integer.MAX_VALUE)
    private String integratedGraphicsUnit;

    @NotNull
    @Column(name = "tdp", nullable = false)
    private Integer tdp;

    @NotNull
    @Column(name = "cooler_included", nullable = false)
    private Boolean coolerIncluded = false;

    @Size(max = 10)
    @NotNull
    @Column(name = "packaging", nullable = false, length = 10)
    private String packaging;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "socket_id", nullable = false)
    private CpuSocket socket;

}