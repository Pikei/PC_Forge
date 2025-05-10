package com.pc_forge.backend.model.entity.product.drive;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji hdd z bazy danych. Dziedziczy ona z klasy {@link Drive},
 * dodając własne pole.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hdd", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("HDD")
public final class HardDiskDrive extends Drive {
    /**
     * Prędkość obrotowa dysku
     */
    @NotNull
    @Column(name = "rotational_speed", nullable = false)
    private Integer rotationalSpeed;
}