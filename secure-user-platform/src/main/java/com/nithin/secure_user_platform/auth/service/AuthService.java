package com.nithin.secure_user_platform.auth.service;

import com.nithin.secure_user_platform.auth.records.LoginRequestBody;
import com.nithin.secure_user_platform.auth.records.RegisterRequestBody;
import com.nithin.secure_user_platform.security.jwt.JwtTokenProvider;
import com.nithin.secure_user_platform.user.domain.User;
import com.nithin.secure_user_platform.user.repo.UserRepository;

import com.nithin.secure_user_platform.utility.enums.Roles;
import com.nithin.secure_user_platform.utility.enums.UserStates;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    UserRepository repository;
    PasswordEncoder passwordEncoder;
    JwtTokenProvider tokenProvider;

    public AuthService(
            UserRepository repository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider tokenProvider) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public void register(RegisterRequestBody requestBody) {
        repository.save(new User(
                null,
                requestBody.username(),
                requestBody.firstName(),
                requestBody.lastName(),
                requestBody.email(),
                passwordEncoder.encode(requestBody.password()),
                Roles.USER,
                UserStates.ACTIVE,
                null,
                null,
                null));
    }

    public String login(LoginRequestBody requestBody) {
        User user = repository.findByUsernameOrEmail(
                requestBody.identifier(),
                requestBody.identifier()).orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(requestBody.password(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }
        ;

        return tokenProvider.generateToken(user);
    }
}
