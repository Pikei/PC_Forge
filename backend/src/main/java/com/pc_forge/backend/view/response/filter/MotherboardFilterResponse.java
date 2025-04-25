package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class MotherboardFilterResponse extends ProductFilterResponse {
    @JsonProperty(RequestParams.SOCKET)
    private List<Object[]> socket;

    @JsonProperty(RequestParams.MOTHERBOARD_STANDARD)
    private List<Object[]> standard;

    @JsonProperty(RequestParams.CHIPSET)
    private List<Object[]> chipset;

    @JsonProperty(RequestParams.RAM_TYPE)
    private List<Object[]> memoryStandard;

    @JsonProperty(RequestParams.RAM_SLOTS)
    private List<Object[]> memorySlot;

    @JsonProperty(RequestParams.RAM_CAPACITY)
    private List<Object[]> maxMemory;

    @JsonProperty(RequestParams.FREQUENCY)
    private List<Object[]> frequency;

    @JsonProperty(RequestParams.BLUETOOTH)
    private List<Object[]> bluetooth;

    @JsonProperty(RequestParams.WIFI)
    private List<Object[]> wifi;

    @JsonProperty(RequestParams.WIDTH_MINIMUM)
    private Double minWidth;

    @JsonProperty(RequestParams.WIDTH_MAXIMUM)
    private Double maxWidth;

    @JsonProperty(RequestParams.DEPTH_MINIMUM)
    private Double minDepth;

    @JsonProperty(RequestParams.DEPTH_MAXIMUM)
    private Double maxDepth;
}
