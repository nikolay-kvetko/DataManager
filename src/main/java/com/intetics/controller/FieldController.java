package com.intetics.controller;

import com.intetics.bean.EntitySchema;
import com.intetics.bean.TextField;
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
import javax.servlet.http.HttpServletRequest;

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
    public String getEntitySchemaFieldsList(@Nonnull @PathVariable Long entitySchemaId, Model model) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        return "entity-field-list";
    }

    @RequestMapping(value = "/{entitySchemaId}/field/create", method = RequestMethod.GET)
    public String createFieldForEntitySchema(@Nonnull @PathVariable Long entitySchemaId, Model model) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);
        model.addAttribute("entitySchemaId", entitySchemaId);

        TextField textField = new TextField();
        textField.setName("");
        model.addAttribute("textField", textField);

        model.addAttribute("modalTitle", "Create Text Field");
        model.addAttribute("modalSaveButton", "Create");

        return "create-field";
    }

    @RequestMapping(value = "/{entitySchemaId}/field/add", method = RequestMethod.POST)
    public String addFieldToEntitySchema(@Nonnull @PathVariable Long entitySchemaId, Model model,
                                         TextField textField, HttpServletRequest request) {
        Assert.notNull(entitySchemaId);

        if(request.getParameter("active") != null) {
            textField.setRequire(true);
        }

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        entitySchema.getFields().add(textField);
        entitySchemaDao.saveOrUpdate(entitySchema);

        model.addAttribute("EntitySchema", entitySchema);

        return "redirect:/entity/"+entitySchemaId+"/field/list";
    }
}
