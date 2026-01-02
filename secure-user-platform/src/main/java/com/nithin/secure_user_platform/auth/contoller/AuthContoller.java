package com.nithin.secure_user_platform.auth.contoller;

import com.nithin.secure_user_platform.auth.records.LoginRequestBody;
import com.nithin.secure_user_platform.auth.records.RegisterRequestBody;
import com.nithin.secure_user_platform.auth.service.AuthService;
import com.nithin.secure_user_platform.utility.records.ErrorResponse;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthContoller {

    AuthService authService;

    public AuthContoller(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public ResponseEntity<?> login(@RequestBody RegisterRequestBody requestBody) {
        try {
            authService.register(requestBody);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    e.getMessage(),
                    "400",
                    "auth/register",
                    LocalDateTime.now().toString());
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestBody requestBody) {
        try {
            String token = authService.login(requestBody);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    e.getMessage(),
                    "400",
                    "auth/login",
                    LocalDateTime.now().toString());
            return ResponseEntity.status(400).body(errorResponse);
        }
    }
}
