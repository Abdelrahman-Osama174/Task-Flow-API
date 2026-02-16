package com.abdelrahman.taskflow.mapper.impl;

import com.abdelrahman.taskflow.dto.TaskListDto;
import com.abdelrahman.taskflow.enums.TaskStatus;
import com.abdelrahman.taskflow.mapper.TaskListMapper;
import com.abdelrahman.taskflow.mapper.TaskMapper;
import com.abdelrahman.taskflow.model.Task;
import com.abdelrahman.taskflow.model.TaskList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toDTO)
                                .toList()
                        ).orElse(null)
        );
    }


    @Override
    public TaskList toEntity(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toEntity)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }


    private double calculateProgress(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return 0.0;
        }

        long closedTasks = tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.CLOSED)
                .count();

        return (double) closedTasks / tasks.size() * 100;
    }
}
