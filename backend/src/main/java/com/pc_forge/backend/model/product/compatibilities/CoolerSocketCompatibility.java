package com.pc_forge.backend.model.product.compatibilities;

import com.pc_forge.backend.model.product.Cooler;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cooler_socket_compatibility_id_gen")
    @SequenceGenerator(name = "cooler_socket_compatibility_id_gen", sequenceName = "cooler_socket_compatibility_compatibility_id_seq", allocationSize = 1)
    @Column(name = "compatibility_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Cooler ean;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "socket_id", nullable = false)
    private CpuSocket socket;

}