package com.intetics.controller;

import com.intetics.bean.EntitySchema;
import com.intetics.dao.EntitySchemaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller responsible for processing requests to EntitySchema-related operations
 */
@Controller
@RequestMapping("/entity")
public class EntitySchemaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntitySchemaController.class);
    private String dateFormat = "HH:mm:ss dd-MM-yyyy";

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @RequestMapping(value = "/list")
    public String getEntitySchemaList(ModelMap model) {

        List<EntitySchema> entitySchemas = entitySchemaDao.getEntitySchemaList();
        model.addAttribute("entitySchemaList", entitySchemas);

        LOGGER.trace("The list of entities has been requested");

        return "entity-list";
    }

    @RequestMapping(value = "/create")
    public String createEntitySchema(ModelMap model) {

        List<EntitySchema> entitySchemas = entitySchemaDao.getEntitySchemaList();
        model.addAttribute("entitySchemaList", entitySchemas);

        EntitySchema entitySchema = new EntitySchema();
        entitySchema.setName("");
        model.addAttribute("EntitySchema", entitySchema);

        model.addAttribute("modalTitle", "Create Entity");
        model.addAttribute("modalSaveButton", "Create");

        LOGGER.trace("Create new EntitySchema");

        return "create-entity";
    }

    @RequestMapping(value = "/edit/{entitySchemaId}", method = RequestMethod.GET)
    public String startToEditEntitySchema(@Nonnull @PathVariable Long entitySchemaId, ModelMap model) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        model.addAttribute("modalTitle", "Edit Entity");
        model.addAttribute("modalSaveButton", "Edit");

        return "edit-entity";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEntitySchema(EntitySchema entitySchema) {

        if(entitySchema.getId() != null){
            entitySchema.setModifiedDate(new SimpleDateFormat(dateFormat).format(new Date()));
            entitySchemaDao.saveOrUpdate(entitySchema);
            return "redirect:/entity/"+entitySchema.getId()+"/field/list";
        }

        entitySchema.setCreateDate(new SimpleDateFormat(dateFormat).format(new Date()));
        entitySchema.setModifiedDate(new SimpleDateFormat(dateFormat).format(new Date()));

        entitySchemaDao.saveOrUpdate(entitySchema);
        return "redirect:/entity/list";
    }

    @RequestMapping(value = "/delete/{entitySchemaId}/confirm", method = RequestMethod.GET)
    public String startDeleteEntitySchema(@Nonnull @PathVariable Long entitySchemaId, ModelMap model) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        List<EntitySchema> entitySchemas = entitySchemaDao.getEntitySchemaList();
        model.addAttribute("entitySchemaList", entitySchemas);

        return "delete-entity";
    }

    @RequestMapping(value = "/delete/{entitySchemaId}", method = RequestMethod.GET)
    public String deleteEntitySchema(@Nonnull @PathVariable Long entitySchemaId) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        entitySchemaDao.delete(entitySchema);

        return "redirect:/entity/list";
    }
}
