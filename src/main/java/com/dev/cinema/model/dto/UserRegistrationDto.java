package com.dev.cinema.model.dto;

import com.dev.cinema.annotation.EmailConstraint;
import com.dev.cinema.annotation.PasswordsCheck;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordsCheck
public class UserRegistrationDto {
    @NotNull(message = "Email can't be null!")
    @EmailConstraint
    private String email;
    @NotNull(message = "Password can't be null!")
    @Size(min = 8, message = "Number of symbols of password must be greater or equal 8!")
    private String password;
    @NotNull(message = "Repeat password can't be null!")
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
