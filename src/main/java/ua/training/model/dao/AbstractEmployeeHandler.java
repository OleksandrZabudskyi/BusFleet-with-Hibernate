package ua.training.model.dao;

import ua.training.model.entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contract for extracting and setting parameters for specific employee
 *
 * @author Zabudskyi Oleksandr
 * @see Employee
 */
public interface AbstractEmployeeHandler {

    /**
     * Extracting employee successor from result set depending on role
     *
     * @param role      employee role
     * @param resultSet result set
     * @return concrete employee successor
     * @throws SQLException exception from database
     */
    Employee extractFromResultSet(Employee.ROLE role, ResultSet resultSet) throws SQLException;

    /**
     * Set prepared statement parameters for specific employee successor
     *
     * @param employee  employee successor
     * @param statement
     * @throws SQLException exception from database
     */
    void setParameters(Employee employee, PreparedStatement statement) throws SQLException;
}
