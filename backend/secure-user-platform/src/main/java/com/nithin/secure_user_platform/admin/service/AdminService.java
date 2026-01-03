package com.nithin.secure_user_platform.admin.service;

import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import com.nithin.secure_user_platform.user.domain.User;
import com.nithin.secure_user_platform.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<User> getAllUser() throws UserNotFoundException {
        List<User> users = userRepository.findAll();
        if (users.isEmpty())  throw new UserNotFoundException("No users found in the system");
        return users;
    }
}
