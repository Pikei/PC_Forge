package com.pc_forge.backend.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ram", schema = "public")
public class Memory {
    @Id
    @Column(name = "ean", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Product product;

    @Column(name = "line", nullable = false, length = 200)
    private String line;

    @Column(name = "memory_type", nullable = false, length = 50)
    private String memoryType;

    @Column(name = "total_capacity", nullable = false)
    private Integer totalCapacity;

    @Column(name = "number_of_modules", nullable = false)
    private Integer numberOfModules;

    @Column(name = "latency", nullable = false, length = 50)
    private String latency;

    @Column(name = "lighting", nullable = false)
    private Boolean lighting = false;

    @Column(name = "frequency", nullable = false)
    private Integer frequency;

}
