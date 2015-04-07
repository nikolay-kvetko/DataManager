package com.intetics.controller;

import com.intetics.bean.EntityName;
import com.intetics.bean.Field;
import com.intetics.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * created: 06.04.2015 10:14
 *
 * @author a.chervyakovsky
 */

@Controller
public class FirstController {

    @Autowired(required = true)
    private BaseService baseService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String start(ModelMap model) {

        List<EntityName> entityNames = baseService.getAll(EntityName.class);
        model.addAttribute("entityNamesList", entityNames);
        return "/show.jsp";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request) {

        EntityName entityName = new EntityName();
        String name = request.getParameter("entityName");
        entityName.setName(name);
        baseService.saveOrUpdate(entityName);
        return "redirect:/main";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showEntityField(Long entityNameId, ModelMap model) {

        EntityName entityName = (EntityName) baseService.get(EntityName.class, entityNameId);
        model.addAttribute("entityName", entityName);
        return "/showEntityNameFields.jsp";
    }

    ///addFieldToEntityName?entityNameId
    @RequestMapping(value = "/addFieldToEntityName", method = RequestMethod.GET)
    public String addFieldToEntityName(Long entityNameId, HttpSession httpSession) {

        EntityName entityName = (EntityName) baseService.get(EntityName.class, entityNameId);
        httpSession.setAttribute("entityName", entityName);
        return "/addField.jsp";
    }

    @RequestMapping(value = "/saveField", method = RequestMethod.POST)
    public String saveField(HttpSession httpSession, HttpServletRequest request) {

        EntityName entityName = (EntityName) httpSession.getAttribute("entityName");
        Field field = new Field();
        field.setName(request.getParameter("fieldName"));
        field.setType(request.getParameter("fieldType"));
        field.setEntityName(entityName);
        baseService.saveOrUpdate(field);
        return "redirect:/show?entityNameId="+entityName.getEntityNameId();
    }
}
