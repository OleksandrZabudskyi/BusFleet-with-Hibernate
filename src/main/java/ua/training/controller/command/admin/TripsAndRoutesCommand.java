package ua.training.controller.command.admin;

import ua.training.constant.Attributes;
import ua.training.constant.GlobalConstants;
import ua.training.constant.Pages;
import ua.training.constant.Regex;
import ua.training.controller.command.Command;
import ua.training.model.entity.Trip;
import ua.training.model.service.TripService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Command for loading routes with trips
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see Attributes
 * @see Pages
 */
public class TripsAndRoutesCommand implements Command {
    private TripService tripService;

    public TripsAndRoutesCommand(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;
        String page = request.getParameter(Attributes.PAGE);

        if (Objects.nonNull(page) && Pattern.matches(Regex.POSITIVE_NUMBER, page)) {
            currentPage = Integer.parseInt(request.getParameter(Attributes.PAGE));
        }

        int numberOfRecords = tripService.getNumberOfRecords();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / GlobalConstants.RECORDS_PER_PAGE);
        if (currentPage > numberOfPages) {
           currentPage = numberOfPages;
        }

        List<Trip> trips = tripService.getTripsAndRoutes((currentPage - 1)
                * GlobalConstants.RECORDS_PER_PAGE, GlobalConstants.RECORDS_PER_PAGE);

        request.setAttribute(Attributes.TRIPS, trips);
        request.setAttribute(Attributes.NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(Attributes.CURRENT_PAGE, currentPage);

        return Pages.ALL_TRIPS_PAGE;
    }
}
