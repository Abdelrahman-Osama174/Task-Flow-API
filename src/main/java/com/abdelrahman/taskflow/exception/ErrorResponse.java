package com.abdelrahman.taskflow.exception;

import java.time.LocalDateTime;

public record ErrorResponse (
        LocalDateTime timestamp,
        int status,
        String message,
        String details
){
}
