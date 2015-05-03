package com.intetics.validation.field;

import com.intetics.bean.Field;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class FieldEmptyValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public void validate(Object o, Errors errors) {
        Field field = (Field) o;
        if (field.getName().isEmpty()) {
            errors.rejectValue("name", "validation.field.empty",
                    "field must not be empty");
        }
    }
}
