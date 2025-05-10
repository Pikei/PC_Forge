package com.pc_forge.backend.view.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Walidator sprawdzający, czy wartość tekstowa jest null lub niepusta (nie składa się tylko z białych znaków).
 * Implementuje interfejs {@link ConstraintValidator} dla adnotacji {@link NullOrNotBlank}.
 */
public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {
    /**
     * Sprawdza, czy podana wartość spełnia warunki walidacji.
     *
     * @param value                      wartość do sprawdzenia poprawności
     * @param constraintValidatorContext kontekst walidacji
     * @return {@code true} jeśli wartość spełnia warunki walidacji, {@code false} w przeciwnym wypadku
     */
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || !value.trim().isEmpty();
    }
}