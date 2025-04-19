package com.pc_forge.backend.controller.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Value("${encryption.salt.rounds}")
    private int saltRounds;

    private String salt;

    @PostConstruct
    public void postConstruct() {
        salt = BCrypt.gensalt(saltRounds);
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, salt);
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
