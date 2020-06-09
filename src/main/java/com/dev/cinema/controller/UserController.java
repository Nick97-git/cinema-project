package com.dev.cinema.controller;

import com.dev.cinema.dto.UserResponseDto;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/byemail")
    public UserResponseDto findUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        return new UserResponseDto(user.getEmail());
    }
}
