package com.nithin.secure_user_platform.messages.controller;

import com.nithin.secure_user_platform.exception.customExceptions.UserInActiveException;
import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import com.nithin.secure_user_platform.messages.domain.dto.MessageDto;
import com.nithin.secure_user_platform.messages.domain.record.ListMessageRequest;
import com.nithin.secure_user_platform.messages.domain.record.SendMessageRequest;
import com.nithin.secure_user_platform.messages.service.MessageService;
import com.nithin.secure_user_platform.security.principal.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Void> sentMessage(
            @AuthenticationPrincipal UserPrincipal user,
            @RequestBody SendMessageRequest request
    ) throws UserNotFoundException, UserInActiveException {

        messageService.sendMessage(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("inbox")
    public Page<MessageDto> getInbox(
            @AuthenticationPrincipal UserPrincipal user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){

        return messageService.getInbox(user, page, size);
    }

    @GetMapping("sent")
    public Page<MessageDto> getSent(
            @AuthenticationPrincipal UserPrincipal user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){

        return messageService.getSent(user, page, size);
    }
}
