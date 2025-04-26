package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class CoolerFilterResponse extends ProductFilterResponse {
    @JsonProperty(RequestParams.SOCKET)
    private List<Object[]> socket;

    @JsonProperty(RequestParams.NUMBER_OF_FANS)
    private List<Object[]> fans;

    @JsonProperty(RequestParams.FAN_DIAMETER)
    private List<Object[]> fanDiameters;

    @JsonProperty(RequestParams.NOISE_LEVEL)
    private Double maxNoiseLevel;

    @JsonProperty(RequestParams.LIGHTNING)
    private Boolean lightning;
}
