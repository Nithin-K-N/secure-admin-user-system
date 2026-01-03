package com.nithin.secure_user_platform.utility.sharedMethods;

import com.nithin.secure_user_platform.user.domain.User;
import com.nithin.secure_user_platform.user.dto.UserDto;

public class EntityMapper {

    public UserDto userToDto(User user){
        return new UserDto(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().name(),
                user.getState().name()
        );
    }
}
