package com.pc_forge.backend.view.api.model.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DriveResponse extends ProductResponse {
    private String driveFormat;
    private Integer storage;
    private String driveInterface;
}
