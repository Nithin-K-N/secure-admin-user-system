package com.nithin.secure_user_platform.messages.repository;

import com.nithin.secure_user_platform.messages.domain.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findByReceiverIdOrderByCreatedAtDesc(
            Long receiverId,
            Pageable pageable
    );

    Page<Message> findBySenderIdOrderByCreatedAtDesc(
            Long senderId,
            Pageable pageable
    );
}
