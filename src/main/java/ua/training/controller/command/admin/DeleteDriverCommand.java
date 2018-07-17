package ua.training.controller.command.admin;

import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.controller.command.Command;
import ua.training.model.service.TripService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Command for deleting driver from route
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see Attributes
 * @see NameCommands
 */
public class DeleteDriverCommand implements Command {
    private TripService tripService;

    public DeleteDriverCommand(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tripId = request.getParameter(Attributes.TRIP_ID);
        String currentPage = request.getParameter(Attributes.PAGE);
        if (Objects.isNull(tripId) || tripId.isEmpty()) {
            return NameCommands.ALL_TRIPS;
        }
        tripService.deleteDriverFromTrip(Integer.parseInt(tripId));
        request.setAttribute(Attributes.PAGE, currentPage);
        return NameCommands.ALL_TRIPS;
    }
}
