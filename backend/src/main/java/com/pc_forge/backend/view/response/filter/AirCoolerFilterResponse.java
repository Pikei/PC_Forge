package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class AirCoolerFilterResponse extends CoolerFilterResponse {
    @JsonProperty(RequestParams.BASE_MATERIAL)
    private List<Object[]> baseMaterial;

    @JsonProperty(RequestParams.HEIGHT_MINIMUM)
    private Integer minHeight;

    @JsonProperty(RequestParams.HEIGHT_MAXIMUM)
    private Integer maxHeight;

    @JsonProperty(RequestParams.WIDTH_MINIMUM)
    private Integer minWidth;

    @JsonProperty(RequestParams.WIDTH_MAXIMUM)
    private Integer maxWidth;

    @JsonProperty(RequestParams.DEPTH_MINIMUM)
    private Integer minDepth;

    @JsonProperty(RequestParams.DEPTH_MAXIMUM)
    private Integer maxDepth;

    @JsonProperty(RequestParams.VERTICAL_INSTALLATION)
    private Boolean verticalInstallation;
}
