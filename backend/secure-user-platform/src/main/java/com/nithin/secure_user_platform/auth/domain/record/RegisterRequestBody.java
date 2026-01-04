package com.nithin.secure_user_platform.auth.domain.record;

public record RegisterRequestBody(
        String username,
        String firstName,
        String lastName,
        String email,
        String password
) {}
