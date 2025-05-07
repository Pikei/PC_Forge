package com.pc_forge.backend.view.response.configuration;

import com.pc_forge.backend.view.response.product.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigurationResponse {
    private String name;
    private Double price;
    private ProcessorResponse processor;
    private MotherboardResponse motherboard;
    private MemoryResponse memory;
    private GraphicsCardResponse graphicsCard;
    private PowerSupplyResponse powerSupply;
    private CaseResponse pcCase;
    private CoolerResponse cooler;
    private SsdResponse solidStateDrive;
    private HddResponse hardDiskDrive;
}
