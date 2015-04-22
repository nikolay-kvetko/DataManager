package com.intetics.validation;

import com.intetics.bean.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserRegistrationValidatorTest {

    private UserRegistrationValidator validator;
    private User user;
    private Errors errors;

    @Before
    public void init() {
        validator = new UserRegistrationValidator();
    }

    @Test
    public void testFieldsAreEmpty() {
        user = new User();
        errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void testFirstNameIsEmpty() {
        user = new User();
        fillUserFields(user);
        user.setFirstName("");
        errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void testLastNameIsEmpty() {
        user = new User();
        fillUserFields(user);
        user.setLastName("");
        errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void testEmailIsEmpty() {
        user = new User();
        fillUserFields(user);
        user.setEmail("");
        errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void testPasswordIsEmpty() {
        user = new User();
        fillUserFields(user);
        user.setPassword("");
        errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void testFirstNameAndLastNameContainIllegalCharacter(){
        user = new User();
        user.setFirstName("123");
        user.setLastName("123");
        errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void testEmailIsIncorrect() {
        user = new User();
        fillUserFields(user);
        user.setEmail("nik@nik");
        errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void testEmailIsCorrect() {
        user = new User();
        fillUserFields(user);
        user.setEmail("Nikol@y.by");
        errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testPasswordMatch() {
        user = new User();
        fillUserFields(user);
        errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testPasswordMismatch() {
        user = new User();
        fillUserFields(user);
        user.setConfirmPassword("AnotherPassword");
        errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        assertTrue(errors.hasErrors());
    }

    private void fillUserFields(User user) {
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setEmail("em@il.by");
        user.setPassword("Password");
        user.setConfirmPassword("Password");
    }
}
