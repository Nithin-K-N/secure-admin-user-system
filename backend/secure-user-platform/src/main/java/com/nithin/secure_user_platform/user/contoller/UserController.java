package com.nithin.secure_user_platform.user.contoller;

import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import com.nithin.secure_user_platform.security.principal.UserPrincipal;
import com.nithin.secure_user_platform.user.domain.User;
import com.nithin.secure_user_platform.user.service.UserService;
import com.nithin.secure_user_platform.utility.records.ErrorResponseBody;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("myProfile")
    public ResponseEntity<?> getProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) throws UserNotFoundException {

        return ResponseEntity.ok(userService.getUserProfile(userPrincipal.getUserId()));
    }
}
