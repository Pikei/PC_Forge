package com.pc_forge.backend.model.database.product;

import com.pc_forge.backend.model.database.product.compatibility.CpuSocket;
import com.pc_forge.backend.model.database.product.compatibility.MotherboardStandard;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "motherboard", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("MB")
public final class Motherboard extends Product {
    @Size(max = 100)
    @NotNull
    @Column(name = "chipset", nullable = false, length = 100)
    private String chipset;

    @Size(max = 100)
    @NotNull
    @Column(name = "memory_standard", nullable = false, length = 100)
    private String memoryStandard;

    @NotNull
    @Column(name = "memory_slots", nullable = false)
    private Integer memorySlots;

    @NotNull
    @Column(name = "max_memory_capacity", nullable = false)
    private Integer maxMemoryCapacity;

    @Size(max = 200)
    @Column(name = "integrated_audio_card", length = 200)
    private String integratedAudioCard;

    @NotNull
    @Column(name = "audio_channels", nullable = false)
    private Double audioChannels;

    @Size(max = 200)
    @NotNull
    @Column(name = "integrated_network_card", nullable = false, length = 200)
    private String integratedNetworkCard;

    @NotNull
    @Column(name = "bluetooth", nullable = false)
    private Boolean bluetooth = false;

    @NotNull
    @Column(name = "wifi", nullable = false)
    private Boolean wifi = false;

    @NotNull
    @Column(name = "width", nullable = false)
    private Double width;

    @NotNull
    @Column(name = "depth", nullable = false)
    private Double depth;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "standard_id", nullable = false)
    private MotherboardStandard standard;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "socket_id", nullable = false)
    private CpuSocket socket;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "expansion_slots", nullable = false, columnDefinition = "varchar[]")
    private List<String> expansionSlots;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "drive_interfaces", nullable = false, columnDefinition = "varchar[]")
    private List<String> driveInterfaces;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "outside_connectors", nullable = false, columnDefinition = "varchar[]")
    private List<String> outsideConnectors;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "supported_memory_frequencies", nullable = false, columnDefinition = "int[]")
    private List<Integer> supportedMemoryFrequencies;

}