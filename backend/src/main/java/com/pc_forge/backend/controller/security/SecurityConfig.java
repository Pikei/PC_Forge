package com.pc_forge.backend.controller.security;

import com.pc_forge.backend.controller.api.constants.UrlPath;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {
    private final JWTRequestFilter jwtRequestFilter;

    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

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
                        .requestMatchers(UrlPath.PAYMENT + "/**").authenticated()
                                .anyRequest().permitAll()
                )
                .build();
    }
}
