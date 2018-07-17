package ua.training.controller.command.admin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.constant.Attributes;
import ua.training.constant.GlobalConstants;
import ua.training.constant.Pages;
import ua.training.model.service.TripService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TripsAndRoutesCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private TripService tripService;
    private TripsAndRoutesCommand tripsAndRoutesCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tripsAndRoutesCommand = new TripsAndRoutesCommand(tripService);
    }

    @Test
    public void testReturnTripPageIfNumberOfPageIncorrect() throws ServletException, IOException {
        when(request.getParameter(Attributes.PAGE)).thenReturn("notNumber");
        when(tripService.getNumberOfRecords()).thenReturn(100);
        when(tripService.getTripsAndRoutes(5, GlobalConstants.RECORDS_PER_PAGE)).thenReturn(new ArrayList<>());
        String page = tripsAndRoutesCommand.execute(request, response);
        assertNotNull(page);
        assertEquals(page, Pages.ALL_TRIPS_PAGE);
        verify(request).getParameter(Attributes.PAGE);
        verify(tripService).getNumberOfRecords();
        verify(tripService).getTripsAndRoutes(anyInt(), anyInt());
    }
}