package com.bw.task_manager.controller;

import com.bw.task_manager.dto.JwtResponseDto;
import com.bw.task_manager.dto.LoginRequestDto;
import com.bw.task_manager.entity.User;
import com.bw.task_manager.security.UserPrincipal;
import com.bw.task_manager.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getIdentifier(),
                        loginRequest.getPassword()
                )
        );

        String jwt = jwtService.generateToken(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        JwtResponseDto response = new JwtResponseDto(
                jwt,
                "Bearer",
                userPrincipal.getUsername(),
                userPrincipal.getEmail(),
                userPrincipal.getRole()
        );

        return ResponseEntity.ok(response);
    }
}
