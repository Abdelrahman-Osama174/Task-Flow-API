package com.abdelrahman.taskflow.service;

import com.abdelrahman.taskflow.dto.TaskListDto;
import com.abdelrahman.taskflow.exception.ResourceNotFoundException;
import com.abdelrahman.taskflow.mapper.TaskListMapper;
import com.abdelrahman.taskflow.model.TaskList;
import com.abdelrahman.taskflow.repository.TaskListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskListService {

    private final TaskListRepo taskListRepo;
    private final TaskListMapper taskListMapper;


    public List<TaskListDto> getTaskLists() {
        return taskListRepo.findAll().stream()
                .map(taskListMapper::toDto)
                .toList();
    }


    public TaskListDto getTaskListById(UUID id) {
        return taskListRepo.findById(id)
                .map(taskListMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Task list not found"));
    }


    public TaskListDto createTaskList(TaskListDto taskListDto) {
        if (taskListDto.id() != null) {
            throw new IllegalArgumentException("New task list cannot have an ID");
        }

        if (taskListDto.title() == null || taskListDto.title().isEmpty()) {
            throw new IllegalArgumentException("Task list title cannot be null or empty");
        }

        TaskList taskList = taskListMapper.toEntity(taskListDto);

        TaskList savedTaskList = taskListRepo.save(taskList);

        return taskListMapper.toDto(savedTaskList);
    }


    @Transactional
    public TaskListDto updateTaskList(UUID id, TaskListDto taskListDto) {
        if(id == null) {
            throw new IllegalArgumentException("Task list ID cannot be null");
        }

        TaskList exitingTaskList = taskListRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task list not found"));

        exitingTaskList.setTitle(taskListDto.title());
        exitingTaskList.setDescription(taskListDto.description());

        TaskList updatedTaskList = taskListRepo.save(exitingTaskList);

        return taskListMapper.toDto(updatedTaskList);
    }


    @Transactional
    public void deleteTaskList(UUID id) {
        TaskList exitingTaskList = taskListRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task list not found"));

        taskListRepo.delete(exitingTaskList); // has @Transactional behind the scene
    }

}
