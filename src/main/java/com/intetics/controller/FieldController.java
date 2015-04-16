package com.intetics.controller;

import com.intetics.bean.Choice;
import com.intetics.bean.EntitySchema;
import com.intetics.bean.Field;
import com.intetics.bean.MultiChoiceField;
import com.intetics.bean.NumberField;
import com.intetics.bean.TextAreaField;
import com.intetics.bean.TextField;
import com.intetics.bean.ValueType;
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
            
        } else if (fieldType.equalsIgnoreCase("TEXT_AREA")){
            model.addAttribute("modalTitle", "Create Text Area Field");
        } else if (fieldType.equalsIgnoreCase("NUMBER")){
            model.addAttribute("modalTitle", "Create Number Field");
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

        } else if (fieldType.equalsIgnoreCase("TEXT_AREA")){
            TextAreaField textAreaField = new TextAreaField();

            textAreaField.setCreateDate(currentDate);
            textAreaField.setModifiedDate(currentDate);
            textAreaField.setName(params.get("fieldName").get(0));
            textAreaField.setCountLine(Integer.valueOf(params.get("countLine").get(0)));

            if (params.get("active") != null) {
                textAreaField.setRequire(true);
            }

            entitySchema.getFields().add(textAreaField);
        } else if (fieldType.equalsIgnoreCase("MULTI_CHOICE")) {
            MultiChoiceField multiChoiceField = new MultiChoiceField();

            multiChoiceField.setCreateDate(currentDate);
            multiChoiceField.setModifiedDate(currentDate);
            multiChoiceField.setName(params.get("fieldName").get(0));
            multiChoiceField.setChoiceType(params.get("display").get(0));

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
        } else if (fieldType.equalsIgnoreCase("NUMBER")){
            NumberField numberField = new NumberField();

            numberField.setCreateDate(currentDate);
            numberField.setModifiedDate(currentDate);
            numberField.setName(params.get("fieldName").get(0));

            numberField.setMinValue(Integer.valueOf(params.get("minValue").get(0)));
            numberField.setMaxValue(Integer.valueOf(params.get("maxValue").get(0)));
            numberField.setNumberDecimal(Integer.valueOf(params.get("numberDecimal").get(0)));

            if (params.get("active") != null) {
                numberField.setRequire(true);
            }
            entitySchema.getFields().add(numberField);
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
            
        } else if (field.getValueType() == ValueType.TEXT_AREA) {
            model.addAttribute("modalTitle", "Edit Text Area Field");
        } else if (field.getValueType() == ValueType.NUMBER) {
            model.addAttribute("modalTitle", "Edit Number Field");
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

        field.setName(params.get("fieldName").get(0));
        if (params.get("active") != null) {
            field.setRequire(true);
        } else {
            field.setRequire(false);
        }

        field.setCreateDate((String) session.getAttribute("fieldCreateDate-"+fieldId));
        field.setModifiedDate(currentDate);

        if (field.getValueType() == ValueType.STRING) {
            TextField textField = (TextField) field;
            textField.setSize(Integer.valueOf(params.get("size").get(0)));

        } else if(field.getValueType() == ValueType.TEXT_AREA){

            TextAreaField textAreaField = (TextAreaField)field;
            textAreaField.setCountLine(Integer.valueOf(params.get("countLine").get(0)));

        } else if (field.getValueType() == ValueType.MULTI_CHOICE) {
            MultiChoiceField multiChoiceField = (MultiChoiceField) field;

            multiChoiceField.setChoiceType(params.get("display").get(0));

            multiChoiceField.getChoices().clear();
            String[] choices = params.get("choices").get(0).trim().split("\\r?\\n");
            List<Choice> choiceList = new ArrayList<Choice>();
            for (String choiceName : choices) {
                Choice choice = new Choice();
                choice.setName(choiceName);
                choiceList.add(choice);
            }

            multiChoiceField.setChoices(choiceList);

        } else if (field.getValueType() == ValueType.NUMBER){
            NumberField numberField = (NumberField) field;

            numberField.setMinValue(Integer.valueOf(params.get("minValue").get(0)));
            numberField.setMaxValue(Integer.valueOf(params.get("maxValue").get(0)));
            numberField.setNumberDecimal(Integer.valueOf(params.get("numberDecimal").get(0)));
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
