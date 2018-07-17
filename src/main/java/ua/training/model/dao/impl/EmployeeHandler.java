package ua.training.model.dao.impl;

import ua.training.constant.Messages;
import ua.training.model.dao.AbstractEmployeeHandler;
import ua.training.model.dao.mapper.AdminMapper;
import ua.training.model.dao.mapper.DriverMapper;
import ua.training.model.entity.Admin;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;
import ua.training.util.LocaleManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for choosing appropriate mapper depending on employee role
 *
 * @author Zabudskyi Oleksandr
 * @see AbstractEmployeeHandler
 */
public class EmployeeHandler implements AbstractEmployeeHandler {

    @Override
    public Employee extractFromResultSet(Employee.ROLE role, ResultSet resultSet) throws SQLException {
        switch (role) {
            case ADMIN:
                return new AdminMapper().extractFromResultSet(resultSet);
            case DRIVER:
                return new DriverMapper().extractFromResultSet(resultSet);
            default:
                throw new IllegalArgumentException(LocaleManager.getProperty(Messages.INVALID_ROLE) + role);
        }
    }

    @Override
    public void setParameters(Employee employee, PreparedStatement statement) throws SQLException {
        switch (employee.getRole()) {
            case ADMIN:
                new AdminMapper().setParameters((Admin) employee, statement);
                return;
            case DRIVER:
                new DriverMapper().setParameters((Driver) employee, statement);
                return;
            default:
                throw new IllegalArgumentException(LocaleManager.getProperty(Messages.INVALID_ROLE) + employee.getRole());
        }
    }
}
