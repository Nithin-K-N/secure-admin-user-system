package com.nithin.secure_user_platform.messages.domain.dto;

import java.time.LocalDateTime;

public record MessageDto(
        Long id,
        Long senderId,
        Long receiverId,
        String content,
        LocalDateTime createdAt
) {
}
