package com.bw.task_manager.service;

import com.bw.task_manager.dto.TaskDTO;
import com.bw.task_manager.entity.Task;
import com.bw.task_manager.entity.User;
import com.bw.task_manager.mappers.TaskMapper;
import com.bw.task_manager.repository.TaskRepository;
import com.bw.task_manager.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDTO findById(Long taskId) {
        User currentUser = validateAndGetUser();

        Task task = taskRepository.findByIdAndUser(taskId, currentUser)
                .orElseThrow(() -> new RuntimeException("Task not found or access denied"));

        return taskMapper.mapToDto(task);
    }

    public TaskDTO save(TaskDTO taskDto) {
        User currentUser = validateAndGetUser();

        Task task = taskMapper.mapToEntity(taskDto);
        task.setUser(currentUser);

        return taskMapper.mapToDto(taskRepository.save(task));
    }

    public List<TaskDTO> getAllForUser() {
        User currentUser = validateAndGetUser();

        List<Task> userTasks = taskRepository.findAllByUserOrderByTimestampDesc(currentUser);

        return userTasks.stream()
                .map(taskMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void deleteTaskById(Long taskId) {
        User currentUser = validateAndGetUser();

        Task task = taskRepository.findByIdAndUser(taskId, currentUser)
                .orElseThrow(() -> new RuntimeException("Task not found or access denied"));

        taskRepository.delete(task);
    }

    public TaskDTO updateTask(Long taskId, TaskDTO taskDto) {
        User currentUser = validateAndGetUser();

        Task existingTask = taskRepository.findByIdAndUser(taskId, currentUser).orElseThrow(()
                -> new RuntimeException("Task not found or access denied"));

        if (taskDto.getTitle() != null) {
            existingTask.setTitle(taskDto.getTitle());
        }
        if (taskDto.getDescription() != null) {
            existingTask.setDescription(taskDto.getDescription());
        }
        existingTask.setCompleted(taskDto.isCompleted());

        Task updated = taskRepository.save(existingTask);

        return taskMapper.mapToDto(updated);
    }


    private User validateAndGetUser() {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return currentUser;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        return userRepository.findByEmailOrLogin(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found: " + authentication.getName()));
    }

}

