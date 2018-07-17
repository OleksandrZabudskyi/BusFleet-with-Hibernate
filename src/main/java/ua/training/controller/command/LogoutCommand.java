package ua.training.controller.command;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.LogMessages;
import ua.training.constant.NameCommands;
import ua.training.model.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command for log out user from system
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see NameCommands
 */
public class LogoutCommand implements Command {
    private final static Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(Attributes.ACTIVE_USER);
        session.setAttribute(Attributes.ROLE, Employee.ROLE.GUEST.toString());
        logger.info(LogMessages.USER_LOGOUT);
        return NameCommands.REDIRECT.concat(NameCommands.INDEX_PAGE);
    }
}
