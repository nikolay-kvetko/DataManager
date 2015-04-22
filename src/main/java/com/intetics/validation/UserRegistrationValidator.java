package com.intetics.validation;

import com.intetics.bean.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Date: 22.04.2015
 * Time: 10:22
 *
 * @author n.kvetko
 */
public class UserRegistrationValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String STRING_PATTERN = "[a-zA-Z]+";

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmpty(errors, "firstName", "validation.user.firstName.isEmpty",
                "First name must not be empty!");


        if (user.getFirstName() != null && !(user.getFirstName().isEmpty())) {
            pattern = Pattern.compile(STRING_PATTERN);
            matcher = pattern.matcher(user.getFirstName());
            if (!matcher.matches()) {
                errors.rejectValue("firstName", "validation.user.firstName.containNonChar",
                        "Enter a valid first name!");
            }
        }

        ValidationUtils.rejectIfEmpty(errors, "lastName", "validation.user.lastName.isEmpty",
                "Last name must not be empty!");

        if (user.getLastName() != null && !(user.getLastName().isEmpty())) {
            pattern = Pattern.compile(STRING_PATTERN);
            matcher = pattern.matcher(user.getFirstName());
            if (!matcher.matches()) {
                errors.rejectValue("firstName", "validation.user.lastName.containNonChar",
                        "Enter a valid last name!");
            }
        }

        ValidationUtils.rejectIfEmpty(errors, "email", "validation.user.email.isEmpty",
                "Email must not be empty!");

        if (user.getEmail() != null && !(user.getEmail().isEmpty())) {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(user.getEmail());
            if (!matcher.matches()) {
                errors.rejectValue("email", "validation.user.email.incorrect",
                        "Enter a correct email!");
            }
        }

        ValidationUtils.rejectIfEmpty(errors, "password", "validation.user.password.isEmpty",
                "Password must not be empty!");

        if (user.getPassword() != null && !(user.getLastName().isEmpty())) {
            if (!(user.getPassword().equals(user.getConfirmPassword()))) {
                errors.rejectValue("confirmPassword", "validation.user.password.mismatch",
                        "Password does not match!");
            }
        }
    }
}
