package com.intetics.controller;

import com.intetics.bean.EntitySchema;
import com.intetics.dao.EntitySchemaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Nonnull;

/**
 * Controller responsible for processing requests to EntitySchema-related operations
 */
@Controller
@RequestMapping("/entity")
public class FieldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FieldController.class);

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @RequestMapping(value = "/{entitySchemaId}/field/list", method = RequestMethod.GET)
    public String addEntityInstance(@Nonnull @PathVariable Long entitySchemaId, Model model) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        return "entity-field-list";
    }
}
