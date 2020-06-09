package com.dev.cinema.dto;

public class UserResponseDto {
    private String email;

    public UserResponseDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
