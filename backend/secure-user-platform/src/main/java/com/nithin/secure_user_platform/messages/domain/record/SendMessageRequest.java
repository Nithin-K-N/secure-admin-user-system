package com.nithin.secure_user_platform.messages.domain.record;

public record SendMessageRequest(
        Long receiverId,
        String message
) {
}
