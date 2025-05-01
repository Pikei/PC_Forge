package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.exceptions.ProductDoesNotExistException;
import com.pc_forge.backend.controller.service.ShoppingCartService;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.view.body.order.ShoppingCartBody;
import com.pc_forge.backend.view.response.order.ProductOrderResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ResponseEntity<List<ProductOrderResponse>> getShoppingCart(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(shoppingCartService.getProductsInShoppingCart(user));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addItem(@Valid @AuthenticationPrincipal User user, @Valid @RequestBody ShoppingCartBody product) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            shoppingCartService.addProductToCart(user, product.getProductId());
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> removeItem(@Valid @AuthenticationPrincipal User user, @RequestBody ShoppingCartBody product) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            shoppingCartService.removeProductFromCart(user, product.getProductId());
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }

}
