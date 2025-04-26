package com.pc_forge.backend.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import com.pc_forge.backend.model.product.compatibility.CpuSocket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "cooler", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cooler extends Product {
    @NotNull
    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    @NotNull
    @Column(name = "fans", nullable = false)
    private Integer fans;

    @NotNull
    @Column(name = "fan_diameter", nullable = false)
    private Integer fanDiameter;

    @Column(name = "fan_speed")
    private Integer fanSpeed;

    @Column(name = "noise_level")
    private Double noiseLevel;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "cooler_socket_compatibility",
            joinColumns = @JoinColumn(name = "ean"),
            inverseJoinColumns = @JoinColumn(name = "socket_id")
    )
    private List<CpuSocket> compatibleSockets;

    @Transient
    @JsonProperty(RequestParams.SOCKET)
    public List<String> getCompatibleSocketsNames() {
        return compatibleSockets.stream()
                .map(CpuSocket::getSocketName)
                .collect(Collectors.toList());
    }

}