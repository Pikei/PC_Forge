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
@Table(name = "ram", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("RAM")
public final class Memory extends Product {
    @Size(max = 200)
    @NotNull
    @Column(name = "line", nullable = false, length = 200)
    private String line;

    @Size(max = 50)
    @NotNull
    @Column(name = "memory_type", nullable = false, length = 50)
    private String memoryType;

    @NotNull
    @Column(name = "total_capacity", nullable = false)
    private Integer totalCapacity;

    @NotNull
    @Column(name = "number_of_modules", nullable = false)
    private Integer numberOfModules;

    @Size(max = 50)
    @NotNull
    @Column(name = "latency", nullable = false, length = 50)
    private String latency;

    @NotNull
    @Column(name = "lighting", nullable = false)
    private Boolean lighting = false;

    @NotNull
    @Column(name = "frequency", nullable = false)
    private Integer frequency;

}