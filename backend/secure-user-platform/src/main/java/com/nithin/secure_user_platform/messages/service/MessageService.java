package com.nithin.secure_user_platform.messages.service;

import com.nithin.secure_user_platform.exception.customExceptions.UserInActiveException;
import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import com.nithin.secure_user_platform.messages.domain.dto.MessageDto;
import com.nithin.secure_user_platform.messages.domain.entity.Message;
import com.nithin.secure_user_platform.messages.repository.MessageRepository;
import com.nithin.secure_user_platform.messages.domain.record.SendMessageRequest;
import com.nithin.secure_user_platform.security.principal.UserPrincipal;
import com.nithin.secure_user_platform.user.repository.UserRepository;
import com.nithin.secure_user_platform.utility.enums.UserStates;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service @RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public void sendMessage(UserPrincipal user, SendMessageRequest responseBody) throws UserInActiveException, UserNotFoundException {

        if(!Objects.equals(user.getState(), UserStates.ACTIVE.name())) throw new UserInActiveException("Banned users cannot send messages");

        userRepository
                .findById(responseBody.receiverId())
                .orElseThrow(() -> new UserNotFoundException("Receiver not found"));

        messageRepository.save(
                new Message(
                        null,
                        user.getUserId(),
                        responseBody.receiverId(),
                        responseBody.message(),
                        null
                )
        );
    }

    public Page<MessageDto> getInbox(UserPrincipal user, int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        return messageRepository
                .findByReceiverIdOrderByCreatedAtDesc(user.getUserId(), pageable)
                .map(this::messageToDto);
    }

    public Page<MessageDto > getSent(UserPrincipal user, int page, int size){
        return  messageRepository
                .findBySenderIdOrderByCreatedAtDesc(user.getUserId(), PageRequest.of(page, size))
                .map(this::messageToDto);
    }

    private MessageDto messageToDto(Message message){
        return new MessageDto(
                message.getId(),
                message.getSenderId(),
                message.getReceiverId(),
                message.getContent(),
                message.getCreatedAt()
        );
    }
}
