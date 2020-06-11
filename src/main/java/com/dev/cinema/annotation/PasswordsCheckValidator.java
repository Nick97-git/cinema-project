package com.dev.cinema.annotation;

import com.dev.cinema.model.dto.UserRegistrationDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsCheckValidator implements
        ConstraintValidator<PasswordsCheck, UserRegistrationDto> {

    @Override
    public boolean isValid(UserRegistrationDto userRegistrationDto, ConstraintValidatorContext
            constraintValidatorContext) {
        return userRegistrationDto.getPassword().equals(userRegistrationDto.getRepeatPassword());
    }
}
