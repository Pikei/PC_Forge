package com.pc_forge.backend.model.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class GraphicsCard extends Product {
    private String chipsetProducer;
    private String chipset;
    private Integer coreFrequency;
    private Integer maxCoreFrequency;
    private Integer streamProcessors;
    private Integer ropUnits;
    private Integer texturingUnits;
    private Integer rtUnits;
    private Integer tensorCores;
    private String dlss;
    private String connector;
    private Integer cardLength;
    private String resolution;
    private Integer recommendedPS;
    private Boolean lightning;
    private Integer ram;
    private String ramType;
    private Integer dataBus;
    private Integer memoryFrequency;
    private String coolingType;
    private Integer numberOfFans;
}
