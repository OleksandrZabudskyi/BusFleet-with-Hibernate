package ua.training.controller.command.driver;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.constant.Attributes;
import ua.training.constant.Messages;
import ua.training.constant.Pages;
import ua.training.controller.util.ParametersValidator;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.exeptions.ServiceException;
import ua.training.model.entity.Driver;
import ua.training.model.service.EmployeeService;
import ua.training.model.service.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class DriverRegistrationCommandTest {

    @Mock
    private EmployeeService employeeService;
    @Mock
    private SecurityService securityService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ParametersValidator parametersValidator;
    private DriverRegistrationCommand driverRegistrationCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        driverRegistrationCommand = new DriverRegistrationCommand(employeeService, securityService, parametersValidator);
    }

    @Test
    public void testReturnRegistrationPageIfParametersIsNotValid() throws ServletException, IOException {
        when(parametersValidator.hasInvalidDriverData(request)).thenReturn(true);
        String page = driverRegistrationCommand.execute(request, response);

        assertEquals(page, Pages.REGISTRATION_PAGE);

        verify(parametersValidator).hasInvalidDriverData(request);
    }

    @Test
    public void testReturnLoginPageIfDriverRegistered() throws Exception {
        when(request.getParameter(Attributes.FIRST_NAME)).thenReturn("Oleg");
        when(request.getParameter(Attributes.LAST_NAME)).thenReturn("Parin");
        when(request.getParameter(Attributes.PHONE_NUMBER)).thenReturn("+38(097)234-12-34");
        when(request.getParameter(Attributes.DRIVER_LICENCE_NUMBER)).thenReturn("CMC23456");
        when(request.getParameter(Attributes.DRIVING_EXPERIENCE)).thenReturn("12");
        when(request.getParameter(Attributes.EMAIL)).thenReturn("parin@gmail.com");
        when(request.getParameter(Attributes.PASSWORD)).thenReturn("12345");
        when(parametersValidator.hasInvalidDriverData(request)).thenReturn(false);
        doNothing().when(employeeService).registerDriver(any(Driver.class));

        String page = driverRegistrationCommand.execute(request, response);

        assertEquals(page, Pages.LOGIN_PAGE);

        verify(request).getParameter(Attributes.FIRST_NAME);
        verify(parametersValidator).hasInvalidDriverData(request);
        verify(employeeService).registerDriver(any());

    }

    @Test
    public void testReturnRegistrationPageIfException() throws Exception {
        when(request.getParameter(Attributes.FIRST_NAME)).thenReturn("Oleg");
        when(request.getParameter(Attributes.LAST_NAME)).thenReturn("Parin");
        when(request.getParameter(Attributes.PHONE_NUMBER)).thenReturn("+38(097)234-12-34");
        when(request.getParameter(Attributes.DRIVER_LICENCE_NUMBER)).thenReturn("CMC23456");
        when(request.getParameter(Attributes.DRIVING_EXPERIENCE)).thenReturn("12");
        when(request.getParameter(Attributes.EMAIL)).thenReturn("parin@gmail.com");
        when(request.getParameter(Attributes.PASSWORD)).thenReturn("12345");
        when(parametersValidator.hasInvalidDriverData(request)).thenReturn(false);
        doThrow(new ServiceException(new EntityAlreadyExistException(Messages.USER_ALREADY_EXIST))).
                when(employeeService).registerDriver(any(Driver.class));
        String page = driverRegistrationCommand.execute(request, response);

        assertEquals(page, Pages.REGISTRATION_PAGE);

        verify(parametersValidator).hasInvalidDriverData(request);
        verify(employeeService).registerDriver(any());
    }
}