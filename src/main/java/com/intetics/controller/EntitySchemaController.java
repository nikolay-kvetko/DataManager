package com.intetics.controller;

import com.intetics.bean.EntitySchema;
import com.intetics.dao.EntitySchemaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getEntitySchemaList(ModelMap model) {

        List<EntitySchema> entitySchemas = entitySchemaDao.getEntitySchemaList();
        model.addAttribute("entitySchemasList", entitySchemas);
        LOGGER.trace("The list of entities has been requested");
        return "entity-list";

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {

        return "";

    }
}
