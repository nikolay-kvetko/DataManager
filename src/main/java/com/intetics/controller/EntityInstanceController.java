package com.intetics.controller;

import com.intetics.bean.*;
import com.intetics.dao.EntityInstanceDao;
import com.intetics.dao.EntitySchemaDao;
import com.intetics.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/list")
    public String getHomeEntitySchemaList(ModelMap model, Principal principal) {

        User user = userDao.getUserByEmail(principal.getName());
        List<EntitySchema> entitySchemas = user.getCompany().getEntitySchemas();
        model.addAttribute("entitySchemaList", entitySchemas);

        return "home-entity-list";

    }

    @RequestMapping(value = "/{entitySchemaId}/instance/list", method = RequestMethod.GET)
    public String getEntityInstancesList(@Nonnull @PathVariable Long entitySchemaId, Model model) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if(entitySchema == null)
            return "error";

        model.addAttribute("EntitySchema", entitySchema);

        List<EntityInstance> entityInstances = entitySchema.getEntityInstances();
        model.addAttribute("entityInstances", entityInstances);

        return "home-entity-instance-list";
    }

    @RequestMapping(value = "/{entitySchemaId}/instance/create")
    public String createEntityInstance(@Nonnull @PathVariable Long entitySchemaId, Model model) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if(entitySchema == null)
            return "error";

        model.addAttribute("EntitySchema", entitySchema);

        List<EntityInstance> entityInstances = entitySchema.getEntityInstances();
        model.addAttribute("entityInstances", entityInstances);

        model.addAttribute("modalSaveButton", "Create");

        return "create-instance";
    }

    @RequestMapping(value = "/{entitySchemaId}/instance/edit/{entityInstanceId}")
    public String editEntityInstance(@Nonnull @PathVariable Long entitySchemaId,
                                           @Nonnull @PathVariable Long entityInstanceId,
                                           Model model) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if(entitySchema == null)
            return "error";

        EntityInstance entityInstance = entityInstanceDao.getEntityInstance(entityInstanceId);

        if(!entitySchema.getEntityInstances().contains(entityInstance))
            return "error";

        model.addAttribute("EntitySchema", entitySchema);
        model.addAttribute("entityInstance", entityInstance);

        List<EntityInstance> entityInstances = entitySchema.getEntityInstances();
        model.addAttribute("entityInstances", entityInstances);

        model.addAttribute("modalSaveButton", "Edit");

        return "edit-instance";
    }

    @RequestMapping(value = "/{entitySchemaId}/instance/add", method = RequestMethod.POST)
    public String addEntityInstance(@Nonnull @PathVariable Long entitySchemaId,
                                    @RequestParam MultiValueMap<String, String> params) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if(entitySchema == null)
            return "error";

        Date currentDate = new Date();

        EntityInstance entityInstance = new EntityInstance();
        entityInstance.setEntitySchema(entitySchema);
        entityInstance.setModifiedDate(currentDate);

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

    @RequestMapping(value = "/{entitySchemaId}/instance/update/{entityInstanceId}", method = RequestMethod.POST)
    public String updateEntityInstance(@Nonnull @PathVariable Long entitySchemaId,
                                     @Nonnull @PathVariable Long entityInstanceId,
                                     @RequestParam MultiValueMap<String, String> params) {
        Assert.notNull(entitySchemaId);
        Assert.notNull(entityInstanceId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if(entitySchema == null)
            return "error";

        EntityInstance entityInstance = entityInstanceDao.getEntityInstance(entityInstanceId);

        if(!entitySchema.getEntityInstances().contains(entityInstance))
            return "error";

        Date currentDate = new Date();

        entityInstance.setEntitySchema(entitySchema);
        entityInstance.setModifiedDate(currentDate);

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
    public String startDeleteEntityInstance(@Nonnull @PathVariable Long entitySchemaId, ModelMap model,
                                   @Nonnull @PathVariable Long instanceId) {
        Assert.notNull(entitySchemaId);
        Assert.notNull(instanceId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if(entitySchema == null)
            return "error";

        EntityInstance entityInstance = entityInstanceDao.getEntityInstance(instanceId);

        if(!entitySchema.getEntityInstances().contains(entityInstance))
            return "error";

        model.addAttribute("EntitySchema", entitySchema);

        List<EntityInstance> entityInstances = entitySchema.getEntityInstances();
        model.addAttribute("entityInstances", entityInstances);

        model.addAttribute("entityInstance", entityInstance);

        return "delete-instance";
    }

    @RequestMapping(value = "/{entitySchemaId}/instance/delete/{instanceId}", method = RequestMethod.GET)
    public String deleteEntityInstance(@Nonnull @PathVariable Long entitySchemaId,
                                     @Nonnull @PathVariable Long instanceId) {
        Assert.notNull(entitySchemaId);
        Assert.notNull(instanceId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if(entitySchema == null)
            return "error";

        EntityInstance entityInstance = entityInstanceDao.getEntityInstance(instanceId);

        if(!entitySchema.getEntityInstances().contains(entityInstance))
            return "error";

        entityInstanceDao.delete(entityInstance);

        return "redirect:/home/entity/" + entitySchemaId + "/instance/list";
    }

    private EntitySchema verifyComplianceEntitySchemaAndCompany(Long entitySchemaId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userDao.getUserByEmail(authentication.getName());

        Company company = user.getCompany();

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);

        if(company.getEntitySchemas().contains(entitySchema))
            return entitySchema;

        return null;
    }
}
