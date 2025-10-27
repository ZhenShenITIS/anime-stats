package server.servlets;

import server.dto.UserDto;
import server.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Object idObj = session.getAttribute("id");
        if (idObj == null) {
            req.getRequestDispatcher("/login.ftl").forward(req, resp);
            return;
        }
        Long id = (Long) idObj;
        UserDto user = userService.getById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/profile.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Object idObj = session.getAttribute("id");
        if (idObj == null) {
            req.getRequestDispatcher("/login.ftl").forward(req, resp);
            return;
        }
        Long id = (Long) idObj;
        UserDto user = userService.getById(id);
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        user.setName(name);
        user.setEmail(email);
        userService.update(user);
        session.setAttribute("user", user);
        session.setAttribute("email", email);
        this.doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }
}
