package ua.training.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.BusDao;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Route;
import ua.training.model.entity.Trip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BusDaoImplH implements BusDao {
    private Session session;

    public BusDaoImplH(Session session) {
        this.session = session;
    }

    @Override
    public List<Bus> findAllBusesWithDrivers() {
        Query<Bus> query = session.createQuery("FROM Bus bus JOIN FETCH bus.drivers", Bus.class);
        return query.getResultList();
    }

    @Override
    public Map<Bus, Route> findAllBusesWithRoutes() {
        return new HashMap<>();
    }

    @Override
    public void addBusesHasDriverRelation(List<Bus> buses, int driverId) {

    }

    @Override
    public Optional<Bus> findById(Integer integer) {
        Query<Bus> query = session.createQuery("FROM Bus WHERE id = :id", Bus.class);
        query.setParameter("id", integer);
        return Optional.of(query.getSingleResult());
    }

    @Override
    public List<Bus> findAll() {
        Query<Bus> query = session.createQuery("FROM Bus", Bus.class);
        return query.getResultList();
    }

    @Override
    public void create(Bus entity) {

    }

    @Override
    public void update(Bus entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void close() {
        session.close();
    }
}
