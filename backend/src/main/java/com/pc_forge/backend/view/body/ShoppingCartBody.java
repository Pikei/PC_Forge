package com.pc_forge.backend.view.body;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartBody {
    @NotNull
    @NotBlank
    private Long productId;
}
