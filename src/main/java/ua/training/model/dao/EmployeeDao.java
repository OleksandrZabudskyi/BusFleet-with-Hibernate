package ua.training.model.dao;

import ua.training.model.entity.Employee;

import java.util.Optional;

public interface EmployeeDao extends GenericDao<Employee, Integer> {

    /**
     * Find employee by email
     *
     * @param email email
     * @return Optional<Employee> or Optional.empty()
     */
    Optional<Employee> findByEmail(String email);
}
