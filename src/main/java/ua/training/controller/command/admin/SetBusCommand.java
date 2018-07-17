package ua.training.controller.command.admin;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.LogMessages;
import ua.training.constant.Messages;
import ua.training.constant.NameCommands;
import ua.training.controller.command.Command;
import ua.training.controller.util.ParametersValidator;
import ua.training.exeptions.ServiceException;
import ua.training.model.service.TripService;
import ua.training.util.LocaleManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for appointment bus to route
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see Attributes
 * @see NameCommands
 */
public class SetBusCommand implements Command {
    private final static Logger logger = Logger.getLogger(SetBusCommand.class);
    private TripService tripService;
    private ParametersValidator parametersValidator;

    public SetBusCommand(TripService tripService, ParametersValidator parametersValidator) {
        this.tripService = tripService;
        this.parametersValidator = parametersValidator;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tripId = request.getParameter(Attributes.TRIP_ID);
        String busId = request.getParameter(Attributes.BUS_ID);
        String currentPage = request.getParameter(Attributes.PAGE);

        if (parametersValidator.validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID)) {
            return NameCommands.ALL_TRIPS;
        }

        try {
            tripService.setBusOnTrip(Integer.parseInt(tripId), Integer.parseInt(busId));
        } catch (ServiceException e) {
            logger.error(LogMessages.TRANSACTION_ERROR, e);
            request.setAttribute(Attributes.BUS_INFO_MESSAGE, LocaleManager.getProperty(Messages.BUS_ALREADY_USED));
        }
        request.setAttribute(Attributes.PAGE, currentPage);
        return NameCommands.ALL_TRIPS;
    }
}
