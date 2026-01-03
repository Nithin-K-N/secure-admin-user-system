package com.nithin.secure_user_platform.admin.controller;

import com.nithin.secure_user_platform.admin.service.AdminService;
import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import com.nithin.secure_user_platform.user.domain.User;
import com.nithin.secure_user_platform.utility.records.ErrorResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("allUsers")
    public ResponseEntity<?> getAllUsers() throws UserNotFoundException {

            List<User> userList = adminService.getAllUser();
            return ResponseEntity.ok(userList);
    }
}
