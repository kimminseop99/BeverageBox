package com.beverage.BeverageBox.controller;

import com.beverage.BeverageBox.dto.request.LoginRequestDto;
import com.beverage.BeverageBox.dto.request.SignupRequestDto;
import com.beverage.BeverageBox.dto.response.UserResponseDto;
import com.beverage.BeverageBox.entity.User;
import com.beverage.BeverageBox.repository.UserRepository;
import com.beverage.BeverageBox.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public UserResponseDto signup(@Valid @RequestBody SignupRequestDto dto) {
        return userService.signup(dto);
    }

    @PostMapping("/login")
    public UserResponseDto login(@Valid @RequestBody LoginRequestDto dto, HttpSession session) {
        return userService.login(dto, session);
    }

    @GetMapping("/me")
    public UserResponseDto myInfo(HttpSession session) {
        Long userId = (Long) session.getAttribute("LOGIN_USER");
        if (userId == null) throw new IllegalArgumentException("로그인이 필요합니다.");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }

}
