package server.listeners;


import server.dao.AnimeDao;
import server.dao.GenreDao;
import server.dao.UserDao;
import server.dao.impl.AnimeDaoImpl;
import server.dao.impl.GenreDaoImpl;
import server.dao.impl.UserDaoImpl;
import server.services.AnimeSaverService;
import server.services.LoginService;
import server.services.MyAnimeListService;
import server.services.RecommendationService;
import server.services.SignUpService;
import server.services.UserService;
import server.services.impl.AnimeSaverServiceImpl;
import server.services.impl.LoginServiceImpl;
import server.services.impl.MyAnimeListServiceImpl;
import server.services.impl.RecommendationServiceImpl;
import server.services.impl.SignUpServiceImpl;
import server.services.impl.UserServiceImpl;
import server.util.DatabaseConnectionUtil;
import server.util.DatabaseInitializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;


public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataSource dataSource = (DataSource) sce.getServletContext().getAttribute("dataSource");
        UserDao userDao = new UserDaoImpl(dataSource);
        AnimeDao animeDao = new AnimeDaoImpl(dataSource);
        GenreDao genreDao = new GenreDaoImpl(dataSource);
        AnimeSaverService animeSaverService = new AnimeSaverServiceImpl(animeDao, genreDao);
        MyAnimeListService myAnimeListService = new MyAnimeListServiceImpl();
        LoginService loginService = new LoginServiceImpl(userDao);
        SignUpService signUpService = new SignUpServiceImpl(userDao);
        UserService userService = new UserServiceImpl(userDao);
        RecommendationService recommendationService = new RecommendationServiceImpl(animeSaverService, animeDao);

        DatabaseInitializer databaseInitializer = new DatabaseInitializer(animeSaverService, myAnimeListService);

        sce.getServletContext().setAttribute("loginService", loginService);
        sce.getServletContext().setAttribute("signUpService", signUpService);
        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("databaseInitializer", databaseInitializer);
        sce.getServletContext().setAttribute("recommendationService", recommendationService);

    }
}
