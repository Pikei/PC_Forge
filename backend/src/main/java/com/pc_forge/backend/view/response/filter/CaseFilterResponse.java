package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class CaseFilterResponse extends ProductFilterResponse {
    @JsonProperty(RequestParams.COLOR)
    private List<Object[]> color;

    @JsonProperty(RequestParams.CASE_TYPE)
    private List<Object[]> caseType;

    @JsonProperty(RequestParams.MOTHERBOARD_STANDARD)
    private List<Object[]> mbStandards;

    @JsonProperty(RequestParams.WIDTH_MINIMUM)
    private Double widthMinimum;

    @JsonProperty(RequestParams.WIDTH_MAXIMUM)
    private Double widthMaximum;

    @JsonProperty(RequestParams.HEIGHT_MINIMUM)
    private Double heightMinimum;

    @JsonProperty(RequestParams.HEIGHT_MAXIMUM)
    private Double heightMaximum;

    @JsonProperty(RequestParams.DEPTH_MINIMUM)
    private Double depthMinimum;

    @JsonProperty(RequestParams.DEPTH_MAXIMUM)
    private Double depthMaximum;

    @JsonProperty(RequestParams.HAS_WINDOW)
    private List<Object[]> hasWindow;

    @JsonProperty(RequestParams.HAS_POWER_SUPPLY)
    private List<Object[]> hasPowerSupply;

    @JsonProperty(RequestParams.PS_POWER)
    private List<Object[]> psPower;

    @JsonProperty(RequestParams.MAX_GPU_LENGTH)
    private List<Object[]> maxGpuLength;

    @JsonProperty(RequestParams.MAX_CPU_HEIGHT)
    private List<Object[]> maxCpuCoolerHeight;

    @JsonProperty(RequestParams.FRONT_FANS)
    private List<Object[]> frontFans;

    @JsonProperty(RequestParams.BACK_FANS)
    private List<Object[]> backFans;

    @JsonProperty(RequestParams.SIDE_FANS)
    private List<Object[]> sideFans;

    @JsonProperty(RequestParams.BOTTOM_FANS)
    private List<Object[]> bottomFans;

    @JsonProperty(RequestParams.TOP_FANS)
    private List<Object[]> topFans;

    @JsonProperty(RequestParams.USB_20)
    private List<Object[]> usb20;

    @JsonProperty(RequestParams.USB_30)
    private List<Object[]> usb30;

    @JsonProperty(RequestParams.USB_31)
    private List<Object[]> usb31;

    @JsonProperty(RequestParams.USB_32)
    private List<Object[]> usb32;

    @JsonProperty(RequestParams.USB_C)
    private List<Object[]> usbC;

}
