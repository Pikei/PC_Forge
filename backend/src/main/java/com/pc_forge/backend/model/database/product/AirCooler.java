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
@Table(name = "air_cooler", schema = "public")
public class AirCooler {
    @Id
    @Column(name = "ean", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Cooler cooler;

    @Column(name = "vertical_installation", nullable = false)
    private Boolean verticalInstallation = false;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "width", nullable = false)
    private Integer width;

    @Column(name = "depth", nullable = false)
    private Integer depth;

    @Column(name = "base_material", nullable = false, length = 100)
    private String baseMaterial;

    @Column(name = "heat_pipes", nullable = false)
    private Integer heatPipes;

    @Column(name = "heat_pipe_diameter", nullable = false)
    private Integer heatPipeDiameter;

}
