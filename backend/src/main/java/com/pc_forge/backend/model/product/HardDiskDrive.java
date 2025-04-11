package com.pc_forge.backend.model.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class HardDiskDrive extends Drive {
    private Integer rotationalSpeed;
}

