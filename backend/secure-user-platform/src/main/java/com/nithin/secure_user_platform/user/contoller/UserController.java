package com.nithin.secure_user_platform.user.contoller;

import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import com.nithin.secure_user_platform.security.principal.UserPrincipal;
import com.nithin.secure_user_platform.user.domain.records.UpdatePasswordRequestBody;
import com.nithin.secure_user_platform.user.domain.records.UpdateProfileRequestBody;
import com.nithin.secure_user_platform.user.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("updateProfile")
    public ResponseEntity<?> updateProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody UpdateProfileRequestBody requestBody
    ) throws UserNotFoundException {

        return ResponseEntity.ok(userService.updateProfile(requestBody, userPrincipal));
    }

    @PostMapping("updatePassword")
    public ResponseEntity<?> updatePassword(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody UpdatePasswordRequestBody requestBody
    ) throws UserNotFoundException {

        return ResponseEntity.ok(userService.updatePassword(requestBody, userPrincipal));
    }
}
