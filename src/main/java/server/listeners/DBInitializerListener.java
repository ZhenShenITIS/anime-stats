package server.listeners;

import server.util.DatabaseInitializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class DBInitializerListener implements ServletContextListener {
    private ScheduledExecutorService scheduler;
    DatabaseInitializer databaseInitializer;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        databaseInitializer = (DatabaseInitializer) sce.getServletContext().getAttribute("databaseInitializer");
        scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable integrityCheckTask = () -> {
            try {
                databaseInitializer.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        scheduler.scheduleAtFixedRate(integrityCheckTask, 0, 24, TimeUnit.HOURS);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (scheduler != null) {
            scheduler.shutdownNow();
        }
    }


}
