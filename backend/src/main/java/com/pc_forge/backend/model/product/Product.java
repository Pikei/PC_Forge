package com.pc_forge.backend.model.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "product", schema = "public")
@DiscriminatorColumn(name = "category", discriminatorType = DiscriminatorType.STRING)
public abstract class Product {
    @Id
    @Column(name = "ean", nullable = false, unique = true)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "category", nullable = false, insertable = false, updatable = false)
    private String category;

    @Size(max = 1000)
    @NotNull
    @Column(name = "name", nullable = false, length = 1000)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @Size(max = 200)
    @NotNull
    @Column(name = "producer_code", nullable = false, length = 200)
    private String producerCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "producer", nullable = false)
    private String producer;

}