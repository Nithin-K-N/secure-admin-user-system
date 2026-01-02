package com.nithin.secure_user_platform.security.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtKeyProvider {

    private final Key key;

    public JwtKeyProvider() {
        // Auto secure random key generator for HMAC-SHA256
        this.key = Jwts.SIG.HS256.key().build();
    }

    public Key getKey() {
        return this.key;
    }
}
