package com.pc_forge.backend.model.entity.product.ps;

import com.pc_forge.backend.model.entity.product.Product;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

/**
 * Klasa będąca reprezentacją encji power_supply z bazy danych. Dziedziczy ona z klasy {@link Product},
 * dodając swoje własne pola.
 */
@Getter
@Setter
@Entity
@Table(name = "power_supply", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("PS")
public final class PowerSupply extends Product {
    /**
     * Standard zasilacza
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "standard", nullable = false, length = 100)
    private String standard;

    /**
     * Moc zasilacza w watach
     */
    @NotNull
    @Column(name = "power", nullable = false)
    private Integer power;

    /**
     * Certyfikat sprawności zasilacza
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "efficiency_certificate", nullable = false, length = 200)
    private String efficiencyCertificate;

    /**
     * Sprawność procentowa zasilacza
     */
    @NotNull
    @Column(name = "efficiency", nullable = false)
    private Integer efficiency;

    /**
     * Typ chłodzenia zasilacza
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "cooling_type", nullable = false, length = 200)
    private String coolingType;

    /**
     * Średnica wiatraków zasilacza
     */
    @NotNull
    @Column(name = "fan_diameter", nullable = false)
    private Integer fanDiameter;

    /**
     * Flaga określająca, czy zasilacz ma modularne okablowanie
     */
    @NotNull
    @Column(name = "modular_cabling", nullable = false)
    private Boolean modularCabling = false;

    /**
     * Liczba złącz ATX-24 pin
     */
    @NotNull
    @Column(name = "atx24", nullable = false)
    private Integer atx24;

    /**
     * Liczba złącz PCI-Express x16
     */
    @NotNull
    @Column(name = "pcie16", nullable = false)
    private Integer pcie16;

    /**
     * Liczba złącz PCI-Express 8 (8 i 4+4)
     */
    @NotNull
    @Column(name = "pcie8", nullable = false)
    private Integer pcie8;

    /**
     * Liczba złącz PCI-Express 6
     */
    @NotNull
    @Column(name = "pcie6", nullable = false)
    private Integer pcie6;

    /**
     * Liczba złącz CPU 8 pin
     */
    @NotNull
    @Column(name = "cpu8", nullable = false)
    private Integer cpu8;

    /**
     * Liczba złącz CPU 4 pin
     */
    @NotNull
    @Column(name = "cpu4", nullable = false)
    private Integer cpu4;

    /**
     * Liczba złącz SATA
     */
    @NotNull
    @Column(name = "sata", nullable = false)
    private Integer sata;

    /**
     * Liczba złącz Molex
     */
    @NotNull
    @Column(name = "molex", nullable = false)
    private Integer molex;

    /**
     * Wysokość zasilacza
     */
    @NotNull
    @Column(name = "height", nullable = false)
    private Integer height;

    /**
     * Szerokość zasilacza
     */
    @NotNull
    @Column(name = "width", nullable = false)
    private Integer width;

    /**
     * Głębokość zasilacza
     */
    @NotNull
    @Column(name = "depth", nullable = false)
    private Integer depth;

    /**
     * Flaga określająca, czy zasilacz ma podświetlenie
     */
    @NotNull
    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    /**
     * Lista zabezpieczeń zasilacza
     */
    @NotNull
    @Type(ListArrayType.class)
    @Column(name = "protections", nullable = false, columnDefinition = "_varchar (Types#ARRAY)")
    private List<String> protections;

}
