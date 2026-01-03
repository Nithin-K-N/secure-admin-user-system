package com.nithin.secure_user_platform.auth.record;

public record RegisterRequestBody(
        String username,
        String firstName,
        String lastName,
        String email,
        String password
) {}
