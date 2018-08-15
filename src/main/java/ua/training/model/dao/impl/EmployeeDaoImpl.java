package ua.training.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.training.model.dao.EmployeeDao;
import ua.training.model.entity.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public Optional<Employee> findByEmail(String email) {
        Session session = HibernateConfig.getCurrentSession();
        Query<Employee> tripQuery = session.createQuery("from Employee where email = :email", Employee.class)
                .setParameter("email", email);
        return Optional.of(tripQuery.getSingleResult());
    }

    @Override
    public List<Employee> findAll() {
        Session session = HibernateConfig.getCurrentSession();
        Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
        return query.getResultList();
    }
}
