package com.intetics.controller;

import com.intetics.bean.Field;
import com.intetics.bean.LookUpField;
import com.intetics.dao.EntitySchemaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @RequestMapping(value = "/getNewFieldList", method = RequestMethod.POST)
    public
    @ResponseBody
    String getFieldListByEntitySchema(@RequestParam Long entityId,
                                      @RequestParam Long currentEntityId) {

        String result = "<select name=\"selectField\" class=\"form-control\" id=\"selectField\">";

        List<Field> fieldList = entitySchemaDao.getEntityFieldList(entityId);

        for (Field field : fieldList) {
            if (field.getFieldId() != currentEntityId) {
                result += "<option value=" + field.getFieldId() + ">" + field.getName() + "</option>";
            }
        }

        result += "</select>";

        return result;
    }

    @RequestMapping(value = "/getImageByUrl", method = RequestMethod.POST)
    public
    @ResponseBody
    String getImageByUrl(@RequestParam String url) {

        URL tmpUrl;

        try {
            tmpUrl = new URL(url);
        } catch (MalformedURLException e) {
            return null;
        }

        String result = "<img src=\" " + tmpUrl + "\" style=\"max-width: 150px; max-height: 150px;\">";

        return result;
    }

    @RequestMapping(value = "/showLookUpField", method = RequestMethod.GET)
    public
//    @ResponseBody
    String showLookUpField(@RequestParam Long idLookUp, Model model) {

        LookUpField lookUpField = (LookUpField) entitySchemaDao.getField(idLookUp);
        Field field = entitySchemaDao.getField(lookUpField.getLookUpFieldId());
        model.addAttribute("fieldId", lookUpField.getFieldId());
        model.addAttribute("fieldValues", field.getFieldValues());

       /* String result = "";

        if (field.getValueType() == ValueType.STRING) {
            result = "<select name=" + lookUpField.getFieldId() + " class=\"form-control\">";
            for (FieldValue fieldValue : field.getFieldValues()) {
                StringValue stringValue = (StringValue) fieldValue;
                result += "<option value=" + stringValue.getId() + ">" + stringValue.getValue() + "</option>";
            }
            result += "</select>";
        } else if (field.getValueType() == ValueType.TEXT_AREA) {
            for (FieldValue fieldValue : field.getFieldValues()) {
                TextAreaValue textAreaValue = (TextAreaValue) fieldValue;
                TextAreaField textAreaField = (TextAreaField) field;
                result += "<div class=\"radio\"><label>" +
                        "<input type=\"radio\" name=" + lookUpField.getFieldId() +
                        " value=\"" + textAreaValue.getId() + "\"><textarea " +
                        "style=\"resize: none; border: none; background: none\" readonly " +
                        "rows = \"" + textAreaField.getCountLine() + "\">" +
                        textAreaValue.getTextAreaValue() + "</textarea></label></div>";
            }
        } else if (field.getValueType() == ValueType.MULTI_CHOICE) {
        } else if (field.getValueType() == ValueType.NUMBER) {
            result = "<select name=" + lookUpField.getFieldId() + " class=\"form-control\">";
            for (FieldValue fieldValue : field.getFieldValues()) {
                NumberValue numberValue = (NumberValue) fieldValue;
                result += "<option value=" + numberValue.getId() + ">" + numberValue.getNumberValue() + "</option>";
            }
            result += "</select>";
        } else if (field.getValueType() == ValueType.DATE) {
            result = "<select name=" + lookUpField.getFieldId() + " class=\"form-control\">";
            for (FieldValue fieldValue : field.getFieldValues()) {
                DateValue dateValue = (DateValue)fieldValue;
                result += "<option value=" + dateValue.getId() + ">" + dateValue.getDateValue() + "</option>";
            }
            result += "</select>";
        } else if (field.getValueType() == ValueType.LOOK_UP){
        } else if (field.getValueType() == ValueType.IMAGE){
            for (FieldValue fieldValue : field.getFieldValues()) {
                ImageValue imageValue = (ImageValue) fieldValue;
                result += "<div class=\"radio\"><label>" +
                        "<input type=\"radio\" name=" + lookUpField.getFieldId() +
                        " value=\"" + imageValue.getId() + "\"><img src=\"data:image/png;base64," +
                        imageValue.getImage() + "\" style=\"max-height: 100px; max-width: 100px\">" +
                        "</label></div>";
            }
        }*/

        return "field.lookup.string";
    }
}
