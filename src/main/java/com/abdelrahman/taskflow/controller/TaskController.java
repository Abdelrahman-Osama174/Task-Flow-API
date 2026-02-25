package com.abdelrahman.taskflow.controller;

import com.abdelrahman.taskflow.dto.ApiResponse;
import com.abdelrahman.taskflow.dto.TaskDto;
import com.abdelrahman.taskflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/task-lists/{taskListId}/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<?>> getTasks(@PathVariable("taskListId") UUID taskListId) {
        List<TaskDto> response = taskService.getTasks(taskListId);
        return ResponseEntity.ok(ApiResponse.success(response, "Tasks retrieved successfully"));
    }


    @GetMapping("/{taskId}")
    public ResponseEntity<ApiResponse<?>> getTaskById(
            @PathVariable("taskListId") UUID taskListId,
            @PathVariable("taskId") UUID taskId) {

        TaskDto response = taskService.getTaskById(taskId, taskListId);
        return ResponseEntity.ok(ApiResponse.success(response, "Task retrieved successfully"));
    }


    @PostMapping("")
    public ResponseEntity<ApiResponse<?>> createTask(
            @PathVariable("taskListId") UUID taskListId,
            @RequestBody TaskDto taskDto) {

        TaskDto response = taskService.createTask(taskListId, taskDto);
        return new ResponseEntity<>(
                ApiResponse.success(response, "Task Created successfully"),
                HttpStatus.CREATED
        );
    }


    @PutMapping("/{taskId}")
    public ResponseEntity<ApiResponse<?>> updateTask(
            @PathVariable("taskListId") UUID taskListId,
            @PathVariable("taskId") UUID taskId,
            @RequestBody TaskDto taskDto) {

        TaskDto response = taskService.updateTask(taskListId, taskId, taskDto);
        return ResponseEntity.ok(ApiResponse.success(response, "Task Updated successfully"));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<ApiResponse<?>> deleteTask(
            @PathVariable("taskListId") UUID taskListId,
            @PathVariable("taskId") UUID taskId) {

        taskService.deleteTask(taskListId, taskId);
        return ResponseEntity.ok(ApiResponse.success(null, "Task deleted successfully"));
    }

}
