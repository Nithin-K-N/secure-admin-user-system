package com.nithin.secure_user_platform.auth.contoller;

import com.nithin.secure_user_platform.auth.records.LoginRequestBody;
import com.nithin.secure_user_platform.auth.records.RegisterRequestBody;
import com.nithin.secure_user_platform.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthContoller {

    AuthService authService;

    public AuthContoller(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("register")
    public ResponseEntity<?> login( @RequestBody RegisterRequestBody requestBody ){
        authService.register(requestBody);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("login")
    public ResponseEntity<?> login( @RequestBody LoginRequestBody requestBody ){
        String token = authService.login(requestBody);
        return ResponseEntity.ok(token);
    }
}
