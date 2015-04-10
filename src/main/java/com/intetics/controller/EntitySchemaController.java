package com.intetics.controller;

import com.intetics.bean.EntitySchema;
import com.intetics.bean.Field;
import com.intetics.bean.MultiChoiceField;
import com.intetics.bean.TextField;
import com.intetics.dao.EntitySchemaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Controller responsible for processing requests to EntitySchema-related operations
 */
@Controller
@RequestMapping("/entity")
public class EntitySchemaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntitySchemaController.class);

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @RequestMapping(value = "/list")
    public String getEntitySchemaList(ModelMap model) {

        List<EntitySchema> entitySchemas = entitySchemaDao.getEntitySchemaList();
        model.addAttribute("entitySchemaList", entitySchemas);

        EntitySchema entitySchema = new EntitySchema();
        entitySchema.setName("");
        model.addAttribute("EntitySchema", entitySchema);

        LOGGER.trace("The list of entities has been requested");

        return "entity-list";

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createEntitySchema(ModelMap model) {

        List<EntitySchema> entitySchemas = entitySchemaDao.getEntitySchemaList();
        model.addAttribute("entitySchemaList", entitySchemas);

        EntitySchema entitySchema = new EntitySchema();
        entitySchema.setName("");
        model.addAttribute("EntitySchema", entitySchema);

        model.addAttribute("modalTitle", "Create Entity");
        model.addAttribute("modalSaveButton", "Create");

        LOGGER.trace("Create new EntitySchema");

        return "create-edit-entity";

    }

    @RequestMapping(value = "/edit/{entitySchemaId}", method = RequestMethod.GET)
    public String startToEditEntitySchema(@PathVariable Long entitySchemaId, ModelMap model) {

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        List<EntitySchema> entitySchemas = entitySchemaDao.getEntitySchemaList();
        model.addAttribute("entitySchemaList", entitySchemas);

        model.addAttribute("modalTitle", "Edit Entity");
        model.addAttribute("modalSaveButton", "Edit");


        return "create-edit-entity";
    }


//    @RequestMapping(value = "/edit/{entitySchemaId}", method = RequestMethod.GET)
    public String addNewField(@PathVariable Long fieldType, @PathVariable Long entitySchemaId) {
        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        Field field = null;
        // text field
        if (fieldType == 1) {
            field = new TextField();
        } else if (fieldType == 3) {
            field = new MultiChoiceField();
        }
        entitySchema.getFields().add(field);
        return "create-edit-entity";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEditedEntitySchema(EntitySchema entitySchema) {

        entitySchemaDao.saveOrUpdate(entitySchema);
        return "redirect:/entity/list";

    }
}
