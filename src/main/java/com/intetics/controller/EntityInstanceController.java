package com.intetics.controller;

import com.intetics.bean.EntityInstance;
import com.intetics.bean.EntitySchema;
import com.intetics.bean.Field;
import com.intetics.bean.FieldValue;
import com.intetics.dao.EntityInstanceDao;
import com.intetics.dao.EntitySchemaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Controller responsible for processing requests to EntitySchema-related operations
 */
@Controller
@RequestMapping("/entity")
public class EntityInstanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityInstanceController.class);

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @Autowired
    private EntityInstanceDao entityInstanceDao;

    @RequestMapping(value = "/{entitySchemaId}/instance/new", method = RequestMethod.POST)
    public String addEntityInstance(@Nonnull @PathVariable Long entitySchemaId, @RequestParam MultiValueMap<String, String> params) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);

        EntityInstance entityInstance = new EntityInstance();
        entityInstance.setEntitySchema(entitySchema);

        for (Field field : entitySchema.getFields()) {
            List<String> values = params.get(field.getFieldId().toString());
            FieldValue fieldValue = field.getValueType().newValue(values);
            fieldValue.setField(field);
            entityInstance.getValues().add(fieldValue);
        }

        entityInstanceDao.saveOrUpdate(entityInstance);

        return "instance-list";
    }
}
