package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class CaseResponse extends ProductResponse {
    private String caseType;
    private String color;
    private Boolean hasWindow;
    private Boolean lightning;
}
