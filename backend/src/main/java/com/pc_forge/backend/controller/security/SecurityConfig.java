package com.pc_forge.backend.controller.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.servlet.HandlerMapping;

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
                                .requestMatchers("/profile").authenticated()
                                .requestMatchers("/cart/**").authenticated()
                                .requestMatchers("/order/**").authenticated()
                                .requestMatchers("/configurations/**").authenticated()
                                .requestMatchers("/profile").authenticated()
                                .requestMatchers("/delete-account").authenticated()
//                        .requestMatchers("/password/**").authenticated()
                                .anyRequest().permitAll()
                )
                .build();
    }
}
