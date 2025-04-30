package com.pc_forge.backend.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_status", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "order_status_status_name_key", columnNames = {"status_name"}),
        @UniqueConstraint(name = "order_status_status_description_key", columnNames = {"status_description"})
})
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "status_name", nullable = false, length = Integer.MAX_VALUE)
    private String statusName;

    @NotNull
    @Column(name = "status_description", nullable = false, length = Integer.MAX_VALUE)
    private String statusDescription;

}