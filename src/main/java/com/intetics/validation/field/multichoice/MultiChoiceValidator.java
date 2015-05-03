package com.intetics.validation.field.multichoice;

import com.intetics.bean.MultiChoiceField;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class MultiChoiceValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return MultiChoiceField.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        MultiChoiceField multiChoiceField = (MultiChoiceField) o;
        if (multiChoiceField.getChoices() == null) {
            errors.rejectValue("choices", "validation.field.multichoice.values",
                    "choices must not be empty");
        }
    }
}
