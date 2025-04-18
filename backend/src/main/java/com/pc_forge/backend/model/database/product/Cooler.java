package com.pc_forge.backend.model.database.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cooler", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cooler extends Product {
    @NotNull
    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    @NotNull
    @Column(name = "fans", nullable = false)
    private Integer fans;

    @NotNull
    @Column(name = "fan_diameter", nullable = false)
    private Integer fanDiameter;

    @Column(name = "fan_speed")
    private Integer fanSpeed;

    @Column(name = "noise_level")
    private Double noiseLevel;

}