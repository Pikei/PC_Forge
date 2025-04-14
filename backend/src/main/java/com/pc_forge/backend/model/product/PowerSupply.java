package com.pc_forge.backend.model.product;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "power_supply", schema = "public")
public class PowerSupply {
    @Id
    @Column(name = "ean", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Product product;

    @Column(name = "standard", nullable = false, length = 100)
    private String standard;

    @Column(name = "power", nullable = false)
    private Integer power;

    @Column(name = "efficiency_certificate", nullable = false, length = 200)
    private String efficiencyCertificate;

    @Column(name = "efficiency", nullable = false)
    private Integer efficiency;

    @Column(name = "cooling_type", nullable = false, length = 200)
    private String coolingType;

    @Column(name = "fan_diameter", nullable = false)
    private Integer fanDiameter;

    @Column(name = "modular_cabling", nullable = false)
    private Boolean modularCabling = false;

    @Column(name = "atx24", nullable = false)
    private Integer atx24;

    @Column(name = "pcie16", nullable = false)
    private Integer pcie16;

    @Column(name = "pcie8", nullable = false)
    private Integer pcie8;

    @Column(name = "pcie6", nullable = false)
    private Integer pcie6;

    @Column(name = "cpu8", nullable = false)
    private Integer cpu8;

    @Column(name = "cpu4", nullable = false)
    private Integer cpu4;

    @Column(name = "sata", nullable = false)
    private Integer sata;

    @Column(name = "molex", nullable = false)
    private Integer molex;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "width", nullable = false)
    private Integer width;

    @Column(name = "depth", nullable = false)
    private Integer depth;

    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    @Type(value = StringArrayType.class)
    @Column(name = "protections", nullable = false, columnDefinition = "varchar[]")
    private List<String> protections;

}
