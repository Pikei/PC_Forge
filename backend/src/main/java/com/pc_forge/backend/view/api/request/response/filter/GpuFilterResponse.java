package com.pc_forge.backend.view.api.request.response.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GpuFilterResponse extends ProductFilterResponse {
    private List<Object[]> chipsetProducer;
    private List<Object[]> chipset;
    private List<Object[]> ram;
    private List<Object[]> ramType;
    private List<Object[]> dlss;
    private List<Object[]> connector;
    private List<Object[]> cardLength;
    private List<Object[]> resolution;
    private List<Object[]> recommendedPsPower;
    private List<Object[]> coolingType;
    private List<Object[]> numberOfFans;
    private List<Object[]> lightning;
}
