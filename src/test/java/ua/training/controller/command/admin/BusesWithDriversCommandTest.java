package ua.training.controller.command.admin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.constant.Attributes;
import ua.training.constant.Pages;
import ua.training.model.service.BusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BusesWithDriversCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private BusService busService;
    private BusesWithDriversCommand busesWithDriversCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        busesWithDriversCommand = new BusesWithDriversCommand(busService);
    }

    @Test
    public void testIfReturnBusesWithRoutesPage() throws ServletException, IOException {
        when(request.getParameter(Attributes.TRIP_ID)).thenReturn("6");
        when(request.getParameter(Attributes.PAGE)).thenReturn("2");
        when(busService.getAllBusesToAllDrivers()).thenReturn(new ArrayList<>());
        String page = busesWithDriversCommand.execute(request, response);
        assertEquals(page, Pages.BUSES_WITH_ROUTES);
        verify(request).getParameter(Attributes.TRIP_ID);
        verify(request).getParameter(Attributes.PAGE);
        verify(busService).getAllBusesToAllDrivers();
    }

    @Test(expected = NumberFormatException.class)
    public void testOnExceptionIfTripIdNotNumber() throws ServletException, IOException {
        when(request.getParameter(Attributes.TRIP_ID)).thenReturn("Test");
        when(request.getParameter(Attributes.PAGE)).thenReturn("2");
        when(busService.getAllBusesToAllDrivers()).thenReturn(new ArrayList<>());
        busesWithDriversCommand.execute(request, response);
        verify(request).getParameter(Attributes.TRIP_ID);
        verify(request).getParameter(Attributes.PAGE);
        verify(busService).getAllBusesToAllDrivers();
    }
}