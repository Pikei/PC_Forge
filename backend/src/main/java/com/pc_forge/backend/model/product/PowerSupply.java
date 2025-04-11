package com.pc_forge.backend.model.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public final class PowerSupply extends Product {
    private String standard;
    private Integer power;
    private String efficiencyCertificate;
    private Integer efficiency;
    private String coolingType;
    private Integer fanDiameter;
    private ArrayList<String> protections;
    private Boolean modularCabling;
    private Integer atx24;
    private Integer pcie16;
    private Integer pcie8;
    private Integer pcie6;
    private Integer cpu8;
    private Integer cpu4;
    private Integer sata;
    private Integer molex;
    private Integer height;
    private Integer width;
    private Integer depth;
    private Boolean lightning;
}
