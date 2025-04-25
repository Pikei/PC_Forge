package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemoryFilterResponse extends ProductFilterResponse {

    @JsonProperty(RequestParams.RAM_TYPE)
    private List<Object[]> memoryType;

    @JsonProperty(RequestParams.RAM_CAPACITY)
    private List<Object[]> totalCapacity;

    @JsonProperty(RequestParams.FREQUENCY)
    private List<Object[]> frequency;

    @JsonProperty(RequestParams.NUMBER_OF_MODULES)
    private List<Object[]> numberOfModules;

    @JsonProperty(RequestParams.LATENCY)
    private List<Object[]> latency;

    @JsonProperty(RequestParams.LIGHTNING)
    private List<Object[]> lightning;
}
