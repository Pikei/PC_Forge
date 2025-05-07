package com.pc_forge.backend.controller.security;

import com.pc_forge.backend.controller.api.constants.UrlPath;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

/**
 * Klasa konfiguracyjna Spring Security. Dodaje reguły filtrowania żądań HTTP
 * i określa reguły dostępu do punktów końcowych (endpoints) API w aplikacji.
 */
@Configuration
public class SecurityConfig {
    /**
     * Filtr służący do filtrowania żądań HTTP, pod kątem obecności tokenów JWT
     */
    private final JWTRequestFilter jwtRequestFilter;

    /**
     * Konstruktor wstrzykujący filtr JWT dla żądań HTTP
     *
     * @param jwtRequestFilter filtr JWT dla żądań HTTP
     */
    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * Metoda dodająca filtr JWT dla żądań HTTP do łańcucha filtrów Spring Security.
     * Definiuje również punkty końcowe, do których wymagana jest autoryzacja
     * i wyłącza domyślne zabezpieczenia CSRF (Cross-Site Request Forgery) oraz CORS (Cross-Origin Resource Sharing).
     *
     * @param http obiekt klasy {@link HttpSecurity} umożliwiający konfigurację zabezpieczeń webowych
     * @return otrzymany obiekt {@code http} z dodanym filtrem JWT
     * @throws Exception w przypadku gdy wystąpi błąd podczas konfiguracji Spring Security
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtRequestFilter, AuthorizationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(UrlPath.PROFILE).authenticated()
                        .requestMatchers(UrlPath.SHOPPING_CART + "/**").authenticated()
                        .requestMatchers(UrlPath.ORDER + "/**").authenticated()
                        .requestMatchers(UrlPath.DELETE_ACCOUNT).authenticated()
                        .requestMatchers(UrlPath.CONFIGURATIONS + "/**").authenticated()
                        .anyRequest().permitAll()
                )
                .build();
    }
}
