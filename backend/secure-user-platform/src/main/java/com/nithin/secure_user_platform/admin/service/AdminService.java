package com.nithin.secure_user_platform.admin.service;

import com.nithin.secure_user_platform.admin.domain.records.MessageResponse;
import com.nithin.secure_user_platform.admin.domain.records.UserIdentificationRequestBody;
import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import com.nithin.secure_user_platform.user.domain.dto.UserDto;
import com.nithin.secure_user_platform.user.domain.entities.User;
import com.nithin.secure_user_platform.user.repository.UserRepository;
import com.nithin.secure_user_platform.utility.enums.Roles;
import com.nithin.secure_user_platform.utility.enums.UserStates;
import com.nithin.secure_user_platform.utility.sharedMethods.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;

    public List<UserDto> getAllUser() throws UserNotFoundException {

        if (userRepository.count() == 0)  throw new UserNotFoundException("No users found in the system");
        return userRepository.findAll().stream().map(entityMapper::userToDto).toList();
    }

    public @Nullable MessageResponse banUser(
            UserIdentificationRequestBody requestBody
    ) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(requestBody.userId());
        if(user.isPresent()){
            if(user.get().getState() != UserStates.BANNED){
                user.get().ban();
                userRepository.save(user.get());
                return new MessageResponse(String.format("User %s banned", requestBody.username()));
            }

            return new MessageResponse(String.format("User %s is already banned", requestBody.username()));
        }else throw new UserNotFoundException("User not found");
    }

    public MessageResponse unbanUser(
            UserIdentificationRequestBody requestBody
    ) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(requestBody.userId());
        if(user.isPresent()){
            if(user.get().getState() == UserStates.BANNED){
                user.get().activate();
                userRepository.save(user.get());
                return new MessageResponse(String.format("User %s activated", requestBody.username()));
            }

            return new MessageResponse(String.format("User %s, is already active", requestBody.username()));
        }else throw new UserNotFoundException("User not found");
    }

    public MessageResponse promoteUser(
            UserIdentificationRequestBody requestBody
    ) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(requestBody.userId());
        if(user.isPresent()){
            if(user.get().getRole() == Roles.USER){
                user.get().promote();
                userRepository.save(user.get());
                return new MessageResponse(String.format("User %s is promoted as Admin", requestBody.username()));
            }

            return new MessageResponse(String.format("User %s is already an Admin", requestBody.username()));
        }else throw new UserNotFoundException("User not found");
    }

    public MessageResponse demoteUser(
            UserIdentificationRequestBody requestBody
    ) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(requestBody.userId());
        if(user.isPresent()){
            if(user.get().getRole() != Roles.USER){
                user.get().demote();
                userRepository.save(user.get());
                return new MessageResponse(String.format("User %s is demoted as User", requestBody.username()));
            }

            return new MessageResponse(String.format("User %s is already an User", requestBody.username()));
        }else throw new UserNotFoundException("User not found");
    }
}
