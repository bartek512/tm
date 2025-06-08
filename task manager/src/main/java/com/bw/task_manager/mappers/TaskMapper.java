package com.bw.task_manager.mappers;

import com.bw.task_manager.dto.TaskDTO;
import com.bw.task_manager.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO mapToDto(Task entity);

    Task mapToEntity(TaskDTO dto);
}
