package ua.training.controller.command;

import org.apache.log4j.Logger;
import ua.training.constant.*;
import ua.training.controller.listener.ActiveUser;
import ua.training.controller.util.ParametersValidator;
import ua.training.model.entity.Employee;
import ua.training.model.service.EmployeeService;
import ua.training.model.service.SecurityService;
import ua.training.util.LocaleManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Class for login to application
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see Attributes
 * @see Pages
 * @see LogMessages
 */
public class LoginCommand implements Command {
    private final static Logger logger = Logger.getLogger(LoginCommand.class);
    private EmployeeService employeeService;
    private SecurityService securityService;
    private ParametersValidator parametersValidator;

    public LoginCommand(EmployeeService employeeService, SecurityService securityService, ParametersValidator parametersValidator) {
        this.employeeService = employeeService;
        this.securityService = securityService;
        this.parametersValidator = parametersValidator;
    }

    /**
     * Check if user with such email and password exist in database, if true
     * create new dto object for storing current user in session context
     * otherwise return
     *
     * @param request httpServletRequest
     * @param response httpServletResponse
     * @return redirect command depending of user role
     * @throws ServletException overriding
     * @throws IOException overriding
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(Attributes.EMAIL);
        String password = request.getParameter(Attributes.PASSWORD);
        if (parametersValidator.validateIfNullOrEmpty(request, Attributes.EMAIL, Attributes.PASSWORD)) {
            return Pages.LOGIN_PAGE;
        }

        Optional<Employee> userOptional = employeeService.findEmployeeByEmail(email);
        if (!userOptional.isPresent() || !securityService.comparePasswords(password, userOptional.get().getPassword())) {
            request.setAttribute(Attributes.ERROR_MESSAGE, LocaleManager.getProperty(Messages.WRONG_LOGIN_OR_PASSWORD));
            return Pages.LOGIN_PAGE;
        }

        Employee employee = userOptional.get();
        HttpSession httpSession = request.getSession();
        ActiveUser activeUser = new ActiveUser(employee);
        httpSession.setAttribute(Attributes.ACTIVE_USER, activeUser);

        if (activeUser.isAlreadyLoggedIn()) {
            logger.warn(LogMessages.USER_ALREADY_LOGGED + employee.getEmail());
        }
        httpSession.setAttribute(Attributes.ROLE, employee.getRole().toString());
        logger.info(LogMessages.USER_SUCCESSFUL_LOGIN + employee.getEmail());
        return getRedirectCommand(employee.getRole());
    }

    /**
     * Extract redirect command depending of user role or index page
     *
     * @param role {@link ua.training.model.entity.Employee.ROLE}
     * @return redirect to command name or index page
     */
    private String getRedirectCommand(Employee.ROLE role) {
        if (role.equals(Employee.ROLE.ADMIN)) {
            return NameCommands.REDIRECT.concat(NameCommands.ADMIN_PAGE);
        }
        if (role.equals(Employee.ROLE.DRIVER)) {
            return NameCommands.REDIRECT.concat(NameCommands.DRIVER_PAGE);
        }
        return Pages.INDEX_PAGE;
    }
}
