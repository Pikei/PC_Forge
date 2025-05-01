package com.pc_forge.backend.model.entity.product.drive;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hdd", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("HDD")
public final class HardDiskDrive extends Drive {
    @NotNull
    @Column(name = "rotational_speed", nullable = false)
    private Integer rotationalSpeed;
}