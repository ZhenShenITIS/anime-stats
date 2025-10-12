package server.servlets;

import server.dto.UserDto;
import server.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ajax/validate/email/registration")
public class AjaxValidateEmailRegistrationServlet extends HttpServlet {
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        System.out.println(email);
        UserDto userDto = userService.getByEmail(email);
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
