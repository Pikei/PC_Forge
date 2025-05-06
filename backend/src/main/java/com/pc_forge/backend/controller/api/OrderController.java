package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.exceptions.OrderDoesNotExistException;
import com.pc_forge.backend.controller.exceptions.PaymentException;
import com.pc_forge.backend.controller.service.OrderService;
import com.pc_forge.backend.controller.service.PaymentService;
import com.pc_forge.backend.model.entity.order.Order;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.view.body.order.AddressBody;
import com.pc_forge.backend.view.response.order.OrderResponse;
import com.pc_forge.backend.view.response.payment.PaymentResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final PaymentService paymentService;

    public OrderController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getOrder(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(orderService.getOrders(user));
    }

    @PostMapping("/new")
    public ResponseEntity<PaymentResponse> createOrder(@AuthenticationPrincipal User user, @Valid @RequestBody AddressBody addressBody) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        PaymentResponse response = new PaymentResponse();
        try {
            Order order = orderService.newOrder(user, addressBody);
            String paymentUrl = paymentService.createPaymentSession(order);
            response.setSuccess(true);
            response.setStatus("WAITING_FOR_PAYMENT");
            response.setMessage("Payment session created");
            response.setPaymentUrl(paymentUrl);
            return ResponseEntity.ok(response);
        } catch (PaymentException e) {
            response.setSuccess(false);
            response.setStatus("PAYMENT_SESSION_ERROR");
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/pay")
    public ResponseEntity<PaymentResponse> payOrder(@AuthenticationPrincipal User user, @RequestParam("order_ID") Long orderId) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Order order;
        PaymentResponse response = new PaymentResponse();
        try {
            order = orderService.getOrder(user, orderId);
            String paymentUrl = paymentService.createPaymentSession(order);
            response.setSuccess(true);
            response.setStatus("WAITING_FOR_PAYMENT");
            response.setMessage("Payment session created");
            response.setPaymentUrl(paymentUrl);
            return ResponseEntity.ok(response);
        } catch (OrderDoesNotExistException e) {
            response.setSuccess(false);
            response.setStatus("ORDER_DOES_NOT_EXIST");
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (PaymentException e) {
            response.setSuccess(false);
            response.setStatus("PAYMENT_SESSION_ERROR");
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/cancel")
    public ResponseEntity<PaymentResponse> cancelOrder(@AuthenticationPrincipal User user, @RequestParam("order_ID") Long orderId) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        PaymentResponse response = new PaymentResponse();
        try {
            var status = orderService.cancelOrder(user, orderId);
            String returned = paymentService.refundPayment(orderId);
            response.setSuccess(true);
            response.setStatus(status);
            response.setMessage("Payment refunded. Returned amount: " + returned);
        } catch (PaymentException e) {
            response.setSuccess(false);
            response.setStatus("PAYMENT_SESSION_ERROR");
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setStatus("ORDER_DOES_NOT_EXIST");
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

}
