package com.bw.task_manager.controller;

import com.bw.task_manager.dto.RegistrationRequestDto;
import com.bw.task_manager.service.RegistrationService;
import jakarta.mail.internet.AddressException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequestDto request) throws AddressException {
        return registrationService.register(request);
    }
}