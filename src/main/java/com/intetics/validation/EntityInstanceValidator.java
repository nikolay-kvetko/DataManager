package com.intetics.validation;

import com.intetics.bean.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.List;


public class EntityInstanceValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return EntityInstance.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        EntityInstance entityInstance = (EntityInstance) o;
        List<FieldValue> fieldValues = entityInstance.getValues();
        int counter = 0;
        for (FieldValue fieldValue : fieldValues) {
            if (fieldValue.getField().getValueType().equals(ValueType.STRING)) {
                String stringValue = ((StringValue) entityInstance.getValues().get(counter)).getValue();
                if (fieldValue.getField().getRequire() && stringValue.isEmpty()) {
                    errors.rejectValue("values", "validation.entityinstance.empty",
                            "value must not be empty");
                }
                counter++;
                continue;
            } else if (fieldValue.getField().getValueType().equals(ValueType.TEXT_AREA)) {
                String textAreaValue = ((TextAreaValue) entityInstance.getValues().get(counter)).getTextAreaValue();
                if (fieldValue.getField().getRequire() && textAreaValue.isEmpty()) {
                    errors.rejectValue("values", "validation.entityinstance.empty",
                            "value must not be empty");
                }
                counter++;
                continue;
            } else if (fieldValue.getField().getValueType().equals(ValueType.MULTI_CHOICE)) {
                List<Choice> choices = ((MultiChoiceValue) entityInstance.getValues().get(counter)).getChoices();
                if (fieldValue.getField().getRequire() && choices.isEmpty()) {
                    errors.rejectValue("values", "validation.entityinstance.empty",
                            "value must not be empty");
                }
                counter++;
                continue;
            } else if (fieldValue.getField().getValueType().equals(ValueType.NUMBER)) {
                Double numberValue = ((NumberValue) entityInstance.getValues().get(counter)).getNumberValue();
                if (fieldValue.getField().getRequire() && numberValue == null) {
                    errors.rejectValue("values", "validation.entityinstance.empty",
                            "value must not be empty");
                }
                counter++;
                continue;
            } else if (fieldValue.getField().getValueType().equals(ValueType.DATE)) {
                Date dateValue = ((DateValue) entityInstance.getValues().get(counter)).getDateValue();
                if (fieldValue.getField().getRequire() && dateValue == null) {
                    errors.rejectValue("values", "validation.entityinstance.empty",
                            "value must not be empty");
                }
                counter++;
                continue;
            } else if (fieldValue.getField().getValueType().equals(ValueType.IMAGE)) {
                String imageValue = ((ImageValue) entityInstance.getValues().get(counter)).getImage();
                if (fieldValue.getField().getRequire() && imageValue.isEmpty()) {
                    errors.rejectValue("values", "validation.entityinstance.empty",
                            "value must not be empty");
                }
                counter++;
                continue;
            } else if (fieldValue.getField().getValueType().equals(ValueType.GPS)) {
                Double latitudeValue = ((GPSValue) entityInstance.getValues().get(counter)).getLatitudeValue();
                Double longitudeValue = ((GPSValue) entityInstance.getValues().get(counter)).getLongitudeValue();
                if (fieldValue.getField().getRequire() && (latitudeValue == null && longitudeValue == null)) {
                    errors.rejectValue("values", "validation.entityinstance.empty",
                            "value must not be empty");
                }
                counter++;
                continue;
            }
        }
    }
}
