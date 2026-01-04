package com.nithin.secure_user_platform.utility.sharedMethods;

import com.nithin.secure_user_platform.user.domain.entities.User;
import com.nithin.secure_user_platform.user.domain.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
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
