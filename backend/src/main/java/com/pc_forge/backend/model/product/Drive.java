package com.pc_forge.backend.model.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Drive extends Product {
    private String format;
    private Integer storage;
    private String driveInterface;
}

