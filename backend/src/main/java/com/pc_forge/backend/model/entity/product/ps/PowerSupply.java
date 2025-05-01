package com.pc_forge.backend.model.entity.product.ps;

import com.pc_forge.backend.model.entity.product.Product;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "power_supply", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("PS")
public final class PowerSupply extends Product {
    @Size(max = 100)
    @NotNull
    @Column(name = "standard", nullable = false, length = 100)
    private String standard;

    @NotNull
    @Column(name = "power", nullable = false)
    private Integer power;

    @Size(max = 200)
    @NotNull
    @Column(name = "efficiency_certificate", nullable = false, length = 200)
    private String efficiencyCertificate;

    @NotNull
    @Column(name = "efficiency", nullable = false)
    private Integer efficiency;

    @Size(max = 200)
    @NotNull
    @Column(name = "cooling_type", nullable = false, length = 200)
    private String coolingType;

    @NotNull
    @Column(name = "fan_diameter", nullable = false)
    private Integer fanDiameter;

    @NotNull
    @Column(name = "modular_cabling", nullable = false)
    private Boolean modularCabling = false;

    @NotNull
    @Column(name = "atx24", nullable = false)
    private Integer atx24;

    @NotNull
    @Column(name = "pcie16", nullable = false)
    private Integer pcie16;

    @NotNull
    @Column(name = "pcie8", nullable = false)
    private Integer pcie8;

    @NotNull
    @Column(name = "pcie6", nullable = false)
    private Integer pcie6;

    @NotNull
    @Column(name = "cpu8", nullable = false)
    private Integer cpu8;

    @NotNull
    @Column(name = "cpu4", nullable = false)
    private Integer cpu4;

    @NotNull
    @Column(name = "sata", nullable = false)
    private Integer sata;

    @NotNull
    @Column(name = "molex", nullable = false)
    private Integer molex;

    @NotNull
    @Column(name = "height", nullable = false)
    private Integer height;

    @NotNull
    @Column(name = "width", nullable = false)
    private Integer width;

    @NotNull
    @Column(name = "depth", nullable = false)
    private Integer depth;

    @NotNull
    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    @NotNull
    @Type(ListArrayType.class)
    @Column(name = "protections", nullable = false, columnDefinition = "_varchar (Types#ARRAY)")
    private List<String> protections;

}