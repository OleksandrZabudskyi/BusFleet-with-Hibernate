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
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AllBusesCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private BusService busService;
    @Mock
    private AllBusesCommand allBusesCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        allBusesCommand = new AllBusesCommand(busService);
    }

    @Test
    public void testIfReturnBusPage() throws ServletException, IOException {
        when(request.getParameter(Attributes.TRIP_ID)).thenReturn("10");
        when(request.getParameter(Attributes.PAGE)).thenReturn("2");
        when(busService.getAllBusesWithRoutes()).thenReturn(new HashMap<>());
        String page = allBusesCommand.execute(request, response);
        assertEquals(page, Pages.BUSES_PAGE);
        verify(request).getParameter(Attributes.TRIP_ID);
        verify(request).getParameter(Attributes.PAGE);
        verify(busService).getAllBusesWithRoutes();
    }

    @Test(expected = NumberFormatException.class)
    public void testOnExceptionIfTripIdNotNumber() throws ServletException, IOException {
        when(request.getParameter(Attributes.TRIP_ID)).thenReturn("Test");
        when(request.getParameter(Attributes.PAGE)).thenReturn("2");
        when(busService.getAllBusesWithRoutes()).thenReturn(new HashMap<>());
        allBusesCommand.execute(request, response);
        verify(request).getParameter(Attributes.TRIP_ID);
        verify(request).getParameter(Attributes.PAGE);
        verify(busService).getAllBusesWithRoutes();
    }
}