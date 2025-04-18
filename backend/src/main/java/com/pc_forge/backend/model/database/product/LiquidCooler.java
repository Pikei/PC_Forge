package com.pc_forge.backend.model.database.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "liquid_cooler", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("LIQUID_COOLER")
public final class LiquidCooler extends Cooler {

    @NotNull
    @Column(name = "cooler_size", nullable = false)
    private Integer coolerSize;

}