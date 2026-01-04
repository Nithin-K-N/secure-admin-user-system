package com.nithin.secure_user_platform.exception;

import com.nithin.secure_user_platform.exception.customExceptions.UserNotFoundException;
import com.nithin.secure_user_platform.utility.records.ErrorResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception e, WebRequest request){
        log.error("Exception occured: {}", e);
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
