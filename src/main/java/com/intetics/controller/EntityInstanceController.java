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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for processing requests to EntitySchema-related operations
 */
@Controller
@RequestMapping("/home/entity")
public class EntityInstanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityInstanceController.class);

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @Autowired
    private EntityInstanceDao entityInstanceDao;

    @RequestMapping(value = "/list")
    public String getHomeEntitySchemaList(ModelMap model) {

        List<EntitySchema> entitySchemas = entitySchemaDao.getEntitySchemaList();
        model.addAttribute("entitySchemaList", entitySchemas);

        return "home-entity-list";

    }

    @RequestMapping(value = "/{entitySchemaId}/instance/list", method = RequestMethod.GET)
    public String getEntitySchemaInstancesList(@Nonnull @PathVariable Long entitySchemaId, Model model) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        List<EntityInstance> entityInstances = entityInstanceDao.getEntityInstancesByEntitySchema(entitySchema);
        model.addAttribute("entityInstances", entityInstances);

        return "home-entity-instance-list";
    }

    @RequestMapping(value = "/{entitySchemaId}/instance/create")
    public String createEntitySchemaInstance(@Nonnull @PathVariable Long entitySchemaId, Model model) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        List<EntityInstance> entityInstances = entityInstanceDao.getEntityInstancesByEntitySchema(entitySchema);
        model.addAttribute("entityInstances", entityInstances);

        model.addAttribute("modalSaveButton", "Create");

        return "create-instance";
    }

    @RequestMapping(value = "/{entitySchemaId}/instance/edit/{entityInstanceId}")
    public String editEntitySchemaInstance(@Nonnull @PathVariable Long entitySchemaId,
                                           @Nonnull @PathVariable Long entityInstanceId,
                                           Model model) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        List<EntityInstance> instances = entityInstanceDao.getEntityInstancesByEntitySchema(entitySchema);
        EntityInstance entityInstance = instances.get(entityInstanceId.intValue());
        model.addAttribute("entityInstance", entityInstance);

        List<EntityInstance> entityInstances = entityInstanceDao.getEntityInstancesByEntitySchema(entitySchema);
        model.addAttribute("entityInstances", entityInstances);

        model.addAttribute("modalSaveButton", "Create");

        return "edit-instance";
    }

    @RequestMapping(value = "/{entitySchemaId}/instance/add", method = RequestMethod.POST)
    public String addEntityInstance(@Nonnull @PathVariable Long entitySchemaId, @RequestParam MultiValueMap<String, String> params) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);

        EntityInstance entityInstance = new EntityInstance();
        entityInstance.setEntitySchema(entitySchema);

        List<FieldValue> fieldValues = new ArrayList<FieldValue>();

        for (Field field : entitySchema.getFields()) {
            List<String> values = params.get(field.getFieldId().toString());

            FieldValue fieldValue = field.getValueType().newValue(values, field);
            fieldValue.setField(field);
            fieldValues.add(fieldValue);
        }

        entityInstance.setValues(fieldValues);
        entityInstanceDao.saveOrUpdate(entityInstance);

        return "redirect:/home/entity/" + entitySchemaId + "/instance/list";
    }

    @RequestMapping(value = "/{entitySchemaId}/instance/delete/{instanceId}/confirm", method = RequestMethod.GET)
    public String startDeleteField(@Nonnull @PathVariable Long entitySchemaId, ModelMap model,
                                   @Nonnull @PathVariable Long instanceId) {
        Assert.notNull(entitySchemaId);
        Assert.notNull(instanceId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        List<EntityInstance> entityInstances = entityInstanceDao.getEntityInstancesByEntitySchema(entitySchema);
        model.addAttribute("entityInstances", entityInstances);

        EntityInstance entityInstance = entityInstanceDao.getEntityInstance(instanceId);
        model.addAttribute("entityInstance", entityInstance);

        return "delete-instance";
    }

    @RequestMapping(value = "/{entitySchemaId}/instance/delete/{instanceId}", method = RequestMethod.GET)
    public String deleteEntitySchema(@Nonnull @PathVariable Long entitySchemaId, @Nonnull @PathVariable Long instanceId) {
        Assert.notNull(entitySchemaId);
        Assert.notNull(instanceId);

        EntityInstance entityInstance = entityInstanceDao.getEntityInstance(instanceId);

        entityInstanceDao.delete(entityInstance);

        return "redirect:/home/entity/" + entitySchemaId + "/instance/list";

    }
}
