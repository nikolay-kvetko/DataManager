package com.intetics.controller;

import com.intetics.bean.*;
import com.intetics.dao.CompanyDao;
import com.intetics.dao.EntitySchemaDao;
import com.intetics.dao.UserDao;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @Autowired
    private UserDao userDao;

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
    public String showLookUpField(@RequestParam Long idLookUp, Model model) {

        String result = "";

        LookUpField lookUpField = (LookUpField) entitySchemaDao.getField(idLookUp);
        Field field = entitySchemaDao.getField(lookUpField.getLookUpFieldId());

        model.addAttribute("fieldId", lookUpField.getFieldId());
        model.addAttribute("fieldValues", field.getFieldValues());

        if (field.getValueType() == ValueType.STRING) {
            result = "field.lookup.string";
        } else if (field.getValueType() == ValueType.TEXT_AREA) {
            TextAreaField textAreaField = (TextAreaField) field;
            model.addAttribute("rowsCount", textAreaField.getCountLine());
            result = "field.lookup.text-area";
        } else if (field.getValueType() == ValueType.NUMBER) {
            result = "field.lookup.number";
        } else if (field.getValueType() == ValueType.DATE) {
            result = "field.lookup.date";
        } else if (field.getValueType() == ValueType.IMAGE) {
            result = "field.lookup.image";
        }

        return result;
    }

    @RequestMapping(value = "/showLogo", method = RequestMethod.GET)
    public String showLogo(Model model, @RequestParam("image") MultipartFile image) {

        String imageValue = "";

        try {
            byte[] bytes = image.getBytes();
            imageValue = Base64.encode(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("logoValue", imageValue);

        return "company.logo";
    }
}
