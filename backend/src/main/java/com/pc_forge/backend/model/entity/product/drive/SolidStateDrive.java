package com.pc_forge.backend.model.entity.product.drive;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji ssd z bazy danych. Dziedziczy ona z klasy {@link Drive},
 * dodając swoje własne pola.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ssd", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("SSD")
public final class SolidStateDrive extends Drive {
    /**
     * Prędkość odczytu dysku
     */
    @NotNull
    @Column(name = "read_speed", nullable = false)
    private Integer readSpeed;

    /**
     * Prędkość zapisu dysku
     */
    @NotNull
    @Column(name = "write_speed", nullable = false)
    private Integer writeSpeed;

}