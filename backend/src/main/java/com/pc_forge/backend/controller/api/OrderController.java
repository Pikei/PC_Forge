package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.service.OrderService;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.view.body.order.AddressBody;
import com.pc_forge.backend.view.response.order.OrderResponse;
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

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrder(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(orderService.getOrders(user));
    }

    @PostMapping("/new")
    public ResponseEntity<String> createOrder(@AuthenticationPrincipal User user, @Valid @RequestBody AddressBody addressBody) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String message = "Deleted " + orderService.newOrder(user, addressBody) + " items from " + user.getUsername() + "'s shopping cart.";
        return ResponseEntity.ok(message);
    }
}
