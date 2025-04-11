package com.pc_forge.backend.model.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class Processor extends Product {
    private String line;
    private String model;
    private Integer cores;
    private Integer threads;
    private String socket;
    private Boolean unlocked;
    private Double frequency;
    private Double maxFreq;
    private String integratedGraphicsUnit;
    private Integer tdp;
    private Boolean coolerIncluded;
    private String packaging;

}
