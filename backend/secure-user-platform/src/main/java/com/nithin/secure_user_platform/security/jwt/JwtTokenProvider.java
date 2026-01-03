package com.nithin.secure_user_platform.security.jwt;

import com.nithin.secure_user_platform.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final JwtKeyProvider jwtKeyProvider;
    public JwtTokenProvider(JwtKeyProvider jwtKeyProvider){
        this.jwtKeyProvider = jwtKeyProvider;
    }

    public String generateJwtToken(User user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("username", user.getUsername())
                .claim("role", user.getRole().name())
                .claim("state", user.getState().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+36000))
                .signWith(jwtKeyProvider.getKey())
                .compact();
    }

    public Claims parseJwtToken(String token){
        return Jwts.parser()
                .verifyWith( (javax.crypto.SecretKey)jwtKeyProvider.getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
