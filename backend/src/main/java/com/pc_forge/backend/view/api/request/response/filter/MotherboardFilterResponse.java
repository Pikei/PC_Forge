package com.pc_forge.backend.view.api.request.response.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MotherboardFilterResponse extends ProductFilterResponse {
    private List<Object[]> socket;
    private List<Object[]> standard;
    private List<Object[]> chipset;
    private List<Object[]> memoryStandard;
    private List<Object[]> memorySlot;
    private List<Object[]> maxMemory;
    private List<Object[]> frequency;
    private List<Object[]> bluetooth;
    private List<Object[]> wifi;
    private Double minWidth;
    private Double maxWidth;
    private Double minDepth;
    private Double maxDepth;
}
