package com.intetics.controller;

import com.intetics.bean.Company;
import com.intetics.bean.Role;
import com.intetics.bean.User;
import com.intetics.dao.CompanyDao;
import com.intetics.dao.RoleDao;
import com.intetics.dao.UserDao;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for processing requests to User-related operations
 */

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private CompanyDao companyDao;

    @RequestMapping(value = "/registration")
    public String getRegistration() {

        return "admin-registration";
    }

    @RequestMapping(value = "/create_admin", method = RequestMethod.POST)
    public String createUserWithRoleAdmin(@RequestParam MultiValueMap<String, String> params) {

        Role role = roleDao.getRoleByName("Admin");

        User user = new User();
        user.setFirstName(params.get("firstName").get(0));
        user.setLastName(params.get("lastName").get(0));
        user.setEmail(params.get("email").get(0));
        user.setPassword(params.get("password").get(0));
        user.setConfirmed(false);
        user.setRole(role);

        //this will be confirm URL
        String emailMessage = "localhost:8080/registration/confirm/"+params.get("email").get(0).replace(".", "_");

        userDao.saveOrUpdate(user);

        return "redirect:/login";
    }

    @RequestMapping(value = "/registration/confirm/{email}")
    public String confirmCreateUserWithRoleAdmin(@Nonnull @PathVariable String email) {

        String validEmail = email.replace("_", ".");
        User user = userDao.getUserByEmail(validEmail);

        user.setConfirmed(true);

        userDao.saveOrUpdate(user);

        return "redirect:/login";
    }

    @RequestMapping(value = "/registration/company/create")
    public String createNewCompany(Model model) {

        model.addAttribute("title","Create Company");
        model.addAttribute("saveButton", "Create");
        model.addAttribute("type", "create");

        return "new-company";
    }

    @RequestMapping(value = "/registration/company/edit")
    public String editCompany(Model model) {

        model.addAttribute("title","Edit Company");
        model.addAttribute("saveButton", "Edit");
        model.addAttribute("type", "edit");

        return "edit-company";
    }

    @RequestMapping(value = "/registration/company/add", method = RequestMethod.POST)
    public String createCompany(@RequestParam MultiValueMap<String, String> params, Principal principal,
                                @RequestParam("image") MultipartFile image,
                                HttpServletRequest request) {

        User user = userDao.getUserByEmail(principal.getName());
        List<User> users = new ArrayList<User>();
        users.add(user);

        Company company = new Company();

        try {
            byte[] bytes = image.getBytes();
            company.setLogo(Base64.encode(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        company.setName(params.get("name").get(0));
        company.setAddress(params.get("address").get(0));
        company.setUsers(users);
        user.setCompany(company);
        companyDao.saveOrUpdate(company);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return "redirect:/home/entity/list";
    }

    @RequestMapping(value = "/registration/company/change", method = RequestMethod.POST)
    public String editCompany(@RequestParam MultiValueMap<String, String> params, Principal principal,
                                @RequestParam("image") MultipartFile image,
                                HttpServletRequest request) {

        User user = userDao.getUserByEmail(principal.getName());
        Company company = user.getCompany();

        try {
            byte[] bytes = image.getBytes();
            company.setLogo(Base64.encode(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        company.setName(params.get("name").get(0));
        company.setAddress(params.get("address").get(0));
        companyDao.saveOrUpdate(company);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return "redirect:/home/entity/list";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(ModelMap model, Integer login_error)
    {
        int error;

        if (login_error==null){
            error=0;
        } else
            error = login_error;

        if(error==1) {
            model.put("Error", "Incorrect username or password");
        }

        return "logIn";
    }
}
