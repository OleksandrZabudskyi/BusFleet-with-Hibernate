package ua.training.controller.listener;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.LogMessages;
import ua.training.model.entity.Employee;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Map;
import java.util.Optional;

/**
 * Class implementing data transfer object for storing them as attribute in servlet context, and
 * avoiding double login same user.
 *
 * @author Zabudskyi Oleksandr
 */
public class ActiveUser implements HttpSessionBindingListener {
    private final static Logger logger = Logger.getLogger(ActiveUser.class);
    private Employee employee;
    private boolean alreadyLoggedIn;

    public ActiveUser(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public boolean isAlreadyLoggedIn() {
        return alreadyLoggedIn;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        addLoggedUserIfNotPresent(event);
    }

    /**
     * Add user to httpSession context
     * If user does not exist in context just add it, otherwise invalidate old user session and add new one
     * to avoid have same user with different session
     *
     * @param event dto binding event
     */
    private void addLoggedUserIfNotPresent(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();
        Map<ActiveUser, HttpSession> activeUsers = (Map<ActiveUser, HttpSession>) context.getAttribute(Attributes.LOGGED_USERS);
        Optional<HttpSession> oldSession = Optional.ofNullable(activeUsers.get(this));
        if (oldSession.isPresent()) {
            oldSession.get().invalidate();
            alreadyLoggedIn = true;
        }
        activeUsers.put(this, event.getSession());
        logger.debug(LogMessages.ADD_USER + employee.getEmail());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        removeLoggedUser(event);
    }

    /**
     * Removing logged user from context
     *
     * @param event
     */
    private void removeLoggedUser(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();
        Map<ActiveUser, HttpSession> activeUsers = (Map<ActiveUser, HttpSession>) context.getAttribute(Attributes.LOGGED_USERS);
        activeUsers.remove(this);
        logger.debug(LogMessages.REMOVE_USER + employee.getEmail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActiveUser activeUser = (ActiveUser) o;

        return employee != null ? employee.equals(activeUser.employee) : activeUser.employee == null;
    }

    @Override
    public int hashCode() {
        return employee != null ? employee.hashCode() : 0;
    }
}
