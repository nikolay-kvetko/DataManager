package com.intetics.filter;

import com.intetics.bean.User;
import com.intetics.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCompanyExistFilter implements Filter {

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
            if("Admin".equals(user.getRole().getName())){
                if(user.getCompany() == null){
                    response.sendRedirect("/registration/new_company");
                } else {
                    chain.doFilter(req, resp);
                }
            } else {
                chain.doFilter(req, resp);
            }
        } else response.sendRedirect("/login");
    }

    public void destroy() {
    }
}
