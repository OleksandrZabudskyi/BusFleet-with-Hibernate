package ua.training.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.EmployeeDao;
import ua.training.model.entity.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeDaoImplH implements EmployeeDao {
    private Session session;

    public EmployeeDaoImplH(Session session) {
        this.session = session;
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        Query<Employee> tripQuery = session.createQuery("from Employee where email = :email", Employee.class)
                .setParameter("email", email);
        return Optional.of(tripQuery.getSingleResult());
    }

    @Override
    public Optional<Employee> findById(Integer integer) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        Query<Employee> tripQuery = session.createQuery("from Employee ", Employee.class);
        return tripQuery.getResultList();
    }

    @Override
    public void create(Employee entity){
        session.persist(entity);
    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void close() {

    }
}
