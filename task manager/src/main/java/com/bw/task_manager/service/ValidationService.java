package com.bw.task_manager.service;

import jakarta.mail.internet.InternetAddress;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean verifyEmail(String email) {

        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
