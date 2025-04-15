package com.pc_forge.backend.model.product.compatibilities;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "socket_id", nullable = false)
    private Integer id;

    @Column(name = "socket", nullable = false, length = 100)
    private String socket;

}