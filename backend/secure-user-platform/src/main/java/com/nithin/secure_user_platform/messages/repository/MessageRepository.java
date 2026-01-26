package com.nithin.secure_user_platform.messages.repository;

import com.nithin.secure_user_platform.messages.domain.dto.MessageDto;
import com.nithin.secure_user_platform.messages.domain.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findByReceiverIdOrderByCreatedAtDesc(
            Long receiverId,
            Pageable pageable
    );

    @Query("""
        SELECT new com.nithin.secure_user_platform.messages.domain.dto.MessageDto(
            m.id,
            s.id,
            s.username,
            r.id,
            r.username,
            m.content,
            m.createdAt
        )
        FROM Message m
        JOIN User s ON m.senderId = s.id
        JOIN User r ON m.receiverId = r.id
        WHERE m.receiverId = :userId
        ORDER BY m.createdAt DESC
        """)
    Page<MessageDto> findInboxWithUsernames(
            @Param("userId") Long userId,
            Pageable pageable
    );


    Page<Message> findBySenderIdOrderByCreatedAtDesc(
            Long senderId,
            Pageable pageable
    );

    @Query("""
        SELECT new com.nithin.secure_user_platform.messages.domain.dto.MessageDto(
            m.id,
            s.id,
            s.username,
            r.id,
            r.username,
            m.content,
            m.createdAt
        )
        FROM Message m
        JOIN User s ON m.senderId = s.id
        JOIN User r ON m.receiverId = r.id
        WHERE m.senderId = :userId
        ORDER BY m.createdAt DESC
        """)
    Page<MessageDto> findSentWithUsernames(
            @Param("userId") Long userId,
            Pageable pageable
    );
}
