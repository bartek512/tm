package com.bw.task_manager.dto;

import com.bw.task_manager.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDto {
    private String token;
    private String type;
    private String username;
    private String email;
    private UserRole role;
}