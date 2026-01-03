package com.nithin.secure_user_platform.user.service;

import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import com.nithin.secure_user_platform.user.domain.User;
import com.nithin.secure_user_platform.user.dto.UserDto;
import com.nithin.secure_user_platform.user.repository.UserRepository;
import com.nithin.secure_user_platform.utility.sharedMethods.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;

    public UserDto getUserProfile(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) return entityMapper.userToDto(user.get());
        else throw new UserNotFoundException("User not found!");
    }
}
