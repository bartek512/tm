package com.bw.task_manager.service;

import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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
