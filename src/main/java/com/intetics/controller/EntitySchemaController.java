package com.intetics.controller;

import com.intetics.bean.Company;
import com.intetics.bean.EntitySchema;
import com.intetics.bean.User;
import com.intetics.dao.EntitySchemaDao;
import com.intetics.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Nonnull;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Controller responsible for processing requests to EntitySchema-related operations
 */
@Controller
@RequestMapping("/entity")
public class EntitySchemaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntitySchemaController.class);

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/list")
    public String getEntitySchemaList(ModelMap model, Principal principal) {

        User user = userDao.getUserByEmail(principal.getName());
        List<EntitySchema> entitySchemas = user.getCompany().getEntitySchemas();
        model.addAttribute("entitySchemaList", entitySchemas);

        LOGGER.trace("The list of entities has been requested");

        return "entity-list";
    }

    @RequestMapping(value = "/create")
    public String createEntitySchema(ModelMap model, Principal principal) {

        User user = userDao.getUserByEmail(principal.getName());
        List<EntitySchema> entitySchemas = user.getCompany().getEntitySchemas();
        model.addAttribute("entitySchemaList", entitySchemas);

        EntitySchema entitySchema = new EntitySchema();
        model.addAttribute("EntitySchema", entitySchema);

        model.addAttribute("modalTitle", "Create Entity");
        model.addAttribute("modalSaveButton", "Create");

        LOGGER.trace("Create new EntitySchema");

        return "create-entity";
    }

    @RequestMapping(value = "/edit/{entitySchemaId}", method = RequestMethod.GET)
    public String startToEditEntitySchema(@Nonnull @PathVariable Long entitySchemaId, ModelMap model,
                                          Principal principal) {
        Assert.notNull(entitySchemaId);

        User user = userDao.getUserByEmail(principal.getName());

        Company company = user.getCompany();

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);

        if(!company.getEntitySchemas().contains(entitySchema)) {
            return "error";
        }

        model.addAttribute("EntitySchema", entitySchema);

        model.addAttribute("modalTitle", "Edit Entity");
        model.addAttribute("modalSaveButton", "Edit");

        return "edit-entity";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEntitySchema(EntitySchema entitySchema, Principal principal) {

        Date currentDate = new Date();

        User user = userDao.getUserByEmail(principal.getName());

        Company company = user.getCompany();
        entitySchema.setCompany(company);

        if(entitySchema.getId() != null){
            if(!company.getEntitySchemas().contains(entitySchema)) {
                return "error";
            }
            entitySchema.setModifiedDate(currentDate);
            entitySchemaDao.saveOrUpdate(entitySchema);
            return "redirect:/entity/"+entitySchema.getId()+"/field/list";
        }

        entitySchema.setModifiedDate(currentDate);
        entitySchema.setCompany(company);

        entitySchemaDao.saveOrUpdate(entitySchema);
        return "redirect:/entity/list";
    }

    @RequestMapping(value = "/delete/{entitySchemaId}/confirm", method = RequestMethod.GET)
    public String startDeleteEntitySchema(@Nonnull @PathVariable Long entitySchemaId, ModelMap model,
                                          Principal principal) {
        Assert.notNull(entitySchemaId);

        User user = userDao.getUserByEmail(principal.getName());

        Company company = user.getCompany();

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);

        if(!company.getEntitySchemas().contains(entitySchema)) {
            return "error";
        }

        model.addAttribute("EntitySchema", entitySchema);

        List<EntitySchema> entitySchemas = user.getCompany().getEntitySchemas();
        model.addAttribute("entitySchemaList", entitySchemas);

        return "delete-entity";
    }

    @RequestMapping(value = "/delete/{entitySchemaId}", method = RequestMethod.GET)
    public String deleteEntitySchema(@Nonnull @PathVariable Long entitySchemaId, Principal principal) {
        Assert.notNull(entitySchemaId);

        User user = userDao.getUserByEmail(principal.getName());

        Company company = user.getCompany();

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(entitySchemaId);

        if(!company.getEntitySchemas().contains(entitySchema)) {
            return "error";
        }

        entitySchemaDao.delete(entitySchema);

        return "redirect:/entity/list";
    }
}
