package com.intetics.validation;

import com.intetics.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserAuthorizationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "validation.user.email.isEmpty",
                "Email must not be empty!");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validation.user.password.isEmpty",
                "Password must not be empty!");

    }
}
