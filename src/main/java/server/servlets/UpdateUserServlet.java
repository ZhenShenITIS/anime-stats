package server.servlets;

import server.dto.UserDto;
import server.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/users/update")
public class UpdateUserServlet extends HttpServlet {

    private UserService userService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Long id = Long.parseLong(req.getParameter("id"));
        String email = req.getParameter("email");
        userService.update(UserDto
                .builder()
                        .id(id)
                        .name(name)
                        .email(email)
                .build());
        req.getRequestDispatcher("/admin/users").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }
}
