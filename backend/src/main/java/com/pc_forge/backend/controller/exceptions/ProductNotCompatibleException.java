package com.pc_forge.backend.controller.exceptions;

public class ProductNotCompatibleException extends RuntimeException {
    public ProductNotCompatibleException(String message) {
        super(message);
    }
}
