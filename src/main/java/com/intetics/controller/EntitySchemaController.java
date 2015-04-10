package com.intetics.controller;

import com.intetics.bean.EntitySchema;
import com.intetics.dao.EntitySchemaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getEntitySchemaList(ModelMap model) {

        List<EntitySchema> entitySchemas = entitySchemaDao.getEntitySchemaList();
        model.addAttribute("entitySchemaList", entitySchemas);

        EntitySchema entitySchema = new EntitySchema();
        entitySchema.setName("");
        model.addAttribute("EntitySchema", entitySchema);

        LOGGER.trace("The list of entities has been requested");

        return "entity-list";

    }

    @RequestMapping(value = "/saveNewEntitySchema", method = RequestMethod.POST)
    public String createEntitySchema(HttpServletRequest request) {

        String name = request.getParameter("name");
        EntitySchema entitySchema = new EntitySchema();
        entitySchema.setName(name);
        entitySchemaDao.saveOrUpdate(entitySchema);
        return "redirect:/entity/list";

    }

    @RequestMapping(value = "/edit/{entitySchemaId}", method = RequestMethod.GET)
    public String startToEditEntitySchema(@PathVariable Long entitySchemaId, ModelMap model) {

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchemaEdit", entitySchema);

        List<EntitySchema> entitySchemas = entitySchemaDao.getEntitySchemaList();
        model.addAttribute("entitySchemaList", entitySchemas);

        return "edit-entity";
    }

    @RequestMapping(value = "/saveEditedEntitySchema", method = RequestMethod.POST)
    public String saveEditedEntitySchema(EntitySchema entitySchema) {

        entitySchemaDao.saveOrUpdate(entitySchema);
        return "redirect:/entity/list";

    }
}
