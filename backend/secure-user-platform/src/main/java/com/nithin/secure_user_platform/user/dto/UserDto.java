package com.nithin.secure_user_platform.user.dto;

import com.nithin.secure_user_platform.utility.enums.Roles;
import com.nithin.secure_user_platform.utility.enums.UserStates;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record UserDto (
        String username,
        String firstName,
        String lastName,
        String email,
        String role,
        String state
){}
