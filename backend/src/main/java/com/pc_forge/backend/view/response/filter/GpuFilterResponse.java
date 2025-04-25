package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GpuFilterResponse extends ProductFilterResponse {
    @JsonProperty(RequestParams.CHIPSET_PRODUCER)
    private List<Object[]> chipsetProducer;

    @JsonProperty(RequestParams.CHIPSET)
    private List<Object[]> chipset;

    @JsonProperty(RequestParams.RAM_CAPACITY)
    private List<Object[]> ram;

    @JsonProperty(RequestParams.RAM_TYPE)
    private List<Object[]> ramType;

    @JsonProperty(RequestParams.DLSS)
    private List<Object[]> dlss;

    @JsonProperty(RequestParams.CONNECTOR)
    private List<Object[]> connector;

    @JsonProperty(RequestParams.RESOLUTION)
    private List<Object[]> resolution;

    @JsonProperty(RequestParams.PS_POWER)
    private List<Object[]> recommendedPsPower;

    @JsonProperty(RequestParams.COOLING_TYPE)
    private List<Object[]> coolingType;

    @JsonProperty(RequestParams.NUMBER_OF_FANS)
    private List<Object[]> numberOfFans;

    @JsonProperty(RequestParams.LIGHTNING)
    private List<Object[]> lightning;

    @JsonProperty(RequestParams.LENGTH_MINIMUM)
    private Integer lengthMinimum;

    @JsonProperty(RequestParams.LENGTH_MAXIMUM)
    private Integer lengthMaximum;
}
