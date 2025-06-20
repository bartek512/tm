package com.bw.task_manager.service;

import com.bw.task_manager.dto.UserResponseDto;
import com.bw.task_manager.entity.ConfirmationToken;
import com.bw.task_manager.entity.User;
import com.bw.task_manager.mappers.UserMapper;
import com.bw.task_manager.repository.UserRepository;
import com.bw.task_manager.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = userRepository.findByEmailOrLogin(identifier)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + identifier));

        return UserPrincipal.create(user);
    }

    public UserResponseDto findUserByIdentifier(String identifier) {
        User user = userRepository.findByEmailOrLogin(identifier)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + identifier));

        return userMapper.toUserResponseDto(user);
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = passwordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        User saved = userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                saved
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }
}
