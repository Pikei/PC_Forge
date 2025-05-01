package com.pc_forge.backend.model.entity.product.cooler;

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
@Table(name = "air_cooler", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("AIR_COOLER")
public final class AirCooler extends Cooler {

    @NotNull
    @Column(name = "vertical_installation", nullable = false)
    private Boolean verticalInstallation = false;

    @NotNull
    @Column(name = "height", nullable = false)
    private Integer height;

    @NotNull
    @Column(name = "width", nullable = false)
    private Integer width;

    @NotNull
    @Column(name = "depth", nullable = false)
    private Integer depth;

    @Size(max = 100)
    @NotNull
    @Column(name = "base_material", nullable = false, length = 100)
    private String baseMaterial;

    @NotNull
    @Column(name = "heat_pipes", nullable = false)
    private Integer heatPipes;

    @NotNull
    @Column(name = "heat_pipe_diameter", nullable = false)
    private Integer heatPipeDiameter;

}