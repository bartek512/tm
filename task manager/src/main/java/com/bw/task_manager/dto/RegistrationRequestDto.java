package com.bw.task_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequestDto {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
