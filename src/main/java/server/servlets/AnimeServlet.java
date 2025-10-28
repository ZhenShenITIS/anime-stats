package server.servlets;

import server.entities.Anime;
import server.services.AnimeSaverService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/anime")
public class AnimeServlet extends HttpServlet {
    AnimeSaverService animeSaverService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Anime anime = animeSaverService.getWithGenresByID(id);
        req.setAttribute("anime", anime);
        req.getRequestDispatcher("/anime.ftl").forward(req, resp);

    }

    @Override
    public void init() throws ServletException {
        animeSaverService = (AnimeSaverService) getServletContext().getAttribute("animeSaverService");
    }
}
