package server.servlets;

import server.entities.Anime;
import server.services.RecommendationService;
import server.services.ShikimoriService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Recommendations", urlPatterns = "/recommendations")
public class RecommendationServlet extends HttpServlet {
    private ShikimoriService shikimoriService;
    private RecommendationService recommendationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object animeList = req.getAttribute("animeList");
        if (animeList == null) {
            req.getRequestDispatcher("/index").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/recommendations.ftl").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("shikiUsername");
        if (username == null) {
            req.getRequestDispatcher("/index").forward(req, resp);
            return;
        }
        Long shikiId = null;
        try {
            shikiId = shikimoriService.getShikiUserIdByUsername(username);
        } catch (Exception e) {
            req.getRequestDispatcher("/index").forward(req, resp);
            return;
        }
        var rates = shikimoriService.getAnimeRatesByShikiUserId(shikiId);
        List<Anime> animeList = recommendationService.getRecommendations(rates);
        req.setAttribute("animeList", animeList);
        this.doGet(req, resp);

    }

    @Override
    public void init() throws ServletException {
        shikimoriService = (ShikimoriService) getServletContext().getAttribute("shikimoriService");
        recommendationService = (RecommendationService) getServletContext().getAttribute("recommendationService");
    }
}
