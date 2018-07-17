package ua.training.controller.command.admin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.model.service.TripService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteBusCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private TripService tripService;
    private DeleteBusCommand deleteBusCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        deleteBusCommand = new DeleteBusCommand(tripService);
    }

    @Test
    public void testIfReturnAllTripsCommand() throws ServletException, IOException {
        when(request.getParameter(Attributes.TRIP_ID)).thenReturn("10");
        when(request.getParameter(Attributes.PAGE)).thenReturn("2");
        doNothing().when(tripService).deleteBusFromTrip(anyInt());
        String nameCommand = deleteBusCommand.execute(request, response);
        assertEquals(nameCommand, NameCommands.ALL_TRIPS);
        verify(request).getParameter(Attributes.TRIP_ID);
        verify(request).getParameter(Attributes.PAGE);
        verify(tripService).deleteBusFromTrip(anyInt());
    }

    @Test(expected = NumberFormatException.class)
    public void testOnExceptionIfTripIdNotNumber() throws ServletException, IOException {
        when(request.getParameter(Attributes.TRIP_ID)).thenReturn("aad");
        when(request.getParameter(Attributes.PAGE)).thenReturn("2");
        doNothing().when(tripService).deleteBusFromTrip(anyInt());
        deleteBusCommand.execute(request, response);
        verify(request).getParameter(Attributes.TRIP_ID);
        verify(request).getParameter(Attributes.PAGE);
        verify(tripService).deleteBusFromTrip(anyInt());
    }
}