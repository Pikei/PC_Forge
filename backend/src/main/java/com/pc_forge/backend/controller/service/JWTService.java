package com.pc_forge.backend.controller.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pc_forge.backend.model.entity.user.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {
    @Value("${security.jwt.key}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.expiration}")
    private Integer expiration;

    private Algorithm algorithm;
    private static final String USERNAME_KEY = "username";
    private static final String VERIFICATION_EMAIL_KEY = "verification_email";
    private static final String RESET_PASSWORD_KEY = "reset_password";

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
                .withClaim(VERIFICATION_EMAIL_KEY, user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * 3600 * expiration)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String generatePasswordResetJWT(User user) {
        return JWT.create()
                .withClaim(RESET_PASSWORD_KEY, user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * 60 * 30)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getResetPasswordEmail(String token) {
        DecodedJWT jwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return jwt.getClaim(RESET_PASSWORD_KEY).asString();
    }

    public String getUsernameClaim(String token) {
        DecodedJWT jwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return jwt.getClaim(USERNAME_KEY).asString();
    }
}
