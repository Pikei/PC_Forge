package com.pc_forge.backend.model.product;

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
@Table(name = "cooler", schema = "public")
public class Cooler {
    @Id
    @Column(name = "ean", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Product product;

    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    @Column(name = "fans", nullable = false)
    private Integer fans;

    @Column(name = "fan_diameter", nullable = false)
    private Integer fanDiameter;

    @Column(name = "fan_speed")
    private Integer fanSpeed;

    @Column(name = "noise_level")
    private Double noiseLevel;

}
