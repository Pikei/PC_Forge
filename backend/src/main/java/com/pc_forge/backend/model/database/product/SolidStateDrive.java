package com.pc_forge.backend.model.database.product;

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
@Table(name = "ssd", schema = "public")
public class SolidStateDrive {
    @Id
    @Column(name = "ean", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Drive drive;

    @Column(name = "read_speed", nullable = false)
    private Integer readSpeed;

    @Column(name = "write_speed", nullable = false)
    private Integer writeSpeed;

}
