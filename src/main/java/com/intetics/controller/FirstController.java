package com.intetics.controller;

import com.intetics.pojos.EntityName;
import com.intetics.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        model.put("entityNamesList", entityNames);
        return "/index.jsp";
    }
}
