package com.bw.task_manager.controller;

import com.bw.task_manager.dto.TaskDTO;
import com.bw.task_manager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/tasks")
@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO task) {
        TaskDTO saved = taskService.save(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getUserTasks() {
        return ResponseEntity.ok(taskService.getAllForUser());
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        TaskDTO task = taskService.findById(taskId);
        taskService.deleteTaskById(taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDto) {
        TaskDTO updated = taskService.updateTask(taskId, taskDto);
        return ResponseEntity.ok(updated);
    }
}
