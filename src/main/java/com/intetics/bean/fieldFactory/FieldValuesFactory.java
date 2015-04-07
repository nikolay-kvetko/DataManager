package com.intetics.bean.fieldFactory;

import com.intetics.bean.EntityInstance;
import com.intetics.bean.Field;
import com.intetics.bean.FieldValue;

import java.util.Set;

/**
 * Created by Кузнец on 05.04.2015.
 */

public class FieldValuesFactory {

    public boolean setValue(Set<Field> fields, EntityInstance entityInstance, Object value) {

        for(Field field : fields){

            if (field.getType().equalsIgnoreCase("StringText") && value instanceof String){

                FieldValue fieldValue = new FieldValue();
                fieldValue.setTextValue((String) value);

                fieldValue.setField(field);
                field.getValues().add(fieldValue);

                fieldValue.setEntityInstance(entityInstance);
                entityInstance.getFieldValues().add(fieldValue);
                return true;

            } else if(field.getType().equalsIgnoreCase("Number") && value instanceof Double){

                FieldValue fieldValue = new FieldValue();
                fieldValue.setNumberValue((Double) value);

                fieldValue.setField(field);
                field.getValues().add(fieldValue);

                fieldValue.setEntityInstance(entityInstance);
                entityInstance.getFieldValues().add(fieldValue);
                return true;
            }
        }
        return false;
    }
}
