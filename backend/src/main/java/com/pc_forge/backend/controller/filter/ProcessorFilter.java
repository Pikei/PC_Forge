package com.pc_forge.backend.controller.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class ProcessorFilter extends ProductFilter {
    private List<String> selectedSockets;
    private List<String> selectedLines;
    private List<Integer> selectedCores;
    private List<Double> selectedFrequencies;
    private List<String> selectedGraphicsUnits;
    private List<String> selectedPackagingTypes;
    private Boolean unlocked;
    private Boolean coolerIncluded;

}
