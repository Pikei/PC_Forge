package com.pc_forge.backend.model.database.product.compatibilities;

import com.pc_forge.backend.model.database.product.Cooler;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cooler_socket_compatibility", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "cooler_socket_compatibility_compatibility_id_key", columnNames = {"compatibility_id"})
})
public class CoolerSocketCompatibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compatibility_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Cooler ean;

}