package server.listeners;


import server.dao.UserDao;
import server.dao.impl.UserDaoImpl;
import server.services.LoginService;
import server.services.SignUpService;
import server.services.UserService;
import server.services.impl.LoginServiceImpl;
import server.services.impl.SignUpServiceImpl;
import server.services.impl.UserServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserDao userDao = new UserDaoImpl();
        LoginService loginService = new LoginServiceImpl(userDao);
        SignUpService signUpService = new SignUpServiceImpl(userDao);
        UserService userService = new UserServiceImpl(userDao);
        sce.getServletContext().setAttribute("loginService", loginService);
        sce.getServletContext().setAttribute("signUpService", signUpService);
        sce.getServletContext().setAttribute("userService", userService);


    }
}
