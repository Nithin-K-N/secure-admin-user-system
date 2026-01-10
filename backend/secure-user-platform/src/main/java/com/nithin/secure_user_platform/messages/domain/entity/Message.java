package com.nithin.secure_user_platform.messages.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "messages")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private Long senderId;

    @Column(nullable = false, updatable = false)
    private Long receiverId;

    @Column(nullable = false, updatable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate(){
        createdAt = LocalDateTime.now();
    }
}
