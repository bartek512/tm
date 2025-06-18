package com.bw.task_manager.dto;

import com.bw.task_manager.entity.User;
import com.bw.task_manager.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private UserRole userRole;
    private boolean enabled;

}
