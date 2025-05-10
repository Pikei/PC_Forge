package com.pc_forge.backend.model.entity.product.ram;

import com.pc_forge.backend.model.entity.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji ram z bazy danych. Dziedziczy ona z klasy {@link Product},
 * dodając swoje własne pola.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ram", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("RAM")
public final class Memory extends Product {
    /**
     * Linia pamięci RAM
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "line", nullable = false, length = 200)
    private String line;

    /**
     * Typ pamięci RAM
     */
    @Size(max = 50)
    @NotNull
    @Column(name = "memory_type", nullable = false, length = 50)
    private String memoryType;

    /**
     * Pojemność łączna zestawu
     */
    @NotNull
    @Column(name = "total_capacity", nullable = false)
    private Integer totalCapacity;

    /**
     * Liczba modułów w zestawie
     */
    @NotNull
    @Column(name = "number_of_modules", nullable = false)
    private Integer numberOfModules;

    /**
     * Opóźnienie zegara pamięci operacyjnej
     */
    @Size(max = 50)
    @NotNull
    @Column(name = "latency", nullable = false, length = 50)
    private String latency;

    /**
     * Flaga określająca czy, pamięć RAM posiada podświetlenie
     */
    @NotNull
    @Column(name = "lighting", nullable = false)
    private Boolean lighting = false;

    /**
     * Częstotliwość taktowania pamięci RAM
     */
    @NotNull
    @Column(name = "frequency", nullable = false)
    private Integer frequency;

}
