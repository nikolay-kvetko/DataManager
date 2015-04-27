package com.intetics.controller;

import com.intetics.bean.Company;
import com.intetics.bean.Role;
import com.intetics.bean.User;
import com.intetics.dao.CompanyDao;
import com.intetics.dao.RoleDao;
import com.intetics.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

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


    @RequestMapping(value = "/registration")
    public String getRegistration() {

        return "admin-registration";
    }

    @RequestMapping(value = "/create_admin", method = RequestMethod.POST)
    public String createUserWithRoleAdmin(@RequestParam MultiValueMap<String, String> params, HttpServletRequest request, Model model) {

        Role role = roleDao.getRoleByName("Admin");

        UUID uid = UUID.randomUUID();
        String stringUid = String.valueOf(uid).replace("-", "_");

        String confirmURL = "http://" +
                request.getServerName() +                   // "host"
                ":" +                                       // ":"
                request.getServerPort() +                   // "8080"
                "/registration/confirm/" +                  // "/registration/confirm/"
                stringUid;                                  // "uid"


        User user = new User();
        user.setFirstName(params.get("firstName").get(0));
        user.setLastName(params.get("lastName").get(0));
        user.setEmail(params.get("email").get(0));
        user.setPassword(params.get("password").get(0));
        user.setConfirmingURL(stringUid);
        user.setConfirmed(false);
        user.setRole(role);

        /*MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setText(confirmURL, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(message);*/

        Date date = new Date();
        user.setModifiedDate(date);

        userDao.saveOrUpdate(user);
        model.addAttribute("email", user.getEmail());

        return "after-registration-page";
    }

    @RequestMapping(value = "/registration/confirm/{uid}")
    public String confirmCreateUserWithRoleAdmin(@Nonnull @PathVariable String uid) {

        User user = userDao.getUserByConfirmingURL(uid);

        if(user != null) {
            user.setConfirmed(true);
            user.setConfirmingURL(null);

            userDao.saveOrUpdate(user);

            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
            authenticationManager.authenticate(authenticationToken);

            if(authenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                if(!"ADMIN".equalsIgnoreCase(user.getRole().getName())){
                    return "redirect:/registration/password/create";
                }
                return "after-confirm-page";
            }

            return "error";     //user is not authenticated
        }

        return "error";     //user with this uid is not exist
    }

    @RequestMapping(value = "/registration/company/create")
    public String createCompany(Model model) {

        Company company = new Company();
        model.addAttribute("company", company);

        return "new-company";
    }

    @RequestMapping(value = "/registration/company/add", method = RequestMethod.POST)
    public String addCompany(Company company, Principal principal,
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

        try {
            byte[] bytes = image.getBytes();
            company.setLogo(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        company.setUsers(users);
        user.setCompany(company);
        companyDao.saveOrUpdate(company);

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

        Date date = new Date();
        user.setModifiedDate(date);

        userDao.saveOrUpdate(user);

        return "redirect:/home/entity/list";
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(ModelMap model, Integer login_error)
    {
        int error;

        if (login_error==null){
            error=0;
        } else
            error = login_error;

        if(error==1) {
            model.put("error", "Incorrect username or password");
        }

        return "login";
    }

    @RequestMapping(value = "/manage_users/edit/{userId}")
    public String editUser(@Nonnull @PathVariable Long userId, Model model, HttpServletRequest request) {
        User user = userDao.getUserById(userId);
        model.addAttribute("user", user);

        HttpSession session = request.getSession();
        session.setAttribute("userId", userId);

        return "user-edit";
    }

    @RequestMapping(value = "/manage_users/save")
    public String saveUser(User user, Model model, HttpServletRequest request) {
        if (user != null) {
            HttpSession session = request.getSession();
            long userId = (Long) session.getAttribute("userId");
            User currentEditingUser = userDao.getUserById(userId);
            user.setUserId(currentEditingUser.getUserId());
            user.setPassword(currentEditingUser.getPassword());
            user.setCompany(currentEditingUser.getCompany());
            user.setRole(currentEditingUser.getRole());
            user.setConfirmed(currentEditingUser.getConfirmed());
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
        model.addAttribute("user", user);

        List<User> users = userDao.getUserByEmail(principal.getName()).getCompany().getUsers();
        model.addAttribute("usersList", users);

        List<Role> roles = roleDao.getRoleNamesExcludingAdmin();
        model.addAttribute("rolesList", roles);

        return "create-user";
    }

    @RequestMapping(value = "/manage_users/add")
    public String addUser(User user, Principal principal, HttpServletRequest request, @RequestParam("userRole") String role) {

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
                request.getServerName() +                   // "host"
                ":" +                                       // ":"
                request.getServerPort() +                   // "8080"
                "/registration/confirm/" +                  // "/registration/confirm/"
                stringUid;                                  // "uid"

        /*MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setText(confirmURL, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(message);*/

        user.setPassword("");

        userDao.saveOrUpdate(user);

        return "redirect:/manage_users/list";
    }

    @RequestMapping(value = "/manage_users/delete/{userId}/confirm")
    public String startDeleteUser(@Nonnull @PathVariable Long userId, Principal principal, Model model){
        Assert.notNull(userId);

        User authenticatedUser = userDao.getUserByEmail(principal.getName());
        Company company = authenticatedUser.getCompany();

        User userForDelete = userDao.getUserById(userId);

        if(!company.getUsers().contains(userForDelete))
            return "error";

        List<User> users = company.getUsers();

        model.addAttribute("usersList", users);
        model.addAttribute("user", userForDelete);

        return "delete-user";
    }

    @RequestMapping(value = "/manage_users/delete/{userId}")
    public String deleteUser(@Nonnull @PathVariable Long userId, Principal principal, Model model){
        Assert.notNull(userId);

        User authenticatedUser = userDao.getUserByEmail(principal.getName());
        Company company = authenticatedUser.getCompany();

        User deletingUser = userDao.getUserById(userId);

        if(!company.getUsers().contains(deletingUser))
            return "error";

        userDao.delete(deletingUser);

        return "redirect:/manage_users/list";
    }
}
