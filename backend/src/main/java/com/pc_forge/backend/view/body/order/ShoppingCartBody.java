package com.pc_forge.backend.view.body.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) wykorzystywanego
 * podczas dodawania przez użytkownika produktu do koszyka zakupowego.
 * Odbierane jest przez kontroler jako Request Body.
 */
@Getter
@Setter
public class ShoppingCartBody {
    /**
     * Identyfikator produktu.
     */
    @NotNull
    private Long productId;
}
