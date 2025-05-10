package com.pc_forge.backend.model.entity.product.cooler;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji liquid_cooler z bazy danych. Dziedziczy ona z klasy {@link Cooler},
 * dodając do tego swoje własne pole.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "liquid_cooler", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("LIQUID_COOLER")
public final class LiquidCooler extends Cooler {

    /**
     * Rozmiar chłodnicy układu chłodzenia cieczą.
     */
    @NotNull
    @Column(name = "cooler_size", nullable = false)
    private Integer coolerSize;

}