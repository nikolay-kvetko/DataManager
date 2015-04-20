package com.intetics.controller;

import com.intetics.bean.Field;
import com.intetics.dao.EntitySchemaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public @ResponseBody String getFieldListByEntitySchema(@RequestParam Long entityId,
                                                           @RequestParam Long currentEntityId){

        String result = "<select name=\"selectField\" class=\"form-control\" id=\"selectField\">";

        List<Field> fieldList = entitySchemaDao.getEntityFieldList(entityId);

        for (Field field : fieldList){
            if (field.getFieldId() != currentEntityId) {
                result += "<option value=" + field.getFieldId() + ">" + field.getName() + "</option>";
            }
        }

        result += "</select>";

        return result;
    }
    @RequestMapping(value = "/getImageByUrl", method = RequestMethod.POST)
    public @ResponseBody String getImageByUrl(@RequestParam String url){

        URL tmpUrl;

        try {
            tmpUrl = new URL(url);
        } catch (MalformedURLException e) {
            return null;
        }

        String result = "<img src=\" " + tmpUrl + "\" style=\"max-width: 150px; max-height: 150px;\">";

        return result;
    }
}
