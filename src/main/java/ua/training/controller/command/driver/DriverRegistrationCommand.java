package ua.training.controller.command.driver;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.LogMessages;
import ua.training.constant.Messages;
import ua.training.constant.Pages;
import ua.training.controller.command.Command;
import ua.training.controller.util.ParametersValidator;
import ua.training.model.entity.Driver;
import ua.training.model.service.EmployeeService;
import ua.training.model.service.SecurityService;
import ua.training.util.LocaleManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for driver registration
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see Attributes
 * @see Pages
 */
public class DriverRegistrationCommand implements Command {
    private final static Logger logger = Logger.getLogger(DriverRegistrationCommand.class);
    private EmployeeService employeeService;
    private SecurityService securityService;
    private ParametersValidator parametersValidator;

    public DriverRegistrationCommand(EmployeeService employeeService, SecurityService securityService,
                                     ParametersValidator parametersValidator) {
        this.employeeService = employeeService;
        this.securityService = securityService;
        this.parametersValidator = parametersValidator;
    }

    /**
     * Registration driver if parameters are valid otherwise redirect to registration page
     *
     * @param request httpServletRequest
     * @param response httpServletResponse
     * @return login page in case of successful registration or registration page otherwise
     * @throws ServletException overridden
     * @throws IOException overridden
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (parametersValidator.hasInvalidDriverData(request)) {
            return Pages.REGISTRATION_PAGE;
        }

        try {
            Driver driver = getDriverFromRequest(request);
            driver.setPassword(securityService.makePasswordHash(driver.getPassword()));
            employeeService.registerDriver(driver);
            return Pages.LOGIN_PAGE;
        } catch (Exception e) {
            logger.error(LogMessages.DRIVER_REGISTRATION_ERROR, e);
            request.setAttribute(Attributes.INFO_MESSAGE, LocaleManager.getProperty(Messages.USER_ALREADY_EXIST));
            return Pages.REGISTRATION_PAGE;
        }
    }

    private Driver getDriverFromRequest(HttpServletRequest request) {
        String firstName = request.getParameter(Attributes.FIRST_NAME);
        String lastName = request.getParameter(Attributes.LAST_NAME);
        String phoneNumber = request.getParameter(Attributes.PHONE_NUMBER);
        String drivingLicenceNumber = request.getParameter(Attributes.DRIVER_LICENCE_NUMBER);
        int drivingExperience = Integer.parseInt(request.getParameter(Attributes.DRIVING_EXPERIENCE));
        String email = request.getParameter(Attributes.EMAIL);
        String password = request.getParameter(Attributes.PASSWORD);

        return new Driver.DriverBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setPassword(password)
                .setDrivingLicenceNumber(drivingLicenceNumber)
                .setDrivingExperience(drivingExperience)
                .createDriver();
    }
}
