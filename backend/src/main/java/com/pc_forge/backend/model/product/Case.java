package com.pc_forge.backend.model.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public final class Case extends Product {
    private String color;
    private Boolean lightning;
    private Double height;
    private Double width;
    private Double depth;
    private Double weight;
    private String caseType;
    private ArrayList<String> mbCompatibility;
    private Boolean window;
    private Double maxGpuLength;
    private Double maxCpuCoolerHeight;
    private Integer maxCpuCoreCount;
    private Integer usb20;
    private Integer usb30;
    private Integer usb31;
    private Integer usb32;
    private Integer usbC;
    private Boolean cardReader;
    private Boolean headphonesConnector;
    private Boolean microphoneConnector;
    private Integer internalBays25;
    private Integer internalBays35;
    private Integer externalBays35;
    private Integer externalBays525;
    private Integer numberOfExpansionSlots;
    private String frontFans;
    private String backFans;
    private String sideFans;
    private String bottomFans;
    private String topFans;
    private Boolean powerSupply;
    private Integer psPower;
}
