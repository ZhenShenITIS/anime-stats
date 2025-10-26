package server.servlets;

import server.dto.UserDto;
import server.dto.UserLoginDto;
import server.entities.User;
import server.services.LoginService;
import server.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet  extends HttpServlet {
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.ftl").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDto user = loginService.login(new UserLoginDto(email, password));
        if (user != null) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("email", email);
            httpSession.setAttribute("id", user.getId());
            httpSession.setMaxInactiveInterval(365*24*60*60);
            req.setAttribute("user", user);
            req.getRequestDispatcher("index").forward(req, resp);
        } else {
            req.getRequestDispatcher("login.ftl").forward(req, resp);
        }
    }


    @Override
    public void init() throws ServletException {
        loginService = (LoginService) getServletContext().getAttribute("loginService");
    }
}
