package com.abdelrahman.taskflow.dto;

import com.abdelrahman.taskflow.enums.TaskPriority;
import com.abdelrahman.taskflow.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime deadline,
        TaskStatus status,
        TaskPriority priority
) {
}
