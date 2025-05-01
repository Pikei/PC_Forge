package com.pc_forge.backend.view.body.configuration;

import com.pc_forge.backend.view.util.NullOrNotBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigurationBody {
    @NullOrNotBlank
    private String configName;

    @NullOrNotBlank
    private Long graphicsCardId;

    @NotNull
    @NotBlank
    private Long caseId;

    @NotNull
    @NotBlank
    private Long motherboardId;

    @NullOrNotBlank
    private Long coolerId;

    @NullOrNotBlank
    private Long powerSupplyId;

    @NotNull
    @NotBlank
    private Long memoryId;

    @NotNull
    @NotBlank
    private Long processorId;

    @NullOrNotBlank
    private Long solidStateDriveId;

    @NullOrNotBlank
    private Long hardDiskDriveId;
}
