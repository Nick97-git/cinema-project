package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class OrderRequestDto {
    @NotNull(message = "User id can't be null!")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
