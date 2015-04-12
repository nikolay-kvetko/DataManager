package com.intetics.controller;

import com.intetics.bean.Choice;
import com.intetics.bean.EntitySchema;
import com.intetics.bean.MultiChoiceField;
import com.intetics.bean.TextField;
import com.intetics.dao.EntitySchemaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/{entitySchemaId}/field/create/{fieldType}", method = RequestMethod.GET)
    public String createFieldForEntitySchema(@Nonnull @PathVariable Long entitySchemaId, Model model, @PathVariable String fieldType) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        if(fieldType.equalsIgnoreCase("STRING")) {
            model.addAttribute("modalTitle", "Create Text Field");

        } else if(fieldType.equalsIgnoreCase("MULTI_CHOICE")) {
            model.addAttribute("modalTitle", "Create Choice Field");
        }

        model.addAttribute("modalSaveButton", "Create");
        model.addAttribute("fieldType", fieldType);

        return "create-field";
    }

    @RequestMapping(value = "/{entitySchemaId}/field/add/{fieldType}", method = RequestMethod.POST)
    public String addFieldToEntitySchema(@Nonnull @PathVariable Long entitySchemaId, Model model,
                                         @RequestParam MultiValueMap<String, String> params,
                                         @PathVariable String fieldType) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);

        if(fieldType.equalsIgnoreCase("STRING")) {
            TextField textField = new TextField();
            textField.setName(params.get("fieldName").get(0));
            textField.setSize(Integer.valueOf(params.get("size").get(0)));

            if(params.get("active") != null) {
                textField.setRequire(true);
            }

            entitySchema.getFields().add(textField);

        } else if(fieldType.equalsIgnoreCase("MULTI_CHOICE")) {
            MultiChoiceField multiChoiceField = new MultiChoiceField();
            multiChoiceField.setName(params.get("fieldName").get(0));

            String[] choices =  params.get("choices").get(0).trim().split("\\r?\\n");
            List<Choice> choiceList = new ArrayList<Choice>();
            for (String choiceName : choices) {
                Choice choice = new Choice();
                choice.setName(choiceName);
                choiceList.add(choice);
            }

            multiChoiceField.setChoices(choiceList);

            if(params.get("active") != null) {
                multiChoiceField.setRequire(true);
            }

            entitySchema.getFields().add(multiChoiceField);
        }

        entitySchemaDao.saveOrUpdate(entitySchema);

        model.addAttribute("EntitySchema", entitySchema);

        return "redirect:/entity/"+entitySchemaId+"/field/list";
    }
}
