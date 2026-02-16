package com.abdelrahman.taskflow.mapper;

import com.abdelrahman.taskflow.dto.TaskDto;
import com.abdelrahman.taskflow.model.Task;

public interface TaskMapper {

    TaskDto toDTO(Task task);

    Task toEntity(TaskDto taskDTO);
}
