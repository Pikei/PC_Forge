package com.pc_forge.backend.model.entity.product.cooler;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji air_cooler z bazy danych. Dziedziczy ona z klasy {@link Cooler},
 * dodając do tego swoje własne pola.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "air_cooler", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("AIR_COOLER")
public final class AirCooler extends Cooler {

    /**
     * Flaga określająca, czy sposób instalacji układu chłodzenia jest wertykalny (pionowy) czy nie
     */
    @NotNull
    @Column(name = "vertical_installation", nullable = false)
    private Boolean verticalInstallation = false;

    /**
     * Wysokość układu chłodzenia
     */
    @NotNull
    @Column(name = "height", nullable = false)
    private Integer height;

    /**
     * Szerokość układu chłodzenia
     */
    @NotNull
    @Column(name = "width", nullable = false)
    private Integer width;

    /**
     * Głębokość układu chłodzenia
     */
    @NotNull
    @Column(name = "depth", nullable = false)
    private Integer depth;

    /**
     * Materiał podstawy
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "base_material", nullable = false, length = 100)
    private String baseMaterial;

    /**
     * Liczba ciepłowodów
     */
    @NotNull
    @Column(name = "heat_pipes", nullable = false)
    private Integer heatPipes;

    /**
     * Średnica ciepłowodów
     */
    @NotNull
    @Column(name = "heat_pipe_diameter", nullable = false)
    private Integer heatPipeDiameter;

}