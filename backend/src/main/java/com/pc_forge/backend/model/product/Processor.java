package com.pc_forge.backend.model.product;

import com.pc_forge.backend.model.product.compatibilities.CpuSocket;
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
@Table(name = "processor", schema = "public")
public class Processor {
    @Id
    @Column(name = "ean", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Product product;

    @Column(name = "line", nullable = false, length = Integer.MAX_VALUE)
    private String line;

    @Column(name = "model", nullable = false, length = 200)
    private String model;

    @Column(name = "cores", nullable = false)
    private Integer cores;

    @Column(name = "threads", nullable = false)
    private Integer threads;

    @Column(name = "unlocked", nullable = false)
    private Boolean unlocked = false;

    @Column(name = "frequency", nullable = false)
    private Double frequency;

    @Column(name = "max_frequency", nullable = false)
    private Double maxFrequency;

    @Column(name = "integrated_graphics_unit", length = Integer.MAX_VALUE)
    private String integratedGraphicsUnit;

    @Column(name = "tdp", nullable = false)
    private Integer tdp;

    @Column(name = "cooler_included", nullable = false)
    private Boolean coolerIncluded = false;

    @Column(name = "packaging", nullable = false, length = 10)
    private String packaging;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "socket_id", nullable = false)
    private CpuSocket socket;

}
