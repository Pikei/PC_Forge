package com.pc_forge.backend.model.database.product.compatibility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "motherboard_standard", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "motherboard_standard_standard_id_key", columnNames = {"standard_id"})
})
public class MotherboardStandard {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "standard_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "standard_name", nullable = false, length = 100)
    private String standardName;

}