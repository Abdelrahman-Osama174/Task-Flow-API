package com.abdelrahman.taskflow.mapper.impl;

import com.abdelrahman.taskflow.dto.TaskDto;
import com.abdelrahman.taskflow.mapper.TaskMapper;
import com.abdelrahman.taskflow.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDto toDTO(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDeadline(),
                task.getStatus(),
                task.getPriority()
        );
    }


    @Override
    public Task toEntity(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.deadline(),
                taskDto.status(),
                taskDto.priority(),
                null, // taskList will be set separately
                null, // createdAt will be set in the service layer
                null  // updatedAt will be set in the service layer
        );
    }
}
