package com.pc_forge.backend.model.entity.compatibility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji motherboard_standard z bazy danych.
 * Zawiera unikalny identyfikator standardu płyty głównej oraz jego nazwę.
 */
@Getter
@Setter
@Entity
@Table(name = "motherboard_standard", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "motherboard_standard_standard_id_key", columnNames = {"standard_id"})
})
public class MotherboardStandard {
    /**
     * Identyfikator standardu płyty głównej
     */
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "standard_id", nullable = false)
    private Long id;

    /**
     * Nazwa standardu płyty głównej
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "standard_name", nullable = false, length = 100)
    private String standardName;

}