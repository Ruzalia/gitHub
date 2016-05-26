package com.epam.ruzaliia_yakunina.java.lesson9.task9.listeners;

import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserService;
import com.epam.ruzaliia_yakunina.java.lesson9.task9.services.UserServiceImplementation;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Created by Ruzaliia_Yakunina on 5/17/2016.
 */
public class AccountServletContextMemory implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(AccountServletContextMemory.class);
    UserService service;

    /**
     * Initializes context for servlet(sets as service for users  CopyOnWriteArrayList<User> service if it was used earlier
     * or creates new CopyOnWriteArrayList<User> service with 1 user).
     *
     * @param event
     */
    public void contextInitialized(ServletContextEvent event) {
        try {
            PropertyConfigurator.configure(event.getServletContext()
                    .getRealPath("WEB-INF/log4j.properties"));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        if (service == null) {
            service = new UserServiceImplementation();
            service.initializeMemory();
        }
        event.getServletContext().setAttribute("UsersMemory", service);
        LOGGER.info("Memory context initialized");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("Context destroyed");
    }
}
