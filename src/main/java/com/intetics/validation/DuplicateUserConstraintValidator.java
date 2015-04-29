package com.intetics.validation;

import com.intetics.bean.User;
import com.intetics.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DuplicateUserConstraintValidator implements ConstraintValidator<DuplicateUser, String> {

    @Autowired
    private UserDao userDao;

    @Override
    public void initialize(DuplicateUser constraintAnnotation) {

    }

    @Override
    public boolean isValid(String userEmail, ConstraintValidatorContext context) {
        if (userEmail == null) {
            return false;
        }
        User user = userDao.getUserByEmail(userEmail);
        if (user != null) {
            return false;
        }
        return true;
    }
}
