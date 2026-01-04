package com.nithin.secure_user_platform.admin.controller;

import com.nithin.secure_user_platform.admin.domain.records.UserIdentificationRequestBody;
import com.nithin.secure_user_platform.admin.service.AdminService;
import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController @RequestMapping("admin")
@SecurityRequirement(name = "bearerAuth") // for swagger
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("allUsers")
    public ResponseEntity<?> getAllUsers() throws UserNotFoundException {

            return ResponseEntity.ok(adminService.getAllUser());
    }

    @PostMapping("banUser")
    public ResponseEntity<?> banUser(
            @RequestBody UserIdentificationRequestBody requestBody
    ) throws UserNotFoundException {

        return ResponseEntity.ok(adminService.banUser(requestBody));
    }

    @PostMapping("unbanUser")
    public ResponseEntity<?> unbanUser(
            @RequestBody UserIdentificationRequestBody requestBody
    ) throws UserNotFoundException {

        return ResponseEntity.ok(adminService.unbanUser(requestBody));
    }

    @PostMapping("promoteUser")
    public ResponseEntity<?> promoteUser(
            @RequestBody UserIdentificationRequestBody requestBody
    ) throws UserNotFoundException {

        return ResponseEntity.ok(adminService.promoteUser(requestBody));
    }

    @PostMapping("demoteUser")
    public ResponseEntity<?> demoteUser(
            @RequestBody UserIdentificationRequestBody requestBody
    ) throws UserNotFoundException {

        return ResponseEntity.ok(adminService.demoteUser(requestBody));
    }
}
