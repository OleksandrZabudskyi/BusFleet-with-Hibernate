package ua.training.model.service;

import ua.training.exeptions.ServiceException;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Provide service layer to manipulate with data from employee dao
 *
 * @author Zabudskyi Oleksandr
 */
public interface EmployeeService {

    /**
     * Find employee by email
     *
     * @param email email
     * @return Optional<Employee> or Optional.empty()
     */
    Optional<Employee> findEmployeeByEmail(String email);

    /**
     * Register driver and update relations to buses
     *
     * @param driver driver
     * @throws ServiceException exception
     */
    void registerDriver(Driver driver) throws ServiceException;

    /**
     *  Get all drivers
     *
     * @return list with Driver or empty list
     */
    List<Driver> getAllDrivers();


}
