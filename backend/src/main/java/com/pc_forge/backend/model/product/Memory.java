package com.pc_forge.backend.model.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class Memory extends Product {
    private String line;
    private String memoryType;
    private Integer totalCapacity;
    private Integer numberOfModules;
    private Integer frequency;
    private String latency;
    private Boolean lightning;
}
