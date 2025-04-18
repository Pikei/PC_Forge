package com.pc_forge.backend.model.database.product.compatibility;

import com.pc_forge.backend.model.database.product.Case;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "standard_id", nullable = false)
    private com.pc_forge.backend.model.database.product.compatibility.MotherboardStandard standard;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Case ean;

}