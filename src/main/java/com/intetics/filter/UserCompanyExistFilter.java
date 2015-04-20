package com.intetics.filter;

import com.intetics.bean.User;
import com.intetics.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;

public class UserCompanyExistFilter implements Filter {

    private ServletContext context;

    @Autowired
    private UserDao userDao;

    public void init(FilterConfig config) throws ServletException {

        this.context = config.getServletContext();
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userDao.getUserByEmail(authentication.getName());

        chain.doFilter(req, resp);
    }

    public void destroy() {
    }
}
