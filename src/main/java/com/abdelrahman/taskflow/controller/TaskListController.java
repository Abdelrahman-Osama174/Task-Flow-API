package com.abdelrahman.taskflow.controller;

import com.abdelrahman.taskflow.dto.ApiResponse;
import com.abdelrahman.taskflow.dto.TaskListDto;
import com.abdelrahman.taskflow.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/task-lists")
public class TaskListController {

    private final TaskListService taskListService;


    @GetMapping()
    public ResponseEntity<ApiResponse<?>> getAllTaskLists() {
        List<TaskListDto> response = taskListService.getTaskLists();
        return ResponseEntity.ok(ApiResponse.success(response, "Task lists retrieved successfully"));
    }


    @GetMapping("/{taskListId}")
    public ResponseEntity<ApiResponse<?>> getTaskList(@PathVariable("taskListId") UUID id) {
        TaskListDto response = taskListService.getTaskListById(id);
        return ResponseEntity.ok(ApiResponse.success(response, "Task list retrieved successfully"));
    }


    @PostMapping()
    public ResponseEntity<ApiResponse<?>> createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskListDto response = taskListService.createTaskList(taskListDto);
        return new ResponseEntity<>(
                ApiResponse.success(response, "Task list created successfully"),
                HttpStatus.CREATED
        );
    }


    @PutMapping("/{taskListId}")
    public ResponseEntity<ApiResponse<?>> updateTaskList(
            @PathVariable("taskListId") UUID id,
            @RequestBody TaskListDto taskListDto) {

        TaskListDto response = taskListService.updateTaskList(id, taskListDto);
        return ResponseEntity.ok(ApiResponse.success(response, "Task list updated successfully"));
    }


    @DeleteMapping("/{taskListId}")
    public ResponseEntity<ApiResponse<?>> deleteTaskList(@PathVariable("taskListId") UUID id) {
        taskListService.deleteTaskList(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Task list deleted successfully"));
    }

}
