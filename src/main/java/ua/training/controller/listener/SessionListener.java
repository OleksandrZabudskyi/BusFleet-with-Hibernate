package ua.training.controller.listener;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.LogMessages;
import ua.training.model.entity.Employee;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Listener for adding default role to session
 * Session should not be without role
 *
 * @author Zabudskyi Oleksandr
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    private final static Logger logger = Logger.getLogger(SessionListener.class);

    /**
     * Add default user role as GUEST {@link Employee.ROLE}
     *
     * @param httpSessionEvent event
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        session.setAttribute(Attributes.ROLE, Employee.ROLE.GUEST.toString());
        logger.debug(LogMessages.SESSION_CREATED + session.getId());
    }

    /**
     * Destroying session
     *
     * @param httpSessionEvent event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        logger.debug(LogMessages.SESSION_DESTROYED + session.getId());
    }
}
