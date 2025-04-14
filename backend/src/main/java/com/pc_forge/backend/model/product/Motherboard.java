package com.pc_forge.backend.model.product;

import com.pc_forge.backend.model.product.compatibilities.CpuSocket;
import com.pc_forge.backend.model.product.compatibilities.MotherboardStandard;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "motherboard", schema = "public")
public class Motherboard {
    @Id
    @Column(name = "ean", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ean", nullable = false)
    private Product product;

    @Column(name = "chipset", nullable = false, length = 100)
    private String chipset;

    @Column(name = "memory_standard", nullable = false, length = 100)
    private String memoryStandard;

    @Column(name = "memory_slots", nullable = false)
    private Integer memorySlots;

    @Column(name = "max_memory_capacity", nullable = false)
    private Integer maxMemoryCapacity;

    @Column(name = "integrated_audio_card", length = 200)
    private String integratedAudioCard;

    @Column(name = "audio_channels", nullable = false)
    private Double audioChannels;

    @Column(name = "integrated_network_card", nullable = false, length = 200)
    private String integratedNetworkCard;

    @Column(name = "bluetooth", nullable = false)
    private Boolean bluetooth = false;

    @Column(name = "wifi", nullable = false)
    private Boolean wifi = false;

    @Column(name = "width", nullable = false)
    private Double width;

    @Column(name = "depth", nullable = false)
    private Double depth;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "standard_id", nullable = false)
    private MotherboardStandard standard;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "socket_id", nullable = false)
    private CpuSocket socket;

    @Type(value = StringArrayType.class)
    @Column(name = "expansion_slots", nullable = false, columnDefinition = "varchar[]")
    private List<String> expansionSlots;

    @Type(value = StringArrayType.class)
    @Column(name = "drive_interfaces", nullable = false, columnDefinition = "varchar[]")
    private List<String> driveInterfaces;

    @Type(value = StringArrayType.class)
    @Column(name = "outside_connectors", nullable = false, columnDefinition = "varchar[]")
    private List<String> outsideConnectors;

    @Type(value = IntArrayType.class)
    @Column(name = "supported_memory_frequencies", nullable = false, columnDefinition = "int[]")
    private List<Integer> supportedMemoryFrequencies;

}
