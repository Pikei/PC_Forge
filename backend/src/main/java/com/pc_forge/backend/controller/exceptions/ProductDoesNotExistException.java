package com.pc_forge.backend.controller.exceptions;

public class ProductDoesNotExistException extends Exception {
    public ProductDoesNotExistException(String message) {
        super(message);
    }
}
