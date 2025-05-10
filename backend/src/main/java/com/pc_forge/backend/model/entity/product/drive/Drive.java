package com.pc_forge.backend.model.entity.product.drive;

import com.pc_forge.backend.model.entity.product.Product;
import com.pc_forge.backend.model.entity.product.cooler.AirCooler;
import com.pc_forge.backend.model.entity.product.cooler.LiquidCooler;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa abstrakcyjna dziedzicząca po {@link Product}, będąca reprezentacją encji drive z bazy danych.
 * Jest to klasa bazowa dla klasy {@link SolidStateDrive} oraz {@link HardDiskDrive} i zawiera informacje wspólne
 * dla obydwu typów dysków twardych.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "drive", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "ean")

public abstract class Drive extends Product {
    /**
     * Format dysku
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "drive_format", nullable = false, length = 100)
    private String driveFormat;

    /**
     * Pojemność dysku w GB
     */
    @NotNull
    @Column(name = "storage", nullable = false)
    private Integer storage;

    /**
     * Interfejs dysku
     */
    @NotNull
    @Column(name = "drive_interface", nullable = false, length = Integer.MAX_VALUE)
    private String driveInterface;

}