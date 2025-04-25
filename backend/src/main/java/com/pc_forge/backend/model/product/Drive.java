package com.pc_forge.backend.model.product;

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
@Table(name = "drive", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "ean")

public abstract class Drive extends Product {
    @Size(max = 100)
    @NotNull
    @Column(name = "drive_format", nullable = false, length = 100)
    private String driveFormat;

    @NotNull
    @Column(name = "storage", nullable = false)
    private Integer storage;

    @NotNull
    @Column(name = "drive_interface", nullable = false, length = Integer.MAX_VALUE)
    private String driveInterface;

}