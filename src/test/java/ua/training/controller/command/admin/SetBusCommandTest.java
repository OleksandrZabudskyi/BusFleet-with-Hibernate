package ua.training.controller.command.admin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.controller.util.ParametersValidator;
import ua.training.exeptions.ServiceException;
import ua.training.model.service.TripService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class SetBusCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private TripService tripService;
    @Mock
    private ParametersValidator parametersValidator;
    private SetBusCommand setBusCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setBusCommand = new SetBusCommand(tripService, parametersValidator);
    }

    @Test
    public void testReturnAllTripsCommand() throws ServiceException, ServletException, IOException {
        when(request.getParameter(Attributes.TRIP_ID)).thenReturn("10");
        when(request.getParameter(Attributes.BUS_ID)).thenReturn("2");
        when(request.getParameter(Attributes.PAGE)).thenReturn("2");
        when(parametersValidator.validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID)).thenReturn(false);
        doNothing().when(tripService).setBusOnTrip(anyInt(), anyInt());

        String command = setBusCommand.execute(request, response);
        assertEquals(command, NameCommands.ALL_TRIPS);
        verify(request).getParameter(Attributes.TRIP_ID);
        verify(request).getParameter(Attributes.BUS_ID);
        verify(request).getParameter(Attributes.PAGE);
        verify(parametersValidator).validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID);
        verify(tripService).setBusOnTrip(anyInt(), anyInt());

    }

    @Test
    public void testReturnAllTripsCommandIfParameterNullOrEmpty() throws ServletException, IOException {
        when(parametersValidator.validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID)).thenReturn(true);

        String command = setBusCommand.execute(request, response);
        assertEquals(command, NameCommands.ALL_TRIPS);
        verify(parametersValidator).validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID);
    }

    @Test(expected = NumberFormatException.class)
    public void testReturnExceptionIfParametersNotNumber() throws ServletException, IOException {
        when(request.getParameter(Attributes.TRIP_ID)).thenReturn("Trip");
        when(request.getParameter(Attributes.BUS_ID)).thenReturn("bus");
        when(parametersValidator.validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID)).thenReturn(false);

        String command = setBusCommand.execute(request, response);
        assertEquals(command, NameCommands.ALL_TRIPS);
        verify(request).getParameter(Attributes.TRIP_ID);
        verify(request).getParameter(Attributes.BUS_ID);
        verify(parametersValidator).validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID);

    }

    @Test(expected = Exception.class)
    public void testIfServiceThrowAnyException() throws ServiceException, ServletException, IOException {
        when(request.getParameter(Attributes.TRIP_ID)).thenReturn("10");
        when(request.getParameter(Attributes.BUS_ID)).thenReturn("2");
        when(request.getParameter(Attributes.PAGE)).thenReturn("2");
        when(parametersValidator.validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID)).thenReturn(false);
        doThrow(Exception.class).when(tripService).setBusOnTrip(anyInt(), anyInt());

        String command = setBusCommand.execute(request, response);
        assertEquals(command, NameCommands.ALL_TRIPS);
        verify(request).getParameter(Attributes.TRIP_ID);
        verify(request).getParameter(Attributes.BUS_ID);
        verify(request).getParameter(Attributes.PAGE);
        verify(parametersValidator).validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID);
        verify(tripService).setBusOnTrip(anyInt(), anyInt());

    }
}