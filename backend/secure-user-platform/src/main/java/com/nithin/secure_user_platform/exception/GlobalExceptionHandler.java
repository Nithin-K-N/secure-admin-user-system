package com.nithin.secure_user_platform.exception;

import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import com.nithin.secure_user_platform.utility.records.ErrorResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException e, WebRequest request){
        return new ResponseEntity<>(
                new ErrorResponseBody(
                    HttpStatus.NOT_FOUND.value(),
                    e.getMessage(),
                    request.getDescription(false),
                    LocalDateTime.now()
                ),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleAllExceptions(Exception e, WebRequest request){
        return new ResponseEntity<>(
                new ErrorResponseBody(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage(),
                        request.getDescription(false),
                        LocalDateTime.now()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
