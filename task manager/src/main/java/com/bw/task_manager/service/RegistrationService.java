package com.bw.task_manager.service;

import com.bw.task_manager.dto.RegistrationRequestDto;
import com.bw.task_manager.entity.User;
import com.bw.task_manager.enums.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final ValidationService validationService;

    public String register(RegistrationRequestDto request) throws AddressException {
        if (!validationService.verifyEmail(request.email())) {
            throw new AddressException("Email is incorrect");
        }

        User user = new User();
        user.setEmail(request.email());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setPassword(request.password());
        user.setUserRole(UserRole.USER);

        return userService.signUpUser(user);
    }
}
