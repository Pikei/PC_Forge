package com.pc_forge.backend.view.response.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    private Boolean success;
    private String status;
    private String paymentUrl;
    private String message;
}
