package com.intetics.validation;

import com.intetics.bean.EntitySchema;
import com.intetics.dao.EntitySchemaDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class DuplicateEntityConstraintValidator implements ConstraintValidator<DuplicateEntity, String> {

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @Override
    public void initialize(DuplicateEntity constraintAnnotation) {

    }

    @Override
    public boolean isValid(String entityName, ConstraintValidatorContext context) {
        if (entityName == null) {
            return false;
        }
        List<EntitySchema> schemas = entitySchemaDao.getEntitySchemaList();
        if (schemas == null) {
            return false;
        }
        for (EntitySchema schema : schemas) {
            if (schema.getName().equals(entityName)) {
                return false;
            }
        }
        return true;
    }
}
