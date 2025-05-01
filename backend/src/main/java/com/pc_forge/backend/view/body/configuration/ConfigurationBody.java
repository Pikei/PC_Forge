package com.pc_forge.backend.view.body.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigurationBody {
    @NotNull
    @NotBlank
    private String configName;

    @NotNull
    private Long processorId;

    @NotNull
    private Long motherboardId;

    @NotNull
    private Long memoryId;

    @NotNull
    private Long caseId;

    private Long graphicsCardId;

    private Long coolerId;

    private Long powerSupplyId;

    private Long solidStateDriveId;

    private Long hardDiskDriveId;
}
