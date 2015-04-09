package com.intetics.controller;

import org.springframework.stereotype.Controller;

/**
 * created: 06.04.2015 10:14
 *
 * @author a.chervyakovsky
 */

@Controller
public class FirstController {

    /*@Autowired(required = true)
    private BaseService baseService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String start(ModelMap model) {

        List<EntityScheme> entitySchemes = baseService.getAll(EntityScheme.class);
        model.addAttribute("entityNamesList", entitySchemes);
        return "/show.jsp";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request) {

        EntityScheme entityScheme = new EntityScheme();
        String name = request.getParameter("entityName");
        entityScheme.setName(name);
        baseService.saveOrUpdate(entityScheme);
        return "redirect:/main";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showEntityField(Long entityNameId, ModelMap model) {

        EntityScheme entityScheme = (EntityScheme) baseService.get(EntityScheme.class, entityNameId);
        model.addAttribute("entityName", entityScheme);
        return "/showEntityNameFields.jsp";
    }

    ///addFieldToEntityName?entityNameId
    @RequestMapping(value = "/addFieldToEntityName", method = RequestMethod.GET)
    public String addFieldToEntityName(Long entityNameId, HttpSession httpSession) {

        EntityScheme entityScheme = (EntityScheme) baseService.get(EntityScheme.class, entityNameId);
        httpSession.setAttribute("entityName", entityScheme);
        return "/addField.jsp";
    }*/

    /*@RequestMapping(value = "/saveField", method = RequestMethod.POST)
    public String saveField(HttpSession httpSession, HttpServletRequest request) {

        EntityName entityName = (EntityName) httpSession.getAttribute("entityName");
        Field field = new Field();
        field.setName(request.getParameter("fieldName"));
        field.setType(request.getParameter("fieldType"));
        field.setEntityName(entityName);
        baseService.saveOrUpdate(field);
        return "redirect:/show?entityNameId="+entityName.getEntitySchemeId();
    }*/
}
