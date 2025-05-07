package com.pc_forge.backend.view.body.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartBody {
    @NotNull
    private Long productId;
}
