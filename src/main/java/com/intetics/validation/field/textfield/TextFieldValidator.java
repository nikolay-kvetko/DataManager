package com.intetics.validation.field.textfield;

import com.intetics.bean.TextField;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class TextFieldValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return TextField.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        TextField textField = (TextField) o;
        if (textField.getSize() == null) {
            errors.rejectValue("size", "validation.field.textfield.size",
                    "size must not be empty");
        }
    }
}
