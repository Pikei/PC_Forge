package com.pc_forge.backend.model.entity.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.model.entity.product.cooler.Cooler;
import com.pc_forge.backend.model.entity.product.cpu.Processor;
import com.pc_forge.backend.model.entity.product.drive.HardDiskDrive;
import com.pc_forge.backend.model.entity.product.drive.SolidStateDrive;
import com.pc_forge.backend.model.entity.product.gpu.GraphicsCard;
import com.pc_forge.backend.model.entity.product.mb.Motherboard;
import com.pc_forge.backend.model.entity.product.pc_case.Case;
import com.pc_forge.backend.model.entity.product.ps.PowerSupply;
import com.pc_forge.backend.model.entity.product.ram.Memory;
import com.pc_forge.backend.model.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "config_id", nullable = false)
    private Long id;

    @Column(name = "config_name")
    private String configName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gpu_id")
    private GraphicsCard graphicsCard;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "case_id", nullable = false)
    private Case pcCase;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mb_id", nullable = false)
    private Motherboard motherboard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cooler_id")
    private Cooler cooler;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ps_id")
    private PowerSupply powerSupply;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ram_id", nullable = false)
    private Memory memory;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cpu_id", nullable = false)
    private Processor processor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ssd_id")
    private SolidStateDrive solidStateDrive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hdd_id")
    private HardDiskDrive hardDiskDrive;

    @Transient
    @JsonProperty("totalPrice")
    public Double getTotalPrice() {
        Double totalPrice = 0.0;
        totalPrice += graphicsCard.getPrice();
        totalPrice += pcCase.getPrice();
        totalPrice += motherboard.getPrice();
        totalPrice += cooler.getPrice();
        totalPrice += powerSupply.getPrice();
        totalPrice += memory.getPrice();
        totalPrice += processor.getPrice();
        totalPrice += solidStateDrive.getPrice();
        totalPrice += hardDiskDrive.getPrice();
        return totalPrice;
    }

}