package server.servlets;

import server.dto.UserRegistrationDto;
import server.services.SignUpService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignUp", urlPatterns = "/signup")
public class SignUpServlet extends HttpServlet {
    SignUpService signUpService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("sign_up.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (signUpService.signUp(UserRegistrationDto.builder().email(email).name(name).password(password).build())) {
            resp.sendRedirect("success_registration.ftl");
        } else {
            resp.sendRedirect("already_signed_up.ftl");
        }
    }

    @Override
    public void init() throws ServletException {
        signUpService = (SignUpService) getServletContext().getAttribute("signUpService");
    }
}
