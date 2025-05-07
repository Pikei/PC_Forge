package com.pc_forge.backend.controller.security;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Klasa konfigurująca dostęp do API Stripe dodając klucz API
 */
@Component
public class PaymentConfig {
    /**
     * Klucz API Stripe
     */
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    /**
     * Metoda wstrzykująca klucz API do biblioteki Stripe
     */
    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }
}
