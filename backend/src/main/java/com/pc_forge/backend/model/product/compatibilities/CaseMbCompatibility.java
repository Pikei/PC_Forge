package com.pc_forge.backend.model.product.compatibilities;

import com.pc_forge.backend.model.product.Case;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "case_mb_compatibility", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "case_mb_compatibility_compatibility_id_key", columnNames = {"compatibility_id"})
})
public class CaseMbCompatibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compatibility_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "standard_id", nullable = false)
    private MotherboardStandard standard;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Case ean;

}