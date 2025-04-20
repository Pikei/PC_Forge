package com.pc_forge.backend.view.api.request.response.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class CaseResponse extends ProductResponse {
    private String color;
    private Boolean lightning;
    private String caseType;
    private Boolean hasWindow;
}
