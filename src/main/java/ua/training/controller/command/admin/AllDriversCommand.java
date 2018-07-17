package ua.training.controller.command.admin;

import ua.training.constant.Attributes;
import ua.training.constant.Pages;
import ua.training.controller.command.Command;
import ua.training.model.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Command for loading all drivers
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see Attributes
 * @see Pages
 */
public class AllDriversCommand implements Command {
    private EmployeeService employeeService;

    public AllDriversCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tripId = request.getParameter(Attributes.TRIP_ID);
        String currentPage = request.getParameter(Attributes.PAGE);
        if (Objects.nonNull(tripId) && Integer.parseInt(tripId) != 0) {
            request.setAttribute(Attributes.TRIP_ID, tripId);
        }
        request.setAttribute(Attributes.DRIVERS, employeeService.getAllDrivers());
        request.setAttribute(Attributes.PAGE, currentPage);
        return Pages.DRIVERS_PAGE;
    }
}
