package com.nithin.secure_user_platform.utility.records;

import java.time.LocalDateTime;

public record ErrorResponseBody(
        int code,
        String message,
        String path,
        LocalDateTime dateTime
) { }
