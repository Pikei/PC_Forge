package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.exceptions.OrderDoesNotExistException;
import com.pc_forge.backend.controller.service.OrderService;
import com.pc_forge.backend.view.response.payment.PaymentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final OrderService orderService;

    public PaymentController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/success")
    public ResponseEntity<PaymentResponse> paymentSuccess(@RequestParam("session_id") String sessionId) {
        PaymentResponse response = new PaymentResponse();
        try {
            orderService.paymentSucceeded(sessionId);
            response.setSuccess(true);
            response.setStatus("PAYMENT_SUCCESSFUL");
            return ResponseEntity.ok(response);
        } catch (OrderDoesNotExistException e) {
            response.setSuccess(false);
            response.setStatus("ORDER_DOES_NOT_EXIST");
            response.setMessage(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cancel")
    public ResponseEntity<PaymentResponse> paymentCancel() {
        PaymentResponse response = new PaymentResponse();
        response.setSuccess(false);
        response.setStatus("PAYMENT_CANCELED");
        response.setMessage("Payment canceled");
        return ResponseEntity.ok(response);
    }
}
