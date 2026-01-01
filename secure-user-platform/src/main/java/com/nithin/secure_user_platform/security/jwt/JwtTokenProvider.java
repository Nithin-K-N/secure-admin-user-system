package com.nithin.secure_user_platform.security.jwt;

import com.nithin.secure_user_platform.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiry}")
    private long expirySeconds;

    private Key key(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getId().toString()) // renamed from setSubject
                .claim("username", user.getUsername())
                .claim("role", user.getRole().name()) // Ensure this is a String
                .claim("state", user.getState().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirySeconds * 1000))
                .signWith(key()) // Algorithm is now inferred from the key type
                .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) key()) // verifyWith replaces setSigningKey
                .build()
                .parseSignedClaims(token) // parseSignedClaims replaces parseClaimsJws
                .getPayload(); // getPayload replaces getBody
    }
}
