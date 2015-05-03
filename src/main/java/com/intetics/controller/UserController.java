package com.intetics.controller;

import com.intetics.bean.Company;
import com.intetics.bean.Role;
import com.intetics.bean.User;
import com.intetics.dao.CompanyDao;
import com.intetics.dao.RoleDao;
import com.intetics.dao.UserDao;
import com.intetics.validation.UserExistValidator;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nonnull;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

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
    private JavaMailSenderImpl mailSender;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserExistValidator userExistValidator;

    @RequestMapping(value = "/registration")
    public String getRegistration(Model model) {
        if (roleDao.getRoleByName("Admin") == null) {
            Role role = new Role();
            role.setName("Admin");
            roleDao.saveOrUpdate(role);
        }

        if (roleDao.getRoleByName("ReadOnly") == null) {
            Role role = new Role();
            role.setName("ReadOnly");
            roleDao.saveOrUpdate(role);
        }

        if (roleDao.getRoleByName("ReadWrite") == null) {
            Role role = new Role();
            role.setName("ReadWrite");
            roleDao.saveOrUpdate(role);
        }
        model.addAttribute("user", new User());
        return "admin-registration";
    }

    @RequestMapping(value = "/create_admin", method = RequestMethod.POST)
    public String createUserWithRoleAdmin(@ModelAttribute("user") @Valid User user,
                                          BindingResult bindingResult,
                                          HttpServletRequest request,
                                          Model model) {

        Role role = roleDao.getRoleByName("Admin");

        UUID uid = UUID.randomUUID();
        String stringUid = String.valueOf(uid).replace("-", "_");

        String confirmURL = "http://" +
                "study.atwss.com" +                         // "host"
                ":" +                                       // ":"
                request.getServerPort() +                   // "8080"
                "/registration/confirm/" +                  // "/registration/confirm/"
                stringUid;                                  // "uid"

        user.setConfirmingURL(stringUid);
        user.setConfirmed(false);
        user.setRole(role);

        userExistValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin-registration";
        }

//        MimeMessage message = mailSender.createMimeMessage();
//
//        MimeMessageHelper helper;
//
//        try {
//            helper = new MimeMessageHelper(message, true);
//            helper.setTo(user.getEmail());
//            helper.setText(confirmURL, true);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        mailSender.send(message);

        Date date = new Date();
        user.setModifiedDate(date);

        userDao.saveOrUpdate(user);
        model.addAttribute("email", user.getEmail());

        return "email-confirmation-sent";
    }

    @RequestMapping(value = "/registration/confirm/{uid}")
    public String confirmCreateUserWithRoleAdmin(@Nonnull @PathVariable String uid) {

        User user = userDao.getUserByConfirmingURL(uid);

        if (user != null) {
            user.setConfirmed(true);
            user.setConfirmingURL(null);
            user.setConfirmPassword(user.getPassword());
            Date date = new Date();
            user.setModifiedDate(date);

            userDao.saveOrUpdate(user);

            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
            authenticationManager.authenticate(authenticationToken);

            if (authenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                if (!"ADMIN".equalsIgnoreCase(user.getRole().getName())) {
                    return "redirect:/registration/password/create";
                }
                return "email-confirmed";
            }

            return "error";     //user is not authenticated
        }

        return "error";     //user with this uid is not exist
    }

    @RequestMapping(value = "/registration/company/create")
    public String createCompany(Model model) {

        Company company = new Company();
        model.addAttribute("company", company);
        model.addAttribute("title", "Create Company");
        model.addAttribute("saveButton", "Create");
        model.addAttribute("type", "create");

        return "new-company";
    }

    @RequestMapping(value = "/registration/company/edit")
    public String editCompany(Model model, Principal principal) {

        Company company = userDao.getUserByEmail(principal.getName()).getCompany();
        model.addAttribute("company", company);
        model.addAttribute("title", "Edit Company");
        model.addAttribute("saveButton", "Edit");
        model.addAttribute("type", "edit");

        return "edit-company";
    }

    @RequestMapping(value = "/registration/company/add", method = RequestMethod.POST)
    public String addCompany(Company company, Principal principal,
                             @RequestParam("image") MultipartFile image,
                             HttpServletRequest request) {

        User user = userDao.getUserByEmail(principal.getName());
        user.setConfirmPassword(user.getPassword());
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

        try {
            byte[] bytes = image.getBytes();
            company.setLogo(Base64.encode(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        if (image.getSize() > 0) {
            try {
                byte[] bytes = image.getBytes();
                company.setLogo(Base64.encode(bytes));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        company.setName(params.get("name").get(0));
        company.setAddress(params.get("address").get(0));
        companyDao.saveOrUpdate(company);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return "redirect:/home/entity/list";
    }

    @RequestMapping(value = "/registration/password/create")
    public String createPassword() {
        return "create-password";
    }

    @RequestMapping(value = "/registration/password/add", method = RequestMethod.POST)
    public String addPassword(Principal principal,
                              @Nonnull @RequestParam("newPassword") String password) {

        User user = userDao.getUserByEmail(principal.getName());
        user.setPassword(password);
        user.setConfirmPassword(user.getPassword());
        Date date = new Date();
        user.setModifiedDate(date);

        userDao.saveOrUpdate(user);

        return "redirect:/home/entity/list";
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(ModelMap model, Integer login_error) {
        int error;

        if (login_error == null) {
            error = 0;
        } else
            error = login_error;

        if (error == 1) {
            model.put("error", "Incorrect username or password");
        }

        return "login";
    }

    @RequestMapping(value = "/manage_users/edit/{userId}")
    public String editUser(@Nonnull @PathVariable Long userId, Model model, HttpServletRequest request, Principal principal) {
        User authenticatedUser = userDao.getUserByEmail(principal.getName());
        Company company = authenticatedUser.getCompany();
        User editingUser = userDao.getUserById(userId);

        if (!company.getUsers().contains(editingUser) || editingUser.getRole().getName().equalsIgnoreCase("Admin"))
            return "error";

        User user = userDao.getUserById(userId);
        user.setConfirmPassword(user.getPassword());
        model.addAttribute("user", user);

        List<User> users = userDao.getUserByEmail(principal.getName()).getCompany().getUsers();
        model.addAttribute("usersList", users);

        List<Role> roles = roleDao.getRoleNamesExcludingAdmin();
        model.addAttribute("rolesList", roles);

        HttpSession session = request.getSession();
        session.setAttribute("userId", userId);

        return "user-edit";
    }

    @RequestMapping(value = "/manage_users/save")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                           Model model, HttpServletRequest request,
                           @RequestParam("userRole") String roleName, Principal principal) {
        if (bindingResult.hasErrors()) {
            List<User> users = userDao.getUserByEmail(principal.getName()).getCompany().getUsers();
            model.addAttribute("usersList", users);

            List<Role> roles = roleDao.getRoleNamesExcludingAdmin();
            model.addAttribute("rolesList", roles);
            return "user-edit";
        }
        if (user != null) {
            HttpSession session = request.getSession();
            long userId = (Long) session.getAttribute("userId");

            User currentEditingUser = userDao.getUserById(userId);

            user.setUserId(currentEditingUser.getUserId());
            user.setPassword(currentEditingUser.getPassword());
            user.setConfirmPassword(currentEditingUser.getPassword());
            user.setCompany(currentEditingUser.getCompany());

            if (!currentEditingUser.getConfirmed()) {

                UUID uid = UUID.randomUUID();
                String stringUid = String.valueOf(uid).replace("-", "_");
                user.setConfirmingURL(stringUid);
                user.setConfirmed(false);

                String confirmURL = "http://" +
                        "study.atwss.com" +                         // "host"
                        ":" +                                       // ":"
                        request.getServerPort() +                   // "8080"
                        "/registration/confirm/" +                  // "/registration/confirm/"
                        stringUid;                                  // "uid"

//                MimeMessage message = mailSender.createMimeMessage();
//
//                MimeMessageHelper helper;
//
//                try {
//                    helper = new MimeMessageHelper(message, true);
//                    helper.setTo(user.getEmail());
//                    helper.setText(confirmURL, true);
//                } catch (MessagingException e) {
//                    e.printStackTrace();
//                }
//
//                mailSender.send(message);
            }

            Role userRole = roleDao.getRoleByName(roleName);
            if (userRole != null) {
                user.setRole(userRole);
            }

            user.setConfirmed(currentEditingUser.getConfirmed());
            user.setModifiedDate(new Date());

            userDao.saveOrUpdate(user);
        }
        model.addAttribute("user", user);

        return "redirect:/manage_users/list";
    }

    @RequestMapping(value = "/manage_users/list")
    public String getUserList(ModelMap model, Principal principal) {

        User user = userDao.getUserByEmail(principal.getName());
        List<User> users = user.getCompany().getUsers();
        model.addAttribute("usersList", users);

        return "user-list";
    }

    @RequestMapping(value = "/manage_users/create")
    public String createUser(ModelMap model, Principal principal) {

        User user = new User();
        model.addAttribute("newUser", user);

        List<User> users = userDao.getUserByEmail(principal.getName()).getCompany().getUsers();
        model.addAttribute("usersList", users);

        List<Role> roles = roleDao.getRoleNamesExcludingAdmin();
        model.addAttribute("rolesList", roles);

        return "create-user";
    }

    @RequestMapping(value = "/manage_users/add")
    public String addUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult, Model model,
                          Principal principal, HttpServletRequest request, @RequestParam("userRole") String role) {

        System.out.println();
        userExistValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {

            List<User> users = userDao.getUserByEmail(principal.getName()).getCompany().getUsers();
            model.addAttribute("usersList", users);

            List<Role> roles = roleDao.getRoleNamesExcludingAdmin();
            model.addAttribute("rolesList", roles);
            return "create-user";
        }

        user.setRole(roleDao.getRoleByName(role));

        Company company = userDao.getUserByEmail(principal.getName()).getCompany();
        user.setCompany(company);

        Date date = new Date();
        user.setModifiedDate(date);

        UUID uid = UUID.randomUUID();
        String stringUid = String.valueOf(uid).replace("-", "_");
        user.setConfirmingURL(stringUid);
        user.setConfirmed(false);

        String confirmURL = "http://" +
                "study.atwss.com" +                         // "host"
                ":" +                                       // ":"
                request.getServerPort() +                   // "8080"
                "/registration/confirm/" +                  // "/registration/confirm/"
                stringUid;                                  // "uid"

//        MimeMessage message = mailSender.createMimeMessage();
//
//        MimeMessageHelper helper;
//
//        try {
//            helper = new MimeMessageHelper(message, true);
//            helper.setTo(user.getEmail());
//            helper.setText(confirmURL, true);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        mailSender.send(message);

        Random random = new Random();
        user.setPassword(Integer.toString(random.nextInt(1234567)));
        user.setConfirmPassword(user.getPassword());

        userDao.saveOrUpdate(user);

        return "redirect:/manage_users/list";
    }

    @RequestMapping(value = "/manage_users/delete/{userId}/confirm")
    public String startDeleteUser(@Nonnull @PathVariable Long userId, Principal principal, Model model) {
        Assert.notNull(userId);

        User userForDelete = userDao.getUserById(userId);

        if ("ADMIN".equalsIgnoreCase(userForDelete.getRole().getName()))
            return "error";

        User authenticatedUser = userDao.getUserByEmail(principal.getName());
        Company company = authenticatedUser.getCompany();

        if (!company.getUsers().contains(userForDelete))
            return "error";

        List<User> users = company.getUsers();

        model.addAttribute("usersList", users);
        model.addAttribute("userForDelete", userForDelete);

        return "delete-user";
    }

    @RequestMapping(value = "/manage_users/delete/{userId}")
    public String deleteUser(@Nonnull @PathVariable Long userId, Principal principal) {
        Assert.notNull(userId);

        User deletingUser = userDao.getUserById(userId);

        if ("ADMIN".equalsIgnoreCase(deletingUser.getRole().getName()))
            return "error";

        User authenticatedUser = userDao.getUserByEmail(principal.getName());
        Company company = authenticatedUser.getCompany();

        if (!company.getUsers().contains(deletingUser))
            return "error";

        userDao.delete(deletingUser);

        return "redirect:/manage_users/list";
    }
}
