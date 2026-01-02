package com.nithin.secure_user_platform.security.jwt;

import com.nithin.secure_user_platform.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final JwtKeyProvider keyProvider;

    @Value("${jwt.expiry}")
    private long expirySeconds;

    /*
     * Secret key manual define logic
     *
     */
    // @Value("${jwt.secret}")
    // private String secret;
    // private Key key(){
    // return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    // }

    public JwtTokenProvider(JwtKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getId().toString()) // renamed from setSubject
                .claim("username", user.getUsername())
                .claim("role", user.getRole().name()) // Ensure this is a String
                .claim("state", user.getState().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirySeconds * 1000))
                .signWith(keyProvider.getKey()) // Algorithm is now inferred from the key type
                .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) keyProvider.getKey()) // verifyWith replaces setSigningKey
                .build()
                .parseSignedClaims(token) // parseSignedClaims replaces parseClaimsJws
                .getPayload(); // getPayload replaces getBody
    }
}
