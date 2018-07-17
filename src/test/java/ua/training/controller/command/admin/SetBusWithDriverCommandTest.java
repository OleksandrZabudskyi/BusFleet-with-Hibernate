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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class SetBusWithDriverCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private TripService tripService;
    @Mock
    private ParametersValidator parametersValidator;
    private SetBusWithDriverCommand setBusWithDriverCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setBusWithDriverCommand = new SetBusWithDriverCommand(tripService, parametersValidator);
    }

    @Test
    public void testReturnAllTripsCommand() throws ServiceException, ServletException, IOException {
        when(request.getParameter(Attributes.TRIP_ID)).thenReturn("10");
        when(request.getParameter(Attributes.BUS_ID)).thenReturn("2");
        when(request.getParameter(Attributes.DRIVER_ID)).thenReturn("4");
        when(request.getParameter(Attributes.PAGE)).thenReturn("1");
        when(parametersValidator.validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID, Attributes.DRIVER_ID)).thenReturn(false);
        doNothing().when(tripService).setBusOnTrip(anyInt(), anyInt());
        doNothing().when(tripService).setDriverOnTrip(anyInt(), anyInt());

        String command = setBusWithDriverCommand.execute(request, response);
        assertEquals(command, NameCommands.ALL_TRIPS);
        verify(request).getParameter(Attributes.TRIP_ID);
        verify(request).getParameter(Attributes.BUS_ID);
        verify(request).getParameter(Attributes.DRIVER_ID);
        verify(request).getParameter(Attributes.PAGE);
        verify(parametersValidator).validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID, Attributes.DRIVER_ID);
        verify(tripService).setBusOnTrip(anyInt(), anyInt());
        verify(tripService).setDriverOnTrip(anyInt(), anyInt());
    }

    @Test(expected = Exception.class)
    public void testIfServiceThrowAnyException() throws ServiceException, ServletException, IOException {
        when(request.getParameter(Attributes.TRIP_ID)).thenReturn("10");
        when(request.getParameter(Attributes.BUS_ID)).thenReturn("2");
        when(request.getParameter(Attributes.DRIVER_ID)).thenReturn("4");
        when(request.getParameter(Attributes.PAGE)).thenReturn("1");
        when(parametersValidator.validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID, Attributes.DRIVER_ID)).thenReturn(false);
        doThrow(new Exception()).when(tripService).setBusOnTrip(anyInt(), anyInt());

        String command = setBusWithDriverCommand.execute(request, response);
        assertEquals(command, NameCommands.ALL_TRIPS);
        verify(request).getParameter(Attributes.TRIP_ID);
        verify(request).getParameter(Attributes.BUS_ID);
        verify(request).getParameter(Attributes.DRIVER_ID);
        verify(request).getParameter(Attributes.PAGE);
        verify(parametersValidator).validateIfNullOrEmpty(request, Attributes.TRIP_ID, Attributes.BUS_ID, Attributes.DRIVER_ID);
        verify(tripService).setBusOnTrip(anyInt(), anyInt());
        verify(tripService).setDriverOnTrip(anyInt(), anyInt());
    }
}