package com.pc_forge.backend.model.product.compatibilities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cpu_socket", schema = "public")
public class CpuSocket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cpu_socket_id_gen")
    @SequenceGenerator(name = "cpu_socket_id_gen", sequenceName = "cpu_socket_socket_id_seq", allocationSize = 1)
    @Column(name = "socket_id", nullable = false)
    private Integer id;

    @Column(name = "socket", nullable = false, length = 100)
    private String socket;

}