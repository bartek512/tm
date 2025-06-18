package com.bw.task_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Value;
import jakarta.validation.constraints.NotBlank;

@Value
public class RegistrationRequestDto {

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    @Size(min = 3, max = 20)
    String login;

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(min = 6)
    String password;
}
