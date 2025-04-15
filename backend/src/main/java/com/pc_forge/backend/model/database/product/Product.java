package com.pc_forge.backend.model.database.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "product_producer_code_key", columnNames = {"producer_code"})
})
public class Product {
    @Id
    @Column(name = "ean", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 1000)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "producer_code", nullable = false, length = 200)
    private String producerCode;

    @Column(name = "producer", nullable = false)
    private String producer;

    @Column(name = "category", nullable = false)
    private String category;

}
