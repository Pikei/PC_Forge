package com.pc_forge.backend.view.api.request.response.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemoryFilterResponse extends ProductFilterResponse {
    private List<Object[]> memoryType;
    private List<Object[]> totalCapacity;
    private List<Object[]> frequency;
    private List<Object[]> numberOfModules;
    private List<Object[]> latency;
    private List<Object[]> lightning;
}
