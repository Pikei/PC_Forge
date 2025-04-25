package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class ProcessorFilterResponse extends ProductFilterResponse {

    @JsonProperty(RequestParams.SOCKET)
    private List<Object[]> socket;

    @JsonProperty(RequestParams.MODEL)
    private List<Object[]> model;

    @JsonProperty(RequestParams.NUMBER_OF_CORES)
    private List<Object[]> numberOfCores;

    @JsonProperty(RequestParams.FREQUENCY)
    private List<Object[]> frequency;

    @JsonProperty(RequestParams.INTEGRATED_GRAPHICS_UNIT)
    private List<Object[]> integratedGraphicsUnit;

    @JsonProperty(RequestParams.COOLER_INCLUDED)
    private List<Object[]> coolerIncluded;

    @JsonProperty(RequestParams.PACKAGING_TYPE)
    private List<Object[]> packaging;

    @JsonProperty(RequestParams.CORE_UNLOCKED)
    private List<Object[]> coreUnlocked;
}
