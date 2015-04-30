package com.intetics.validation;

import com.intetics.bean.Company;
import com.intetics.bean.EntitySchema;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class DuplicateEntityNameValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return EntitySchema.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EntitySchema entitySchema = (EntitySchema) target;
        Company company = entitySchema.getCompany();
        List<EntitySchema> entitySchemas = company.getEntitySchemas();
        for (EntitySchema schema : entitySchemas) {
            if (entitySchema.getName().equals(schema.getName())) {
                errors.rejectValue("name", "validation.entityschema.name.exist",
                        "entity with this name already exist");
            }
        }
    }
}
