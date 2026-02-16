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
@RequestMapping("/api/v1/task-lists/{task_list_id}/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("")
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable("task_list_id") UUID taskListId) {
        return ResponseEntity.ok(taskService.getTasks(taskListId));
    }


    @GetMapping("/{task_id}")
    public ResponseEntity<TaskDto> getTaskById(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskListId, taskId));
    }


    @PostMapping("")
    public ResponseEntity<TaskDto> createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto) {

        return new ResponseEntity<>(taskService.createTask(taskListId, taskDto), HttpStatus.CREATED);
    }


    @PutMapping("/{task_id}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId,
            @RequestBody TaskDto taskDto) {

        return ResponseEntity.ok(taskService.updateTask(taskListId, taskId, taskDto));
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<String> deleteTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {

        taskService.deleteTask(taskListId, taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }

}
