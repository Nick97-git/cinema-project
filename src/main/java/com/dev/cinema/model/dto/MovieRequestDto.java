package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovieRequestDto {
    @NotNull(message = "Title can't be null!")
    @Size(min = 1, max = 30, message = "Number of symbols must be between 1 and 30!")
    private String title;
    @NotNull(message = "Description can't be null!")
    @Size(min = 10, max = 500, message = "Number of symbols must be between 10 and 500!")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
