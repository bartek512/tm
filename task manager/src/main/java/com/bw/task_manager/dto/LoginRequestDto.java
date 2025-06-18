package com.bw.task_manager.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class LoginRequestDto {

    @NotBlank
    private String identifier;

    @NotBlank
    private String password;
}
