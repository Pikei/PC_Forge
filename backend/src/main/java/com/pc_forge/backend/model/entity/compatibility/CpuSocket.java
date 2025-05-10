package com.pc_forge.backend.model.entity.compatibility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji cpu_socket z bazy danych.
 * Zawiera unikalny identyfikator gniazda procesora oraz nazwę gniazda.
 */
@Getter
@Setter
@Entity
@Table(name = "cpu_socket", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "cpu_socket_socket_key", columnNames = {"socket"})
})
public class CpuSocket {
    /**
     * Identyfikator gniazda procesora.
     */
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "socket_id", nullable = false)
    private Long socketId;

    /**
     * Nazwa gniazda procesora.
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "socket", nullable = false, length = 100)
    private String socketName;

}