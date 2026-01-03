package com.nithin.secure_user_platform.auth.service;

import com.nithin.secure_user_platform.auth.record.LoginRequestBody;
import com.nithin.secure_user_platform.auth.record.RegisterRequestBody;
import com.nithin.secure_user_platform.exception.customExceptions.UserAlreadyExistsException;
import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import com.nithin.secure_user_platform.security.jwt.JwtTokenProvider;
import com.nithin.secure_user_platform.user.domain.User;
import com.nithin.secure_user_platform.user.dto.UserDto;
import com.nithin.secure_user_platform.user.repository.UserRepository;
import com.nithin.secure_user_platform.utility.enums.Roles;
import com.nithin.secure_user_platform.utility.enums.UserStates;
import com.nithin.secure_user_platform.utility.sharedMethods.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EntityMapper entityMapper;

    public UserDto registerUser(RegisterRequestBody requestBody) throws UserAlreadyExistsException {
        Optional<User> user = userRepository.findByUsernameOrEmail(requestBody.username(), requestBody.email());
        if(user.isPresent()) throw new UserAlreadyExistsException("Email already taken");

        User savedUser = userRepository.save(
                new User(
                        null,
                        requestBody.username(),
                        requestBody.firstName(),
                        requestBody.lastName(),
                        requestBody.email(),
                        passwordEncoder.encode(requestBody.password()),
                        Roles.USER,
                        UserStates.ACTIVE,
                        null,
                        null,
                        null
                )
        );
        return entityMapper.userToDto(savedUser);
    }

    public String login(LoginRequestBody requestBody) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUsernameOrEmail(requestBody.identifier(), requestBody.identifier());

        if(user.isPresent() && Objects.equals(user.get().getPasswordHash(), passwordEncoder.encode(requestBody.password()))){
            return jwtTokenProvider.generateJwtToken(user.get());
        } else throw new UserNotFoundException("Invalid credentials or user doesn't exist");
    }
}
