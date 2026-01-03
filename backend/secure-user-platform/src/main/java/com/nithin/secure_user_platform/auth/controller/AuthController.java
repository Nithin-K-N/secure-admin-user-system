package com.nithin.secure_user_platform.auth.controller;

import com.nithin.secure_user_platform.auth.record.LoginRequestBody;
import com.nithin.secure_user_platform.auth.record.RegisterRequestBody;
import com.nithin.secure_user_platform.auth.service.AuthService;
import com.nithin.secure_user_platform.exception.customExceptions.UserAlreadyExistsException;
import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> registerUser(
            @RequestBody RegisterRequestBody requestBody
    ) throws UserAlreadyExistsException {

            return ResponseEntity.ok(authService.registerUser(requestBody));
    }

    @PostMapping("login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequestBody requestBody
    ) throws UserNotFoundException {

            return ResponseEntity.ok(authService.login(requestBody));
    }
}
