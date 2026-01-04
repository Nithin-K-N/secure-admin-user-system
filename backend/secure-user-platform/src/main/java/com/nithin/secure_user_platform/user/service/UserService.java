package com.nithin.secure_user_platform.user.service;

import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import com.nithin.secure_user_platform.security.principal.UserPrincipal;
import com.nithin.secure_user_platform.user.domain.entities.User;
import com.nithin.secure_user_platform.user.domain.dto.UserDto;
import com.nithin.secure_user_platform.user.domain.records.UpdatePasswordRequestBody;
import com.nithin.secure_user_platform.user.domain.records.UpdateProfileRequestBody;
import com.nithin.secure_user_platform.user.repository.UserRepository;
import com.nithin.secure_user_platform.utility.enums.UserStates;
import com.nithin.secure_user_platform.utility.sharedMethods.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto getUserProfile(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) return entityMapper.userToDto(user.get());
        else throw new UserNotFoundException("User not found!");
    }

    public @Nullable Object updateProfile(
            UpdateProfileRequestBody requestBody,
            UserPrincipal userPrincipal
    ) throws UserNotFoundException {


        Optional<User> user = userRepository.findById(userPrincipal.getUserId());
        if(user.isPresent()){

            if (user.get().getState() != UserStates.ACTIVE)  throw new RuntimeException("Banned users cannot update profile");

            user.get().setFirstName(requestBody.firstName());
            user.get().setLastName(requestBody.lastName());
            userRepository.save(user.get());

            return entityMapper.userToDto(user.get());
        }else throw new UserNotFoundException("User not found");
    }

    public @Nullable Object updatePassword(
            UpdatePasswordRequestBody requestBody,
            UserPrincipal userPrincipal
    ) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(userPrincipal.getUserId());
        if(user.isPresent() && passwordEncoder.matches(requestBody.oldPassword(), user.get().getPasswordHash())){

            user.get().setPasswordHash(passwordEncoder.encode(requestBody.newPassword()));
            userRepository.save(user.get());
            return "Password changed successfully";
        }else throw new UserNotFoundException("User not found");
    }
}
