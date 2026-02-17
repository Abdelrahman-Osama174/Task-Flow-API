package com.abdelrahman.taskflow.controller;

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
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable("taskListId") UUID taskListId) {
        return ResponseEntity.ok(taskService.getTasks(taskListId));
    }


    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(
            @PathVariable("taskListId") UUID taskListId,
            @PathVariable("taskId") UUID taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskListId, taskId));
    }


    @PostMapping("")
    public ResponseEntity<TaskDto> createTask(
            @PathVariable("taskListId") UUID taskListId,
            @RequestBody TaskDto taskDto) {

        return new ResponseEntity<>(taskService.createTask(taskListId, taskDto), HttpStatus.CREATED);
    }


    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable("taskListId") UUID taskListId,
            @PathVariable("taskId") UUID taskId,
            @RequestBody TaskDto taskDto) {

        return ResponseEntity.ok(taskService.updateTask(taskListId, taskId, taskDto));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(
            @PathVariable("taskListId") UUID taskListId,
            @PathVariable("taskId") UUID taskId) {

        taskService.deleteTask(taskListId, taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }

}
