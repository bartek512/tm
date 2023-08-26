package com.bw.task_manager.service;

import com.bw.task_manager.dto.TaskDTO;
import com.bw.task_manager.entity.Task;
import com.bw.task_manager.reposirory.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDTO findById(Long taskId){
        return mapToDto(taskRepository.findById(taskId).orElse(null));
    }

    public TaskDTO save(TaskDTO task) {
        Task saved = taskRepository.save(mapToEntity(task));
        return mapToDto(saved);
    }

    public List<TaskDTO> getAll() {
        List<Task> entities = taskRepository.findAll();
        List<TaskDTO> dtos = new ArrayList<>();

        for (Task entity : entities) {
            dtos.add(mapToDto(entity));
        }

        return dtos;
    }

    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private Task mapToEntity(TaskDTO dto) {
        Task entity = new Task();
        entity.setId((new Random().nextLong(10000000)));

        BeanUtils.copyProperties(dto, entity, "id");

        return entity;
    }

    private TaskDTO mapToDto(Task entity) {
        TaskDTO dto = new TaskDTO();

        BeanUtils.copyProperties(entity, dto);

        return dto;
    }
}
