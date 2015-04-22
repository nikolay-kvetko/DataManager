package com.intetics.controller;

import com.intetics.bean.Company;
import com.intetics.bean.Role;
import com.intetics.bean.User;
import com.intetics.dao.CompanyDao;
import com.intetics.dao.RoleDao;
import com.intetics.dao.UserDao;
import com.intetics.validation.UserAuthorizationValidator;
import com.intetics.validation.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nonnull;
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

    @Autowired
    private UserRegistrationValidator userRegistrationValidator;

    @Autowired
    private UserAuthorizationValidator userAuthorizationValidator;

    @RequestMapping(value = "/registration")
    public String getRegistration(Model model) {
        model.addAttribute("user", new User());
        return "admin-registration";
    }

    @RequestMapping(value = "/create_admin", method = RequestMethod.POST)
    public String createUserWithRoleAdmin(@RequestParam MultiValueMap<String, String> params,
                                          @ModelAttribute("user") User user,
                                          BindingResult bindingResult) {

        Role role = roleDao.getRoleByName("Admin");

        user = new User();
        user.setFirstName(params.get("firstName").get(0));
        user.setLastName(params.get("lastName").get(0));
        user.setEmail(params.get("email").get(0));
        user.setPassword(params.get("password").get(0));
        user.setConfirmPassword(params.get("confirmPassword").get(0));
        user.setConfirmed(false);
        user.setRole(role);

        userRegistrationValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            bindingResult.resolveMessageCodes("errors.user.firstName");
            return "admin-registration";
        }

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
    public String createNewCompany() {

        return "new-company";
    }

    @RequestMapping(value = "/registration/company/add", method = RequestMethod.POST)
    public String createCompany(@RequestParam MultiValueMap<String, String> params, Principal principal,
                                @RequestParam("image") MultipartFile image) {

        User user = userDao.getUserByEmail(principal.getName());
        List<User> users = new ArrayList<User>();
        users.add(user);

        /*BufferedImage image2 = null;
        try {
            image2 = ImageIO.read(image.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer width = image2.getWidth();
        Integer height = image2.getHeight();*/

        Company company = new Company();

        try {
            byte[] bytes = image.getBytes();
            company.setLogo(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        company.setName(params.get("name").get(0));
        company.setAddress(params.get("address").get(0));
        company.setUsers(users);
        user.setCompany(company);
        companyDao.saveOrUpdate(company);

        return "redirect:/home/entity/list";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(/*@RequestParam MultiValueMap<String, String> params,
                        @ModelAttribute("user") User user,
                        BindingResult bindingResult*/) {
//        user = new User();
//        user.setEmail(params.get("email").get(0));
//        user.setPassword(params.get("password").get(0));
//
//        userAuthorizationValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "user-authorization";
//        }
//        return "user-authorization";
        return null;
    }
}
