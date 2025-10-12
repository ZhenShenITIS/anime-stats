package server.servlets;

import server.dto.UserDto;
import server.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ajax/validate/login/registration")
public class AjaxValidateLoginRegistrationServlet extends HttpServlet {
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        System.out.println(login);
        UserDto userDto = userService.getByLogin(login);
        resp.setContentType("plain/text");
        if (userDto == null) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }

    }

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }
}
