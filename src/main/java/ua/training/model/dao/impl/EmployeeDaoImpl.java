package ua.training.model.dao.impl;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.LogMessages;
import ua.training.constant.Messages;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.AbstractEmployeeHandler;
import ua.training.model.entity.Employee;
import ua.training.model.dao.EmployeeDao;
import ua.training.model.dao.util.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {
    private final static Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
    private final Connection connection;
    private final AbstractEmployeeHandler employeeHandler;

    public EmployeeDaoImpl(Connection connection, AbstractEmployeeHandler employeeHandler) {
        this.connection = connection;
        this.employeeHandler = employeeHandler;
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        Optional<Employee> employee = Optional.empty();
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.FIND_USER_BY_EMAIL)) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                employee = Optional.ofNullable(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error(LogMessages.NO_RESULT_FROM_DB, e);
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        Optional<Employee> employee = Optional.empty();
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.FIND_USER_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                employee = Optional.ofNullable(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error(LogMessages.NO_RESULT_FROM_DB, e);
            throw new RuntimeException(e);
        }
        return employee;
    }


    @Override
    public List<Employee> findAll() {
        List<Employee> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_ALL_USERS);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(getUserFromResultSet(resultSet));
            }
            return resultList;
        } catch (SQLException e) {
            logger.error(LogMessages.NO_RESULT_FROM_DB, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Employee entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_USER)) {
            employeeHandler.setParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            try {
                throw new EntityAlreadyExistException(Messages.USER_ALREADY_EXIST, e, entity.getEmail());
            } catch (EntityAlreadyExistException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e) {
            logger.error(LogMessages.CREATE_ENTITY_ERROR, e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Employee entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_USER_BY_ID)) {
            employeeHandler.setParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessages.UPDATE_ENTITY_ERROR, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.DELETE_USER_BY_ID)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessages.DELETE_ENTITY_ERROR, e);
            throw new RuntimeException(e);
        }
    }

    private Employee getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Employee.ROLE role = Employee.ROLE.valueOf(resultSet.getString(Attributes.ROLE));
        return employeeHandler.extractFromResultSet(role, resultSet);
    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(LogMessages.CONNECTION_CLOSE_ERROR, e);
            throw new RuntimeException(e);
        }
    }
}
