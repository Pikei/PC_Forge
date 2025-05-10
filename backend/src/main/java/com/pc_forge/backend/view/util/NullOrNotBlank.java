package com.pc_forge.backend.view.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Adnotacja służąca do walidacji wartości pola typu String określającej, że pole
 * może przyjmować wartość {@code null} lub niepustego ciągu znaków.
 * Używane do sprawdzania opcjonalnych wartości, takich jak numer mieszkania lub numer telefonu.
 */
@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = NullOrNotBlankValidator.class)
public @interface NullOrNotBlank {
    /**
     * Wiadomość zwracana w przypadku niepowodzenia walidacji.
     */
    String message() default "{javax.validation.constraints.NullOrNotBlank.message}";

    /**
     * Grupy walidacji, do których należy adnotacja.
     */
    Class<?>[] groups() default {};

    /**
     * Payload dla adnotacji.
     */
    Class<? extends Payload>[] payload() default {};
}
