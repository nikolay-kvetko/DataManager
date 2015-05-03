package com.intetics.validation.field.number;

import com.intetics.bean.NumberField;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class NumberValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return NumberField.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        NumberField numberField = (NumberField) o;
        if (numberField.getMinValue() == null) {
            errors.rejectValue("minValue", "validation.field.numberfield.minvalue",
                    "min value must not be empty");
        } else if (numberField.getMaxValue() == null) {
            errors.rejectValue("maxValue", "validation.field.numberfield.maxvalue",
                    "max value must not be empty");
        } else if (numberField.getNumberDecimal() == null) {
            errors.rejectValue("numberDecimal", "validation.field.numberfield.numberDecimal",
                    "number of decimal places must not be empty");
        }
    }
}
