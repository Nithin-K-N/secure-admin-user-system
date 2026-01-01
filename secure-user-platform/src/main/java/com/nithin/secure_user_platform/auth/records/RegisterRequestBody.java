package com.nithin.secure_user_platform.auth.records;

public record RegisterRequestBody(
        String username,
        String firstName,
        String lastName,
        String email,
        String password
) {}
