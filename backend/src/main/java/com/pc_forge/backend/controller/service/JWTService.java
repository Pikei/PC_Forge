package com.pc_forge.backend.controller.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pc_forge.backend.model.entity.user.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {
    @Value("${JWT_KEY}")
    private String key;

    @Value("${JWT_ISSUER}")
    private String issuer;

    @Value("${JWT_EXPIRATION_HOURS}")
    private Integer expiration;

    private Algorithm algorithm;
    private static final String USERNAME_KEY = "username";
    private static final String EMAIL = "email";

    @PostConstruct
    private void postConstruct() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(key);
    }

    public String createJWT(User user) {
        return JWT.create()
                .withClaim(USERNAME_KEY, user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * 3600 * expiration)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String generateVerificationJWT(User user) {
        return JWT.create()
                .withClaim(EMAIL, user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * 3600 * expiration)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String verifyJWT(String token) {
        return JWT.decode(token).getClaim(USERNAME_KEY).asString();
    }
}
