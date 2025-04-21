package com.pc_forge.backend.view.api.request.response.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProcessorFilterResponse extends ProductFilterResponse {
    private List<Object[]> socket;
    private List<Object[]> model;
    private List<Object[]> numberOfCores;
    private List<Object[]> frequency;
    private List<Object[]> integratedGraphicsUnit;
    private List<Object[]> coolerIncluded;
    private List<Object[]> packaging;
    private List<Object[]> coreUnlocked;
}
