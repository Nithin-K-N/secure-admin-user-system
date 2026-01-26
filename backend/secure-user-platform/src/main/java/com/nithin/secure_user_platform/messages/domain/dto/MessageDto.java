package com.nithin.secure_user_platform.messages.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MessageDto{
    Long id;
    Long senderId;
    String senderUsername;
    Long receiverId;
    String receiverUsername;
    String content;
    LocalDateTime createdAt;
}
