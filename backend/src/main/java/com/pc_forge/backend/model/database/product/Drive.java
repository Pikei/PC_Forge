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
@Table(name = "drive", schema = "public")
public class Drive {
    @Id
    @Column(name = "ean", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Product product;

    @Column(name = "drive_format", nullable = false, length = 100)
    private String driveFormat;

    @Column(name = "storage", nullable = false)
    private Integer storage;

    @Column(name = "drive_interface", nullable = false, length = Integer.MAX_VALUE)
    private String driveInterface;

}
