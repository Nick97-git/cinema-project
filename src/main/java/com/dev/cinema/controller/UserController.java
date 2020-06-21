package com.dev.cinema.controller;

import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/by-email")
    public UserResponseDto findUserByEmail(@RequestParam String email) {
        User user = userService.getByEmail(email);
        return userMapper.convertToResponseDto(user);
    }
}
