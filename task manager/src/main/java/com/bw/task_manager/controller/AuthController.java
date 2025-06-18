package com.bw.task_manager.controller;

import com.bw.task_manager.dto.JwtResponseDto;
import com.bw.task_manager.dto.LoginRequestDto;
import com.bw.task_manager.dto.UserResponseDto;
import com.bw.task_manager.entity.User;
import com.bw.task_manager.security.JwtUtil;
import com.bw.task_manager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getIdentifier(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserResponseDto user = userService.findUserByIdentifier(loginRequest.getIdentifier());

        return ResponseEntity.ok(new JwtResponseDto(jwt,
                user.getLogin(),
                user.getEmail(),
                user.getUserRole()));
    }
}
