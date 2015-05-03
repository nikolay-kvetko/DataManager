package com.intetics.validation.field.textarea;

import com.intetics.bean.TextAreaField;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class TextAreaValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return TextAreaField.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        TextAreaField textAreaField = (TextAreaField) o;
        if (textAreaField.getCountLine() == null) {
            errors.rejectValue("countLine", "validation.field.textarea.countline",
                    "count line must not be empty");
        }
        if (textAreaField.getCountLine() != null && (textAreaField.getCountLine() < 0 || textAreaField.getCountLine() > 5)) {
            errors.rejectValue("countLine", "validation.field.textarea.countlineminmax",
                    "count line can be from 1 to 5");
        }
    }
}
