package com.pc_forge.backend.model.entity.compatibility;

import com.pc_forge.backend.model.entity.product.pc_case.Case;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji case_mb_compatibility z bazy danych. Zawiera informacje o tym,
 * jakie standardy płyt głównych są kompatybilne, z jakimi obudowani.
 */
@Getter
@Setter
@Entity
@Table(name = "case_mb_compatibility", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "case_mb_compatibility_compatibility_id_key", columnNames = {"compatibility_id"})
})
public class CaseMbCompatibility {
    /**
     * Identyfikator kompatybilności
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compatibility_id", nullable = false)
    private Long id;

    /**
     * Standard płyty głównej
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "standard_id", nullable = false)
    private MotherboardStandard standard;

    /**
     * Obiekt obudowy kompatybilny ze standardem płyty głównej
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Case ean;

}