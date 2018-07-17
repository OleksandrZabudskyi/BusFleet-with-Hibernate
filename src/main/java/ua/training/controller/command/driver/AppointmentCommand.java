package ua.training.controller.command.driver;

import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.constant.Pages;
import ua.training.controller.command.Command;
import ua.training.controller.listener.ActiveUser;
import ua.training.model.entity.Trip;
import ua.training.model.service.TripService;
import ua.training.model.service.impl.TripServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Command for loading driver appointment
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see Attributes
 * @see Pages
 */
public class AppointmentCommand implements Command {
    private TripService tripService;

    public AppointmentCommand(TripServiceImpl tripService) {
        this.tripService = tripService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActiveUser activeUser = (ActiveUser) request.getSession().getAttribute(Attributes.ACTIVE_USER);
        if(Objects.isNull(activeUser)) {
            return NameCommands.LOGOUT;
        }
        List<Trip> tripList = tripService.getAppointmentTripsToDrivers(activeUser.getEmployee());
        request.setAttribute(Attributes.TRIPS, tripList);
        return Pages.APPOINTMENT_PAGE;
    }
}
