package com.bw.task_manager.controller;

import com.bw.task_manager.dto.TaskDTO;
import com.bw.task_manager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/tasks")
@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO task) {
        TaskDTO saved = taskService.save(task);

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        TaskDTO task = taskService.findById(taskId);
        taskService.deleteTaskById(taskId);
        return ResponseEntity.ok().build();
    }
}
