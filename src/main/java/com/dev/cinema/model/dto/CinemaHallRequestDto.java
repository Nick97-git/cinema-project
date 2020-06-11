package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CinemaHallRequestDto {
    @NotNull(message = "Capacity can't be null!")
    private Integer capacity;
    @NotNull(message = "Description can't be null!")
    @Size(min = 8, max = 200, message = "Number of symbols must be between 8 and 200!")
    private String description;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
