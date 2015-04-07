package com.intetics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller responsible for processing requests to entity-related operations
 */
@Controller
@RequestMapping("/entity")
public class EntityController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(ModelMap model) {

//        List<EntityName> entityNames = baseService.getAll(EntityName.class);
//        model.addAttribute("entityNamesList", entityNames);
        return "entity-list";
    }
}
