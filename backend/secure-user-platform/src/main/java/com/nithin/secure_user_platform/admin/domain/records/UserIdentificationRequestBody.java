package com.nithin.secure_user_platform.admin.domain.records;

public record UserIdentificationRequestBody(
        Long userId,
        String username
) {}
