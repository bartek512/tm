package com.bw.task_manager.mappers;

import com.bw.task_manager.dto.UserResponseDto;
import com.bw.task_manager.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toUserResponseDto(User user);
}
