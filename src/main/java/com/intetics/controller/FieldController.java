package com.intetics.controller;

import com.intetics.bean.*;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller responsible for processing requests to EntitySchema-related operations
 */
@Controller
@RequestMapping("/entity")
public class FieldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FieldController.class);
    private String dateFormat = "HH:mm:ss dd-MM-yyyy";

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
    public String createFieldForEntitySchema(@Nonnull @PathVariable Long entitySchemaId, Model model,
                                             @PathVariable String fieldType) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        if (fieldType.equalsIgnoreCase("STRING")) {
            model.addAttribute("modalTitle", "Create Text Field");

        } else if (fieldType.equalsIgnoreCase("MULTI_CHOICE")) {
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

        String currentDate = new SimpleDateFormat(dateFormat).format(new Date());

        if (fieldType.equalsIgnoreCase("STRING")) {
            TextField textField = new TextField();

            textField.setCreateDate(currentDate);
            textField.setModifiedDate(currentDate);
            textField.setName(params.get("fieldName").get(0));
            textField.setSize(Integer.valueOf(params.get("size").get(0)));

            if (params.get("active") != null) {
                textField.setRequire(true);
            }

            entitySchema.getFields().add(textField);

        } else if (fieldType.equalsIgnoreCase("MULTI_CHOICE")) {
            MultiChoiceField multiChoiceField = new MultiChoiceField();

            multiChoiceField.setCreateDate(currentDate);
            multiChoiceField.setModifiedDate(currentDate);
            multiChoiceField.setName(params.get("fieldName").get(0));

            String[] choices = params.get("choices").get(0).trim().split("\\r?\\n");
            List<Choice> choiceList = new ArrayList<Choice>();
            for (String choiceName : choices) {
                Choice choice = new Choice();
                choice.setName(choiceName);
                choiceList.add(choice);
            }

            multiChoiceField.setChoices(choiceList);

            if (params.get("active") != null) {
                multiChoiceField.setRequire(true);
            }

            entitySchema.getFields().add(multiChoiceField);
        }

        entitySchema.setModifiedDate(currentDate);
        entitySchemaDao.saveOrUpdate(entitySchema);

        model.addAttribute("EntitySchema", entitySchema);

        return "redirect:/entity/" + entitySchemaId + "/field/list";
    }

    @RequestMapping(value = "{entitySchemaId}/field/edit/{fieldId}", method = RequestMethod.GET)
    public String editFieldInEntitySchema(@Nonnull @PathVariable Long entitySchemaId, Model model,
                                          @Nonnull @PathVariable Long fieldId, HttpServletRequest request) {
        Assert.notNull(entitySchemaId);
        Assert.notNull(fieldId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        Field field = entitySchemaDao.getField(fieldId);

        if (field.getValueType() == ValueType.STRING) {
            model.addAttribute("modalTitle", "Edit Text Field");

        } else if (field.getValueType() == ValueType.MULTI_CHOICE) {
            model.addAttribute("modalTitle", "Edit Choice Field");
        }

        HttpSession session = request.getSession();
        session.setAttribute("fieldCreateDate-"+fieldId, field.getCreateDate());

        model.addAttribute("modalSaveButton", "Edit");
        model.addAttribute("fieldType", field.getValueType().name().toLowerCase());
        model.addAttribute("field", field);

        return "create-field";
    }

    @RequestMapping(value = "/{entitySchemaId}/field/change/{fieldId}", method = RequestMethod.POST)
    public String changeFieldInEntitySchema(@Nonnull @PathVariable Long entitySchemaId, Model model,
                                         @RequestParam MultiValueMap<String, String> params,
                                         @Nonnull @PathVariable Long fieldId, HttpServletRequest request) {
        Assert.notNull(entitySchemaId);
        Assert.notNull(fieldId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);

        String currentDate = new SimpleDateFormat(dateFormat).format(new Date());

        HttpSession session = request.getSession();

        Field field = null;

        for (Field tmpField : entitySchema.getFields()){
            if (tmpField.getFieldId() == fieldId){
                field = tmpField;
            }
        }

        field.setCreateDate((String) session.getAttribute("fieldCreateDate-"+fieldId));
        field.setModifiedDate(currentDate);

        if (field.getValueType() == ValueType.STRING) {
            TextField textField = (TextField) field;

            textField.setName(params.get("fieldName").get(0));
            textField.setSize(Integer.valueOf(params.get("size").get(0)));

            if (params.get("active") != null) {
                textField.setRequire(true);
            } else {
                textField.setRequire(false);
            }

        } else if (field.getValueType() == ValueType.MULTI_CHOICE) {
            MultiChoiceField multiChoiceField = (MultiChoiceField) field;

            multiChoiceField.setName(params.get("fieldName").get(0));
            multiChoiceField.getChoices().clear();

            String[] choices = params.get("choices").get(0).trim().split("\\r?\\n");
            List<Choice> choiceList = new ArrayList<Choice>();
            for (String choiceName : choices) {
                Choice choice = new Choice();
                choice.setName(choiceName);
                choiceList.add(choice);
            }

            multiChoiceField.setChoices(choiceList);

            if (params.get("active") != null) {
                multiChoiceField.setRequire(true);
            } else {
                multiChoiceField.setRequire(false);
            }
        }

        entitySchema.setModifiedDate(currentDate);
        entitySchemaDao.saveOrUpdate(entitySchema);

        model.addAttribute("EntitySchema", entitySchema);

        return "redirect:/entity/" + entitySchemaId + "/field/list";
    }

    @RequestMapping(value = "/{entitySchemaId}/field/delete/{fieldId}/confirm", method = RequestMethod.GET)
    public String startDeleteField(@Nonnull @PathVariable Long entitySchemaId, ModelMap model,
                                   @Nonnull @PathVariable Long fieldId) {
        Assert.notNull(entitySchemaId);
        Assert.notNull(fieldId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);
        model.addAttribute("EntitySchema", entitySchema);

        Field field = entitySchemaDao.getField(fieldId);
        model.addAttribute("field", field);

        return "delete-field";
    }

    @RequestMapping(value = "/{entitySchemaId}/field/delete/{fieldId}", method = RequestMethod.GET)
    public String deleteEntitySchema(@Nonnull @PathVariable Long entitySchemaId, @Nonnull @PathVariable Long fieldId) {
        Assert.notNull(entitySchemaId);
        Assert.notNull(fieldId);

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);

        String currentDate = new SimpleDateFormat(dateFormat).format(new Date());

        Field field = entitySchemaDao.getField(fieldId);
        entitySchema.getFields().remove(field);

        entitySchema.setModifiedDate(currentDate);
        entitySchemaDao.saveOrUpdate(entitySchema);

        return "redirect:/entity/" + entitySchemaId + "/field/list";
    }
}
