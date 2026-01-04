package com.nithin.secure_user_platform.auth.domain.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestBody(
        @NotBlank(message = "Username is required")
        String identifier,

        @Size(min=8, message = "Password must be atleast 8 character")
        String password
) {
}
