package com.intetics.validation;

import com.intetics.bean.User;
import com.intetics.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserExistValidator implements Validator {

    @Autowired
    private UserDao userDao;


    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object target, Errors errors) {
        User currentUser = (User) target;
        User existUser = userDao.getUserByEmail(currentUser.getEmail());
        if (existUser != null) {
            errors.rejectValue("email", "validation.user.email.exist",
                    "user with this email already exist");
        }
    }
}
