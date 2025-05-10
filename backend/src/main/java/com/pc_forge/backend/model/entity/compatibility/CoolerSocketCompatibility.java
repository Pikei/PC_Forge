package com.pc_forge.backend.model.entity.compatibility;

import com.pc_forge.backend.model.entity.product.cooler.Cooler;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji cooler_socket_compatibility z bazy danych. Zawiera informacje o tym,
 * Jakie układy chłodzenia są kompatybilne, z jakimi gniazdami procesora.
 */
@Getter
@Setter
@Entity
@Table(name = "cooler_socket_compatibility", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "cooler_socket_compatibility_compatibility_id_key", columnNames = {"compatibility_id"})
})
public class CoolerSocketCompatibility {
    /**
     * Identyfikator kompatybilności
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compatibility_id", nullable = false)
    private Long id;

    /**
     * Obiekt układu chłodzenia kompatybilny z gniazdem procesora
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Cooler ean;

    /**
     * Obiekt gniazda procesora
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "socket_id", nullable = false)
    private CpuSocket socket;
}
