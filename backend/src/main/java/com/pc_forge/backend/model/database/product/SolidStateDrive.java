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
@Table(name = "ssd", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("SSD")
public final class SolidStateDrive extends Drive {
    @NotNull
    @Column(name = "read_speed", nullable = false)
    private Integer readSpeed;

    @NotNull
    @Column(name = "write_speed", nullable = false)
    private Integer writeSpeed;

}