package com.dev.cinema.annotation;

import com.dev.cinema.model.dto.UserRegistrationDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsValueMatchValidator implements
        ConstraintValidator<PasswordsValueMatch, UserRegistrationDto> {

    @Override
    public boolean isValid(UserRegistrationDto userRegistrationDto, ConstraintValidatorContext
            constraintValidatorContext) {
        String password = userRegistrationDto.getPassword();
        String repeatPassword = userRegistrationDto.getRepeatPassword();
        return password != null && password.equals(repeatPassword);
    }
}
