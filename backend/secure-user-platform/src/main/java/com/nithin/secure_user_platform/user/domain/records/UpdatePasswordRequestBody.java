package com.nithin.secure_user_platform.user.domain.records;

public record UpdatePasswordRequestBody(
        String oldPassword,
        String newPassword
) {}
