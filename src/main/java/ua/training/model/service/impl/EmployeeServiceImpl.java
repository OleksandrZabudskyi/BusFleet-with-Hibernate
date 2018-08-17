package ua.training.model.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.training.model.dao.BusDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.EmployeeDao;
import ua.training.model.dao.impl.HibernateConfig;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;
import ua.training.model.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao();
            Optional<Employee> employee = employeeDao.findByEmail(email);
            transaction.commit();
            return employee;
        }
    }

    @Override
    public void registerDriver(Driver driver) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            BusDao busDao = DaoFactory.getInstance().createBusDao();
            List<Bus> buses = busDao.findAll();
            driver.setBuses(buses);
            driver.setRole(Employee.ROLE.DRIVER);
            session.save(driver);
            transaction.commit();
        }
    }

    @Override
    public List<Driver> getAllDrivers() {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao();
            List<Employee> employees = employeeDao.findAll();
            transaction.commit();
            return employees.stream()
                    .filter(employee -> employee instanceof Driver)
                    .map(employee -> (Driver) employee).collect(Collectors.toList());
        }
    }
}
