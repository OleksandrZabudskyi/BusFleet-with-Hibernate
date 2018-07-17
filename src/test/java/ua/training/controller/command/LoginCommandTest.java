package ua.training.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.constant.Pages;
import ua.training.controller.util.ParametersValidator;
import ua.training.model.entity.Admin;
import ua.training.model.entity.Employee;
import ua.training.model.service.EmployeeService;
import ua.training.model.service.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LoginCommandTest {
    @Mock
    private EmployeeService employeeService;
    @Mock
    private SecurityService securityService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession httpSession;
    @Mock
    private ParametersValidator parametersValidator;
    private LoginCommand loginCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginCommand = new LoginCommand(employeeService, securityService, parametersValidator);
    }

    @Test
    public void testReturnLoginPageIfEmailAttributeNull() throws ServletException, IOException {
        when(request.getParameter(Attributes.EMAIL)).thenReturn(null);
        when(request.getParameter(Attributes.PASSWORD)).thenReturn("12345");
        when(parametersValidator.validateIfNullOrEmpty(request, Attributes.EMAIL, Attributes.PASSWORD)).thenReturn(true);
        String page = loginCommand.execute(request, response);

        assertEquals(page, Pages.LOGIN_PAGE);

        verify(request).getParameter(Attributes.EMAIL);
        verify(request).getParameter(Attributes.PASSWORD);
        verify(parametersValidator).validateIfNullOrEmpty(request, Attributes.EMAIL, Attributes.PASSWORD);
    }

    @Test
    public void testReturnLoginPageIfPasswordAttributeNull() throws ServletException, IOException {
        when(request.getParameter(Attributes.EMAIL)).thenReturn("volkov@gmail.com");
        when(request.getParameter(Attributes.PASSWORD)).thenReturn(null);
        when(parametersValidator.validateIfNullOrEmpty(request, Attributes.EMAIL, Attributes.PASSWORD)).thenReturn(true);
        String page = loginCommand.execute(request, response);

        assertEquals(page, Pages.LOGIN_PAGE);

        verify(request).getParameter(Attributes.EMAIL);
        verify(request).getParameter(Attributes.PASSWORD);
        verify(parametersValidator).validateIfNullOrEmpty(request, Attributes.EMAIL, Attributes.PASSWORD);
    }

    @Test
    public void testReturnLoginPageIfLoginInvalid() throws ServletException, IOException {
        when(request.getParameter(Attributes.EMAIL)).thenReturn("volkov@gmail.com");
        when(request.getParameter(Attributes.PASSWORD)).thenReturn("12345");
        when(parametersValidator.validateIfNullOrEmpty(request, Attributes.EMAIL, Attributes.PASSWORD)).thenReturn(false);
        when(employeeService.findEmployeeByEmail("volkov@gmail.com")).thenReturn(Optional.empty());
        String page = loginCommand.execute(request, response);

        assertEquals(page, Pages.LOGIN_PAGE);

        verify(request).getParameter(Attributes.EMAIL);
        verify(request).getParameter(Attributes.PASSWORD);
        verify(parametersValidator).validateIfNullOrEmpty(request, Attributes.EMAIL, Attributes.PASSWORD);
        verify(employeeService).findEmployeeByEmail(anyString());
    }

    @Test
    public void testRedirectToAdminPageIfLoginIsValid() throws ServletException, IOException {
        when(request.getParameter(Attributes.EMAIL)).thenReturn("aliev@gmail.com");
        when(request.getParameter(Attributes.PASSWORD)).thenReturn("12345");
        when(request.getSession()).thenReturn(httpSession);
        when(parametersValidator.validateIfNullOrEmpty(request, Attributes.EMAIL, Attributes.PASSWORD)).thenReturn(false);
        Admin admin = new Admin.AdminBuilder()
                .setId(1)
                .setEmail("aliev@gmail.com")
                .setPassword("77+977+9SO+/vciT77+977+9N2Z/fnPvv70SQw==,-1758309945")
                .setRole(Employee.ROLE.ADMIN)
                .createAdmin();
        when(employeeService.findEmployeeByEmail("aliev@gmail.com")).thenReturn(Optional.ofNullable(admin));
        when(securityService.comparePasswords("12345", admin.getPassword())).thenReturn(true);
        String page = loginCommand.execute(request, response);

        assertEquals(page, NameCommands.REDIRECT.concat(NameCommands.ADMIN_PAGE));

        verify(request).getParameter(Attributes.EMAIL);
        verify(request).getParameter(Attributes.PASSWORD);
        verify(request).getSession();
        verify(parametersValidator).validateIfNullOrEmpty(request, Attributes.EMAIL, Attributes.PASSWORD);
        verify(employeeService).findEmployeeByEmail(anyString());
        verify(securityService).comparePasswords(anyString(), anyString());
    }

    @Test
    public void testRedirectToDriverPageIfLoginIsValid() throws ServletException, IOException {
        when(request.getParameter(Attributes.EMAIL)).thenReturn("novak@gmail.com");
        when(request.getParameter(Attributes.PASSWORD)).thenReturn("12345");
        when(request.getSession()).thenReturn(httpSession);
        when(parametersValidator.validateIfNullOrEmpty(request, Attributes.EMAIL, Attributes.PASSWORD)).thenReturn(false);
        Admin admin = new Admin.AdminBuilder()
                .setId(1)
                .setEmail("novak@gmail.com")
                .setPassword("77+9yYLvv70ceu+/ve+/ve+/vTR2Eu+/ve+/ve+/ve+/vQ==,1128203477")
                .setRole(Employee.ROLE.DRIVER)
                .createAdmin();
        when(employeeService.findEmployeeByEmail("novak@gmail.com")).thenReturn(Optional.ofNullable(admin));
        when(securityService.comparePasswords("12345", admin.getPassword())).thenReturn(true);
        String page = loginCommand.execute(request, response);

        assertEquals(page, NameCommands.REDIRECT.concat(NameCommands.DRIVER_PAGE));

        verify(request).getParameter(Attributes.EMAIL);
        verify(request).getParameter(Attributes.PASSWORD);
        verify(request).getSession();
        verify(parametersValidator).validateIfNullOrEmpty(request, Attributes.EMAIL, Attributes.PASSWORD);
        verify(employeeService).findEmployeeByEmail(anyString());
        verify(securityService).comparePasswords(anyString(), anyString());
    }
}