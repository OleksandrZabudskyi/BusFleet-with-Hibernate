package ua.training.model.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.exeptions.ServiceException;
import ua.training.model.dao.BusDao;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.dao.impl.HibernateConfig;
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
        try (Session session = HibernateConfig.getSession();
             EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(session)) {
            return employeeDao.findByEmail(email);
        }
    }

    @Override
    public void registerDriver(Driver driver) {
        try (Session session = HibernateConfig.getSession();
             EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(session);
             BusDao busDao = DaoFactory.getInstance().createBusDao(session)) {
            Transaction transaction = session.beginTransaction();
            driver.setRole(Employee.ROLE.DRIVER);
            employeeDao.create(driver);
            Optional<Employee> savedDriver = employeeDao.findByEmail(driver.getEmail());
            savedDriver.ifPresent(employee -> busDao.addBusesHasDriverRelation(busDao.findAll(), employee.getId()));
            transaction.commit();
        }
    }

    @Override
    public List<Driver> getAllDrivers() {
        try (Session session = HibernateConfig.getSession();
             EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(session)) {
            List<Employee> employees = employeeDao.findAll();
            return employees.stream()
                    .filter(employee -> employee instanceof Driver)
                    .map(employee -> (Driver) employee).collect(Collectors.toList());
        }
    }
}
