package com.nithin.secure_user_platform.messages.domain.entity;

import com.nithin.secure_user_platform.user.domain.entities.User;
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
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sender_id", nullable = false, updatable = false)
//    private User sender;

    @Column(nullable = false, updatable = false)
    private Long receiverId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "receiver_id", nullable = false, updatable = false)
//    private User receiver;

    @Column(nullable = false, updatable = false)
    private String content;

//    private Boolean isRead;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate(){
        createdAt = LocalDateTime.now();
    }
}
