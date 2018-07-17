package ua.training.controller.listener;

import ua.training.constant.Attributes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Listener for storing logged users map in context for avoiding double login to system
 *
 * @author Zabudskyi Oleksandr
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * Add map with loged users to servlet context
     *
     * @param servletContextEvent event
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Map<ActiveUser, HttpSession> loggedUsers = new ConcurrentHashMap<>();
        servletContextEvent.getServletContext().setAttribute(Attributes.LOGGED_USERS, loggedUsers);
    }

    /**
     * Removing logged users map from context
     *
     * @param servletContextEvent event
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(Attributes.LOGGED_USERS);
    }
}
