package com.nithin.secure_user_platform.security.jwt;

import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.Key;

@Getter
@Component
public class JwtKeyProvider {

    private final Key key;
    public JwtKeyProvider(){
        this.key = Jwts.SIG.HS256.key().build();
    }
}
