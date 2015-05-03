package com.intetics.controller;

import com.intetics.bean.*;
import com.intetics.dao.EntitySchemaDao;
import com.intetics.dao.UserDao;
import com.intetics.validation.field.FieldEmptyValidator;
import com.intetics.validation.field.multichoice.MultiChoiceValidator;
import com.intetics.validation.field.number.NumberValidator;
import com.intetics.validation.field.textarea.TextAreaValidator;
import com.intetics.validation.field.textfield.TextFieldValidator;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
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
 * Controller responsible for processing requests to Field-related operations
 */
@Controller
@RequestMapping("/entity")
public class FieldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FieldController.class);

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/{entitySchemaId}/field/list", method = RequestMethod.GET)
    public String getEntitySchemaFieldsList(@Nonnull @PathVariable Long entitySchemaId, Model model) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if (entitySchema == null)
            return "error";

        model.addAttribute("EntitySchema", entitySchema);

        return "entity-field-list";
    }

    @RequestMapping(value = "/{entitySchemaId}/field/create/{fieldType}", method = RequestMethod.GET)
    public String createFieldForEntitySchema(@Nonnull @PathVariable Long entitySchemaId, Model model,
                                             @PathVariable String fieldType, Principal principal) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if (entitySchema == null)
            return "error";

        model.addAttribute("EntitySchema", entitySchema);

        if (fieldType.equalsIgnoreCase("STRING")) {
            model.addAttribute("modalTitle", "Create Text Field");

        } else if (fieldType.equalsIgnoreCase("MULTI_CHOICE")) {
            model.addAttribute("modalTitle", "Create Choice Field");

        } else if (fieldType.equalsIgnoreCase("TEXT_AREA")) {
            model.addAttribute("modalTitle", "Create Text Area Field");

        } else if (fieldType.equalsIgnoreCase("NUMBER")) {
            model.addAttribute("modalTitle", "Create Number Field");

        } else if (fieldType.equalsIgnoreCase("DATE")) {
            model.addAttribute("modalTitle", "Create Date Field");

        } else if (fieldType.equalsIgnoreCase("IMAGE")) {
            model.addAttribute("modalTitle", "Create Image Field");

        } else if (fieldType.equalsIgnoreCase("GPS")) {
            model.addAttribute("modalTitle", "Create GPS Field");

        } else if (fieldType.equalsIgnoreCase("LOOK_UP")) {
            model.addAttribute("modalTitle", "Create Look Up Field");

            User user = userDao.getUserByEmail(principal.getName());
            List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
            model.addAttribute("listEntity", entitySchemaList);
            model.addAttribute("listField", entitySchemaDao.getEntityFieldList(entitySchemaList.get(0).getId()));
        }

        model.addAttribute("modalSaveButton", "Create");
        model.addAttribute("fieldType", fieldType);

        return "create-field";
    }

    @RequestMapping(value = "/{entitySchemaId}/field/add/{fieldType}", method = RequestMethod.POST)
    public String addFieldToEntitySchema(@Nonnull @PathVariable Long entitySchemaId, Model model,
                                         @RequestParam MultiValueMap<String, String> params,
                                         @PathVariable String fieldType, Principal principal) {
        Assert.notNull(entitySchemaId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if (entitySchema == null)
            return "error";

        Date currentDate = new Date();

        if (fieldType.equalsIgnoreCase("STRING")) {
            TextField textField = new TextField();

            textField.setModifiedDate(currentDate);
            textField.setName(params.get("fieldName").get(0));
            String digits = "-?\\d+";
            if ((params.get("size").get(0)).matches(digits)) {
                textField.setSize(Integer.valueOf(params.get("size").get(0)));
            }
            textField.setCountLookUp(0L);

            if (params.get("active") != null) {
                textField.setRequire(true);
            }

            DataBinder dataBinder = new DataBinder(textField);
            if (textField.getName().isEmpty()) {
                dataBinder.setValidator(new FieldEmptyValidator());
            } else {
                dataBinder.setValidator(new TextFieldValidator());
            }
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {

                model.addAttribute("EntitySchema", entitySchema);
                model.addAttribute("modalTitle", "Create Text Field");
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("listField", entitySchemaDao.getEntityFieldList(entitySchemaList.get(0).getId()));
                model.addAttribute("modalSaveButton", "Create");
                model.addAttribute("fieldType", fieldType);
                return "create-field";
            }

            entitySchema.getFields().add(textField);

        } else if (fieldType.equalsIgnoreCase("TEXT_AREA")) {
            TextAreaField textAreaField = new TextAreaField();

            textAreaField.setModifiedDate(currentDate);
            textAreaField.setName(params.get("fieldName").get(0));
            String digits = "-?\\d+";
            if (params.get("countLine").get(0).matches(digits)) {
                textAreaField.setCountLine(Integer.valueOf(params.get("countLine").get(0)));
            }
            textAreaField.setCountLookUp(0L);

            if (params.get("active") != null) {
                textAreaField.setRequire(true);
            }

            DataBinder dataBinder = new DataBinder(textAreaField);
            if (textAreaField.getName().isEmpty()) {
                dataBinder.setValidator(new FieldEmptyValidator());
            } else {
                dataBinder.setValidator(new TextAreaValidator());
            }
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {

                model.addAttribute("EntitySchema", entitySchema);
                model.addAttribute("modalTitle", "Create Text Field");
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("listField", entitySchemaDao.getEntityFieldList(entitySchemaList.get(0).getId()));
                model.addAttribute("modalSaveButton", "Create");
                model.addAttribute("fieldType", fieldType);
                return "create-field";
            }

            entitySchema.getFields().add(textAreaField);

        } else if (fieldType.equalsIgnoreCase("MULTI_CHOICE")) {
            MultiChoiceField multiChoiceField = new MultiChoiceField();

            multiChoiceField.setModifiedDate(currentDate);
            multiChoiceField.setName(params.get("fieldName").get(0));
            multiChoiceField.setChoiceType(params.get("display").get(0));
            multiChoiceField.setCountLookUp(0L);

            String[] choices = params.get("choices").get(0).trim().split("\\r?\\n");
            if (!choices[0].isEmpty()) {
                List<Choice> choiceList = new ArrayList<Choice>();
                for (String choiceName : choices) {
                    Choice choice = new Choice();
                    choice.setName(choiceName);
                    choiceList.add(choice);
                }


                multiChoiceField.setChoices(choiceList);
            }

            if (params.get("active") != null) {
                multiChoiceField.setRequire(true);
            }

            DataBinder dataBinder = new DataBinder(multiChoiceField);
            if (multiChoiceField.getName().isEmpty()) {
                dataBinder.setValidator(new FieldEmptyValidator());
            } else {
                dataBinder.setValidator(new MultiChoiceValidator());
            }
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {

                model.addAttribute("EntitySchema", entitySchema);
                model.addAttribute("modalTitle", "Create Choice Field");
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("listField", entitySchemaDao.getEntityFieldList(entitySchemaList.get(0).getId()));
                model.addAttribute("modalSaveButton", "Create");
                model.addAttribute("fieldType", fieldType);
                return "create-field";
            }

            entitySchema.getFields().add(multiChoiceField);

        } else if (fieldType.equalsIgnoreCase("NUMBER")) {
            NumberField numberField = new NumberField();
            String digits = "-?\\d+";
            numberField.setModifiedDate(currentDate);
            numberField.setName(params.get("fieldName").get(0));
            numberField.setCountLookUp(0L);
            if (params.get("minValue").get(0).matches(digits)
                    && params.get("maxValue").get(0).matches(digits)
                    && params.get("numberDecimal").get(0).matches(digits)) {
                numberField.setMinValue(Integer.valueOf(params.get("minValue").get(0)));
                numberField.setMaxValue(Integer.valueOf(params.get("maxValue").get(0)));
                numberField.setNumberDecimal(Integer.valueOf(params.get("numberDecimal").get(0)));
            }

            if (params.get("active") != null) {
                numberField.setRequire(true);
            }

            DataBinder dataBinder = new DataBinder(numberField);
            if (numberField.getName().isEmpty()) {
                dataBinder.setValidator(new FieldEmptyValidator());
            } else {
                dataBinder.setValidator(new NumberValidator());
            }
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {

                model.addAttribute("EntitySchema", entitySchema);
                model.addAttribute("modalTitle", "Create Number Field");
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("listField", entitySchemaDao.getEntityFieldList(entitySchemaList.get(0).getId()));
                model.addAttribute("modalSaveButton", "Create");
                model.addAttribute("fieldType", fieldType);
                return "create-field";
            }

            entitySchema.getFields().add(numberField);

        } else if (fieldType.equalsIgnoreCase("DATE")) {
            DateField dateField = new DateField();

            dateField.setModifiedDate(currentDate);
            dateField.setName(params.get("fieldName").get(0));
            dateField.setCountLookUp(0L);

            dateField.setFullDate(Boolean.valueOf(params.get("format").get(0)));

            if (params.get("active") != null) {
                dateField.setRequire(true);
            }

            DataBinder dataBinder = new DataBinder(dateField);
            dataBinder.setValidator(new FieldEmptyValidator());
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {

                model.addAttribute("EntitySchema", entitySchema);
                model.addAttribute("modalTitle", "Create Date Field");
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("listField", entitySchemaDao.getEntityFieldList(entitySchemaList.get(0).getId()));
                model.addAttribute("modalSaveButton", "Create");
                model.addAttribute("fieldType", fieldType);
                return "create-field";
            }

            entitySchema.getFields().add(dateField);

        } else if (fieldType.equalsIgnoreCase("IMAGE")) {
            ImageField imageField = new ImageField();

            imageField.setModifiedDate(currentDate);
            imageField.setName(params.get("fieldName").get(0));
            imageField.setCountLookUp(0L);

            if (params.get("active") != null) {
                imageField.setRequire(true);
            }

            DataBinder dataBinder = new DataBinder(imageField);
            dataBinder.setValidator(new FieldEmptyValidator());
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {

                model.addAttribute("EntitySchema", entitySchema);
                model.addAttribute("modalTitle", "Create Image Field");
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("listField", entitySchemaDao.getEntityFieldList(entitySchemaList.get(0).getId()));
                model.addAttribute("modalSaveButton", "Create");
                model.addAttribute("fieldType", fieldType);
                return "create-field";
            }

            entitySchema.getFields().add(imageField);

        } else if (fieldType.equalsIgnoreCase("GPS")) {
            GPSField gpsField = new GPSField();

            gpsField.setCreateDate(currentDate);
            gpsField.setModifiedDate(currentDate);
            gpsField.setName(params.get("fieldName").get(0));
            gpsField.setCountLookUp(0L);

            if (params.get("active") != null) {
                gpsField.setRequire(true);
            }

            DataBinder dataBinder = new DataBinder(gpsField);
            dataBinder.setValidator(new FieldEmptyValidator());
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {

                model.addAttribute("EntitySchema", entitySchema);
                model.addAttribute("modalTitle", "Create GPS Field");
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("listField", entitySchemaDao.getEntityFieldList(entitySchemaList.get(0).getId()));
                model.addAttribute("modalSaveButton", "Create");
                model.addAttribute("fieldType", fieldType);
                return "create-field";
            }

            entitySchema.getFields().add(gpsField);

        } else if (fieldType.equalsIgnoreCase("LOOK_UP")) {
            LookUpField lookUpField = new LookUpField();

            lookUpField.setModifiedDate(currentDate);
            lookUpField.setName(params.get("fieldName").get(0));
            lookUpField.setCountLookUp(0L);

            lookUpField.setLookUpEntityId(Long.valueOf(Integer.valueOf(params.get("selectEntity").get(0))));
            lookUpField.setLookUpFieldId(Long.valueOf(Integer.valueOf(params.get("selectField").get(0))));

            EntitySchema tmpEntitySchema = entitySchemaDao.getEntitySchema(lookUpField.getLookUpEntityId());
            for (Field tmpField : tmpEntitySchema.getFields()) {
                if (tmpField.getFieldId() == lookUpField.getLookUpFieldId()) {
                    tmpField.setCountLookUp(tmpField.getCountLookUp() + 1);
                }
            }
            entitySchemaDao.saveOrUpdate(tmpEntitySchema);

            if (params.get("active") != null) {
                lookUpField.setRequire(true);
            }

            DataBinder dataBinder = new DataBinder(lookUpField);
            dataBinder.setValidator(new FieldEmptyValidator());
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {

                model.addAttribute("EntitySchema", entitySchema);
                model.addAttribute("modalTitle", "Create Look Up Field");
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("listField", entitySchemaDao.getEntityFieldList(entitySchemaList.get(0).getId()));
                model.addAttribute("modalSaveButton", "Create");
                model.addAttribute("fieldType", fieldType);
                return "create-field";
            }

            entitySchema.getFields().add(lookUpField);
        }

        entitySchema.setModifiedDate(currentDate);
        entitySchemaDao.saveOrUpdate(entitySchema);

        model.addAttribute("EntitySchema", entitySchema);

        return "redirect:/entity/" + entitySchemaId + "/field/list";
    }

    @RequestMapping(value = "{entitySchemaId}/field/edit/{fieldId}", method = RequestMethod.GET)
    public String editFieldInEntitySchema(@Nonnull @PathVariable Long entitySchemaId, Model model,
                                          @Nonnull @PathVariable Long fieldId, Principal principal) {
        Assert.notNull(entitySchemaId);
        Assert.notNull(fieldId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if (entitySchema == null)
            return "error";

        Field field = entitySchemaDao.getField(fieldId);

        if (!entitySchema.getFields().contains(field))
            return "error";

        model.addAttribute("EntitySchema", entitySchema);

        if (field.getValueType() == ValueType.STRING) {
            model.addAttribute("modalTitle", "Edit Text Field");

        } else if (field.getValueType() == ValueType.MULTI_CHOICE) {
            model.addAttribute("modalTitle", "Edit Choice Field");

        } else if (field.getValueType() == ValueType.TEXT_AREA) {
            model.addAttribute("modalTitle", "Edit Text Area Field");

        } else if (field.getValueType() == ValueType.NUMBER) {
            model.addAttribute("modalTitle", "Edit Number Field");

        } else if (field.getValueType() == ValueType.DATE) {
            model.addAttribute("modalTitle", "Edit Date Field");

        } else if (field.getValueType() == ValueType.IMAGE) {
            model.addAttribute("modalTitle", "Edit Image Field");

        } else if (field.getValueType() == ValueType.GPS) {
            model.addAttribute("modalTitle", "Edit GPS Field");

        } else if (field.getValueType() == ValueType.LOOK_UP) {
            model.addAttribute("modalTitle", "Edit Look Up Field");

            User user = userDao.getUserByEmail(principal.getName());
            List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
            model.addAttribute("listEntity", entitySchemaList);

            LookUpField lookUpField = (LookUpField) field;

            List<Field> entityFieldList = entitySchemaDao.getEntityFieldList(lookUpField.getLookUpEntityId());
            Field tmpField = null;
            for (Field fieldItem : entityFieldList) {
                if (fieldItem.getFieldId() == fieldId) {
                    tmpField = fieldItem;
                }
            }
            entityFieldList.remove(tmpField);
            model.addAttribute("listField", entityFieldList);
        }

        model.addAttribute("modalSaveButton", "Edit");
        model.addAttribute("fieldType", field.getValueType().name().toLowerCase());
        model.addAttribute("field", field);

        return "create-field";
    }

    @RequestMapping(value = "/{entitySchemaId}/field/change/{fieldId}", method = RequestMethod.POST)
    public String changeFieldInEntitySchema(@Nonnull @PathVariable Long entitySchemaId, Model model,
                                            @RequestParam MultiValueMap<String, String> params,
                                            @Nonnull @PathVariable Long fieldId, Principal principal) {
        Assert.notNull(entitySchemaId);
        Assert.notNull(fieldId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if (entitySchema == null)
            return "error";

        Field field = null;
        for (Field tmpField : entitySchema.getFields()) {
            if (tmpField.getFieldId() == fieldId) {
                field = tmpField;
            }
        }

        if (!entitySchema.getFields().contains(field))
            return "error";

        Date currentDate = new Date();

        field.setName(params.get("fieldName").get(0));
        if (params.get("active") != null) {
            field.setRequire(true);
        } else {
            field.setRequire(false);
        }

        field.setModifiedDate(currentDate);

        if (field.getValueType() == ValueType.STRING) {
            TextField textField = (TextField) field;
            String digits = "-?\\d+";
            if ((params.get("size").get(0)).matches(digits)) {
                textField.setSize(Integer.valueOf(params.get("size").get(0)));
            }

            DataBinder dataBinder = new DataBinder(textField);
            if (textField.getName().isEmpty()) {
                dataBinder.setValidator(new FieldEmptyValidator());
            } else {
                dataBinder.setValidator(new TextFieldValidator());
            }
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {
                model.addAttribute("modalTitle", "Edit Text Field");
                model.addAttribute("EntitySchema", entitySchema);
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("modalSaveButton", "Edit");
                model.addAttribute("fieldType", field.getValueType().name().toLowerCase());
                model.addAttribute("field", field);
                return "create-field";
            }

        } else if (field.getValueType() == ValueType.TEXT_AREA) {

            TextAreaField textAreaField = (TextAreaField) field;
            String digits = "-?\\d+";
            if (params.get("countLine").get(0).matches(digits)) {
                textAreaField.setCountLine(Integer.valueOf(params.get("countLine").get(0)));
            }
            DataBinder dataBinder = new DataBinder(textAreaField);
            if (textAreaField.getName().isEmpty()) {
                dataBinder.setValidator(new FieldEmptyValidator());
            } else {
                dataBinder.setValidator(new TextAreaValidator());
            }
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {
                model.addAttribute("modalTitle", "Edit Text Area Field");
                model.addAttribute("EntitySchema", entitySchema);
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("modalSaveButton", "Edit");
                model.addAttribute("fieldType", field.getValueType().name().toLowerCase());
                model.addAttribute("field", field);
                return "create-field";
            }

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

            DataBinder dataBinder = new DataBinder(multiChoiceField);
            if (multiChoiceField.getName().isEmpty()) {
                dataBinder.setValidator(new FieldEmptyValidator());
            } else {
                dataBinder.setValidator(new MultiChoiceValidator());
            }
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {
                model.addAttribute("modalTitle", "Edit Choice Field");
                model.addAttribute("EntitySchema", entitySchema);
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("modalSaveButton", "Edit");
                model.addAttribute("fieldType", field.getValueType().name().toLowerCase());
                model.addAttribute("field", field);
                return "create-field";
            }

        } else if (field.getValueType() == ValueType.NUMBER) {
            NumberField numberField = (NumberField) field;
            String digits = "-?\\d+";
            if (params.get("minValue").get(0).matches(digits)
                    && params.get("maxValue").get(0).matches(digits)
                    && params.get("numberDecimal").get(0).matches(digits)) {
                numberField.setMinValue(Integer.valueOf(params.get("minValue").get(0)));
                numberField.setMaxValue(Integer.valueOf(params.get("maxValue").get(0)));
                numberField.setNumberDecimal(Integer.valueOf(params.get("numberDecimal").get(0)));
            }

            DataBinder dataBinder = new DataBinder(numberField);
            if (numberField.getName().isEmpty()) {
                dataBinder.setValidator(new FieldEmptyValidator());
            } else {
                dataBinder.setValidator(new NumberValidator());
            }
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {
                model.addAttribute("modalTitle", "Edit Number Field");
                model.addAttribute("EntitySchema", entitySchema);
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("modalSaveButton", "Edit");
                model.addAttribute("fieldType", field.getValueType().name().toLowerCase());
                model.addAttribute("field", field);
                return "create-field";
            }

        } else if (field.getValueType() == ValueType.DATE) {
            DateField dateField = (DateField) field;

            dateField.setFullDate(Boolean.valueOf(params.get("format").get(0)));

            DataBinder dataBinder = new DataBinder(dateField);
            dataBinder.setValidator(new FieldEmptyValidator());
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {
                model.addAttribute("modalTitle", "Edit Date Field");
                model.addAttribute("EntitySchema", entitySchema);
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("modalSaveButton", "Edit");
                model.addAttribute("fieldType", field.getValueType().name().toLowerCase());
                model.addAttribute("field", field);
                return "create-field";
            }

        } else if (field.getValueType() == ValueType.LOOK_UP) {
            LookUpField lookUpField = (LookUpField) field;

            EntitySchema tmpEntitySchema = entitySchemaDao.getEntitySchema(lookUpField.getLookUpEntityId());
            for (Field tmpField : tmpEntitySchema.getFields()) {
                if (tmpField.getFieldId() == lookUpField.getLookUpFieldId()) {
                    tmpField.setCountLookUp(tmpField.getCountLookUp() - 1);
                }
            }
            entitySchemaDao.saveOrUpdate(tmpEntitySchema);

            lookUpField.setLookUpEntityId(Long.valueOf(Integer.valueOf(params.get("selectEntity").get(0))));
            lookUpField.setLookUpFieldId(Long.valueOf(Integer.valueOf(params.get("selectField").get(0))));

            tmpEntitySchema = entitySchemaDao.getEntitySchema(lookUpField.getLookUpEntityId());
            for (Field tmpField : tmpEntitySchema.getFields()) {
                if (tmpField.getFieldId() == lookUpField.getLookUpFieldId()) {
                    tmpField.setCountLookUp(tmpField.getCountLookUp() + 1);
                }
            }

            DataBinder dataBinder = new DataBinder(lookUpField);
            dataBinder.setValidator(new FieldEmptyValidator());
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {
                model.addAttribute("modalTitle", "Edit Look Up Field");
                model.addAttribute("EntitySchema", entitySchema);
                User user = userDao.getUserByEmail(principal.getName());
                List<EntitySchema> entitySchemaList = user.getCompany().getEntitySchemas();
                model.addAttribute("listEntity", entitySchemaList);
                model.addAttribute("modalSaveButton", "Edit");
                model.addAttribute("fieldType", field.getValueType().name().toLowerCase());
                model.addAttribute("field", field);
                return "create-field";
            }

            entitySchemaDao.saveOrUpdate(tmpEntitySchema);
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

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if (entitySchema == null)
            return "error";

        Field field = entitySchemaDao.getField(fieldId);

        if (!entitySchema.getFields().contains(field))
            return "error";

        model.addAttribute("EntitySchema", entitySchema);
        model.addAttribute("field", field);

        return "delete-field";
    }

    @RequestMapping(value = "/{entitySchemaId}/field/delete/{fieldId}", method = RequestMethod.GET)
    public String deleteField(@Nonnull @PathVariable Long entitySchemaId, @Nonnull @PathVariable Long fieldId) {
        Assert.notNull(entitySchemaId);
        Assert.notNull(fieldId);

        EntitySchema entitySchema = verifyComplianceEntitySchemaAndCompany(entitySchemaId);
        if (entitySchema == null)
            return "error";

        Field field = entitySchemaDao.getField(fieldId);

        if (!entitySchema.getFields().contains(field))
            return "error";

        if (field.getValueType() == ValueType.LOOK_UP) {
            LookUpField lookUpField = (LookUpField) field;
            EntitySchema tmpEntitySchema = entitySchemaDao.getEntitySchema(lookUpField.getLookUpEntityId());
            for (Field tmpField : tmpEntitySchema.getFields()) {
                if (tmpField.getFieldId() == lookUpField.getLookUpFieldId()) {
                    tmpField.setCountLookUp(tmpField.getCountLookUp() - 1);
                }
            }
            entitySchemaDao.saveOrUpdate(tmpEntitySchema);
        }

        entitySchema.getFields().remove(field);

        Date currentDate = new Date();

        entitySchema.setModifiedDate(currentDate);
        entitySchemaDao.saveOrUpdate(entitySchema);

        return "redirect:/entity/" + entitySchemaId + "/field/list";
    }

    private EntitySchema verifyComplianceEntitySchemaAndCompany(Long entitySchemaId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userDao.getUserByEmail(authentication.getName());

        Company company = user.getCompany();

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);

        if (company.getEntitySchemas().contains(entitySchema))
            return entitySchema;

        return null;
    }
}
