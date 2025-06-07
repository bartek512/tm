package com.bw.task_manager.dto;

import lombok.Value;

@Value
public class RegistrationRequestDto {
    String firstName;
    String lastName;
    String email;
    String password;
}
