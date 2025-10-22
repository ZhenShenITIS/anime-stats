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
            resp.sendRedirect("index");
            return;
        }
        System.out.println(animeList);
        req.getRequestDispatcher("recommendations.ftl").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("shikiUsername");
        if (username == null) {
            resp.sendRedirect("index");
            System.out.println("1");
            return;
        }
        Long shikiId = null;
        try {
            shikiId = shikimoriService.getShikiUserIdByUsername(username);
        } catch (Exception e) {
            resp.sendRedirect("index");
            System.out.println("2");
            e.printStackTrace();
            return;
        }
        var rates = shikimoriService.getAnimeRatesByShikiUserId(shikiId);
        System.out.println(rates);
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
