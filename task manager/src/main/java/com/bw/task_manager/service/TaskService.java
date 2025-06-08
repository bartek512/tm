package com.bw.task_manager.service;

import com.bw.task_manager.dto.TaskDTO;
import com.bw.task_manager.entity.Task;
import com.bw.task_manager.entity.User;
import com.bw.task_manager.mappers.TaskMapper;
import com.bw.task_manager.repository.TaskRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDTO findById(Long taskId) {
        return taskMapper.mapToDto(taskRepository.findById(taskId).orElse(null));
    }

    public TaskDTO save(TaskDTO task) {
        Task saved = taskRepository.save(taskMapper.mapToEntity(task));
        User currenUser = getCurrenUser();
        return taskMapper.mapToDto(saved);
    }

    public List<TaskDTO> getAll() {
        List<Task> entities = taskRepository.findAll();
        List<TaskDTO> dtos = new ArrayList<>();

        for (Task entity : entities) {
            dtos.add(taskMapper.mapToDto(entity));
        }

        return dtos;
    }

    private User getCurrenUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

}
