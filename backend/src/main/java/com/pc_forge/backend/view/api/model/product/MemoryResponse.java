package com.pc_forge.backend.view.api.model.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class MemoryResponse extends ProductResponse {
    private String memoryType;
    private Integer totalCapacity;
    private String latency;
    private Integer frequency;
}
