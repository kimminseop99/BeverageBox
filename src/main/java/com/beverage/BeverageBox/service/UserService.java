package com.beverage.BeverageBox.service;

import com.beverage.BeverageBox.dto.request.SignupRequestDto;
import com.beverage.BeverageBox.dto.response.UserResponseDto;
import com.beverage.BeverageBox.entity.User;
import com.beverage.BeverageBox.entity.UserRole;
import com.beverage.BeverageBox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto signup(SignupRequestDto dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(UserRole.USER)
                .build();

        userRepository.save(user);

        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole().name());
    }
}
