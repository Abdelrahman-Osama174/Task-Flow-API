package com.abdelrahman.taskflow.mapper;

import com.abdelrahman.taskflow.dto.TaskListDto;
import com.abdelrahman.taskflow.model.TaskList;

public interface TaskListMapper {

    TaskListDto toDto(TaskList taskList);

    TaskList toEntity(TaskListDto taskListDTO);
}
