package ua.training.model.service.impl;

import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.exeptions.ServiceException;
import ua.training.model.dao.BusDao;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.EmployeeDao;
import ua.training.model.service.EmployeeService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(connection)) {
            return employeeDao.findByEmail(email);
        }
    }

    @Override
    public void registerDriver(Driver driver) throws ServiceException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(connection);
             BusDao busDao = DaoFactory.getInstance().createBusDao(connection)) {
            connection.setAutoCommit(false);
            driver.setRole(Employee.ROLE.DRIVER);
            employeeDao.create(driver);
            Optional<Employee> savedDriver = employeeDao.findByEmail(driver.getEmail());
            savedDriver.ifPresent(employee -> busDao.addBusesHasDriverRelation(busDao.findAll(), employee.getId()));
            connection.commit();
        } catch (SQLException | EntityAlreadyExistException e) {
            try {
                connection.rollback();
                throw new ServiceException(e);
            } catch (SQLException e1) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public List<Driver> getAllDrivers() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(connection)) {
            List<Employee> employees = employeeDao.findAll();
            return employees.stream()
                    .filter(employee -> employee instanceof Driver)
                    .map(employee -> (Driver) employee).collect(Collectors.toList());
        }
    }
}
