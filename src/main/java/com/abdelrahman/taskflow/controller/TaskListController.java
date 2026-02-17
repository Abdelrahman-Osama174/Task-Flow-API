package com.abdelrahman.taskflow.controller;

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
    public ResponseEntity<List<TaskListDto>> getAllTaskLists() {
        return ResponseEntity.ok(taskListService.getTaskLists());
    }


    @GetMapping("/{taskListId}")
    public ResponseEntity<TaskListDto> getTaskList(@PathVariable("taskListId") UUID id) {
        return ResponseEntity.ok(taskListService.getTaskListById(id));
    }


    @PostMapping()
    public ResponseEntity<TaskListDto> createTaskList(@RequestBody TaskListDto taskListDto) {
        return new ResponseEntity<>(taskListService.createTaskList(taskListDto), HttpStatus.CREATED);
    }


    @PutMapping("/{taskListId}")
    public ResponseEntity<TaskListDto> updateTaskList(
            @PathVariable("taskListId") UUID id,
            @RequestBody TaskListDto taskListDto) {

        return ResponseEntity.ok(taskListService.updateTaskList(id, taskListDto));
    }


    @DeleteMapping("/{taskListId}")
    public ResponseEntity<String> deleteTaskList(@PathVariable("taskListId") UUID id) {
        taskListService.deleteTaskList(id);
        return ResponseEntity.ok().body("Task list deleted successfully");
    }

}
