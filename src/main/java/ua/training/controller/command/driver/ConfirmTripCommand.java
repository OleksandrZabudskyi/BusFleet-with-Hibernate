package ua.training.controller.command.driver;

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
 * Command for confirmation appointment
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see Attributes
 * @see NameCommands
 */
public class ConfirmTripCommand implements Command {
    private TripService tripService;

    public ConfirmTripCommand(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tripId = request.getParameter(Attributes.TRIP_ID);
        if (Objects.nonNull(tripId) && tripId.trim().isEmpty()) {
            return NameCommands.APPOINTMENT;
        }
        tripService.setTripConfirmation(Integer.parseInt(tripId));
        return NameCommands.APPOINTMENT;
    }
}
