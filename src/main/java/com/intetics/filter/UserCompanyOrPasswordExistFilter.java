package com.intetics.filter;

import com.intetics.bean.User;
import com.intetics.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserCompanyOrPasswordExistFilter implements Filter {

    private ServletContext context;

    @Autowired
    private UserDao userDao;

    public void init(FilterConfig config) throws ServletException {

        this.context = config.getServletContext();
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletResponse response = (HttpServletResponse) resp;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userDao.getUserByEmail(authentication.getName());

        if(user != null){

            HttpServletRequest request = (HttpServletRequest)req;
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if("ADMIN".equalsIgnoreCase(user.getRole().getName())){
                if(user.getCompany() == null){
                    response.sendRedirect("/registration/company/create");
                } else {
                    chain.doFilter(req, resp);
                }
            } else {
                if(user.getPassword().isEmpty()){
                    response.sendRedirect("/registration/password/create");
                } else {
                    chain.doFilter(req, resp);
                }
            }
        } else response.sendRedirect("/");
    }

    public void destroy() {
    }
}
