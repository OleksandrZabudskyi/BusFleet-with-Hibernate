package ua.training.controller.command;

import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.constant.Pages;
import ua.training.model.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for redirecting to specific index page according user role
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see Pages
 */
public class IndexPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = (String) request.getSession().getAttribute(Attributes.ROLE);
        if (Employee.ROLE.ADMIN.toString().equals(role)) {
            return Pages.ADMIN_PAGE;
        }
        if (Employee.ROLE.DRIVER.toString().equals(role)) {
            return Pages.DRIVER_PAGE;
        }
        return Pages.INDEX_PAGE;
    }
}
