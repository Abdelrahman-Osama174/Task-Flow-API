package com.abdelrahman.taskflow.service;

import com.abdelrahman.taskflow.dto.TaskDto;
import com.abdelrahman.taskflow.enums.TaskPriority;
import com.abdelrahman.taskflow.enums.TaskStatus;
import com.abdelrahman.taskflow.exception.ResourceNotFoundException;
import com.abdelrahman.taskflow.mapper.TaskMapper;
import com.abdelrahman.taskflow.model.Task;
import com.abdelrahman.taskflow.model.TaskList;
import com.abdelrahman.taskflow.repository.TaskListRepo;
import com.abdelrahman.taskflow.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;
    private final TaskListRepo taskListRepo;



    public List<TaskDto> getTasks(UUID taskListId) {
        if (taskListId == null) {
            throw new IllegalArgumentException("Task list ID cannot be null");
        }

        List<Task> tasks = taskRepo.findByTaskListId(taskListId);

        return tasks.stream()
                .map(taskMapper::toDTO)
                .toList();
    }


    public TaskDto getTaskById(UUID taskListId, UUID taskId) {
        if (taskListId == null) {
            throw new IllegalArgumentException("Task list ID cannot be null");
        }

        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }

        Task task = taskRepo.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id: " + taskId + " not found"));

        return taskMapper.toDTO(task);
    }


    @Transactional
    public TaskDto createTask(UUID taskListId, TaskDto taskDto) {
        if (taskListId == null) {
            throw new IllegalArgumentException("Task list ID cannot be null");
        }

        TaskList taskList = taskListRepo.findById(taskListId)
                .orElseThrow(() -> new ResourceNotFoundException("Task List not found"));

        if (taskDto.id() != null) {
            throw new IllegalArgumentException("New task cannot have an ID");
        }

        if (taskDto.title() == null || taskDto.title().equals("")) {
            throw new IllegalArgumentException("Task title cannot be null or empty");
        }

        Task task = taskMapper.toEntity(taskDto);

        task.setPriority(Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM));
        task.setStatus(TaskStatus.OPEN);
        task.setTaskList(taskList);

        Task createdTask = taskRepo.save(task);

        return taskMapper.toDTO(createdTask);
    }


    @Transactional
    public TaskDto updateTask(UUID taskListId, UUID taskId, TaskDto taskDto) {
        if (taskListId == null) {
            throw new IllegalArgumentException("Task list ID cannot be null");
        }

        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }

        if (taskDto.priority() == null) {
            throw new IllegalArgumentException("Task priority cannot be null");
        }

        if (taskDto.status() == null) {
            throw new IllegalArgumentException("Task status cannot be null");
        }

        Task task = taskRepo.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id: " + taskId + " not found"));

        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setDeadline(taskDto.deadline());
        task.setStatus(taskDto.status());
        task.setPriority(taskDto.priority());

        Task updatedTask = taskRepo.save(task);

        return taskMapper.toDTO(updatedTask);
    }


    @Transactional
    public void deleteTask(UUID taskListId, UUID taskId) {
        Task task = taskRepo.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id: " + taskId + " not found"));

        taskRepo.delete(task);
    }

}
