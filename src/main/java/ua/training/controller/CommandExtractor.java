package ua.training.controller;

import ua.training.constant.GlobalConstants;
import ua.training.constant.NameCommands;
import ua.training.constant.Regex;
import ua.training.controller.command.*;
import ua.training.controller.command.LoginCommand;
import ua.training.controller.command.LogoutCommand;
import ua.training.controller.command.admin.*;
import ua.training.controller.command.driver.AppointmentCommand;
import ua.training.controller.command.driver.ConfirmTripCommand;
import ua.training.controller.command.driver.DriverRegistrationCommand;
import ua.training.controller.command.redirect.AdminPageCommand;
import ua.training.controller.command.redirect.DriverPageCommand;
import ua.training.controller.command.redirect.LoginPageCommand;
import ua.training.controller.command.redirect.RegistrationPageCommand;
import ua.training.controller.util.ParametersValidator;
import ua.training.model.service.impl.BusServiceImpl;
import ua.training.model.service.impl.EmployeeServiceImpl;
import ua.training.model.service.impl.SecurityServiceImpl;
import ua.training.model.service.impl.TripServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *  Class is used to get appropriate command {@link Command}
 *  by the string presentation nameCommand {@link NameCommands} value.
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see NameCommands
 */
public class CommandExtractor {
    private static CommandExtractor instance;
    private Map<String, Command> commands = new HashMap<>();

    private CommandExtractor() {
        commands.put(NameCommands.INDEX_PAGE, new IndexPageCommand());
        commands.put(NameCommands.LOGIN_PAGE, new LoginPageCommand());
        commands.put(NameCommands.REGISTRATION_PAGE, new RegistrationPageCommand());
        commands.put(NameCommands.ADMIN_PAGE, new AdminPageCommand());
        commands.put(NameCommands.DRIVER_PAGE, new DriverPageCommand());
        commands.put(NameCommands.LOGIN, new LoginCommand(new EmployeeServiceImpl(), new SecurityServiceImpl(),
                new ParametersValidator()));
        commands.put(NameCommands.REGISTRATION, new DriverRegistrationCommand(new EmployeeServiceImpl(),
                new SecurityServiceImpl(), new ParametersValidator()));
        commands.put(NameCommands.LOGOUT, new LogoutCommand());
        commands.put(NameCommands.LANGUAGE, new LanguageCommand());
        commands.put(NameCommands.ALL_TRIPS, new TripsAndRoutesCommand(new TripServiceImpl()));
        commands.put(NameCommands.ALL_BUSES, new AllBusesCommand(new BusServiceImpl()));
        commands.put(NameCommands.SET_BUS, new SetBusCommand(new TripServiceImpl(), new ParametersValidator()));
        commands.put(NameCommands.SET_DRIVER, new SetDriverCommand(new TripServiceImpl(), new ParametersValidator()));
        commands.put(NameCommands.DELETE_BUS, new DeleteBusCommand(new TripServiceImpl()));
        commands.put(NameCommands.DELETE_DRIVER, new DeleteDriverCommand(new TripServiceImpl()));
        commands.put(NameCommands.ALL_DRIVERS, new AllDriversCommand(new EmployeeServiceImpl()));
        commands.put(NameCommands.APPOINTMENT, new AppointmentCommand(new TripServiceImpl()));
        commands.put(NameCommands.CONFIRM_APPOINTMENT, new ConfirmTripCommand(new TripServiceImpl()));
        commands.put(NameCommands.BUSES_WITH_DRIVERS, new BusesWithDriversCommand(new BusServiceImpl()));
        commands.put(NameCommands.SET_BUS_WITH_DRIVER, new SetBusWithDriverCommand(new TripServiceImpl(),
                new ParametersValidator()));
        commands.put(NameCommands.USER_INFO, new ProfileCommand());
        commands.put(NameCommands.CONTACTS_PAGE, new ContactsPageCommand());


    }

    /**
     * Returns command {@link Command} by the specified string value
     *
     * @param request value that corresponds to appropriate command
     * @return command by the specified string value
     */
    public Command getCommand(HttpServletRequest request) {
        String path = request.getRequestURI();
        path = path.replaceAll(Regex.URL, GlobalConstants.EMPTY_SIGN);
        return commands.getOrDefault(path, new IndexPageCommand());
    }

    public synchronized static CommandExtractor getInstance() {
        if (instance == null) {
            instance = new CommandExtractor();
        }
        return instance;
    }
}

