package com.pc_forge.backend.model.product.compatibilities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "motherboard_standard", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "motherboard_standard_standard_id_key", columnNames = {"standard_id"})
})
public class MotherboardStandard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motherboard_standard_id_gen")
    @SequenceGenerator(name = "motherboard_standard_id_gen", sequenceName = "motherboard_standard_standard_id_seq", allocationSize = 1)
    @Column(name = "standard_id", nullable = false)
    private Integer id;

    @Column(name = "standard_name", nullable = false, length = 100)
    private String standardName;

}