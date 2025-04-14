package com.pc_forge.backend.model.user;

import com.pc_forge.backend.model.product.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "configuration", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "configuration_config_id_key", columnNames = {"config_id"})
})
public class Configuration {
    @Id
    @Column(name = "config_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gpu_id")
    private GraphicsCard gpu;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "case_id", nullable = false)
    private Case caseField;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mb_id", nullable = false)
    private Motherboard mb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cooler_id")
    private Cooler cooler;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ps_id")
    private PowerSupply ps;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ram_id", nullable = false)
    private Memory ram;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cpu_id", nullable = false)
    private Processor cpu;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserData user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ssd_id")
    private SolidStateDrive ssd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hdd_id")
    private HardDiskDrive hdd;

}