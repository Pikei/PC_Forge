package com.pc_forge.backend.model.product.compatibility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cpu_socket", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "cpu_socket_socket_key", columnNames = {"socket"})
})
public class CpuSocket {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "socket_id", nullable = false)
    private Integer socketId;

    @Size(max = 100)
    @NotNull
    @Column(name = "socket", nullable = false, length = 100)
    private String socketName;

}