package com.beverage.BeverageBox.controller;

import com.beverage.BeverageBox.dto.request.SignupRequestDto;
import com.beverage.BeverageBox.dto.response.UserResponseDto;
import com.beverage.BeverageBox.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody SignupRequestDto dto) {
        return userService.signup(dto);
    }
}
