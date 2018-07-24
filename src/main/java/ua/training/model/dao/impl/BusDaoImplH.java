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
        Query<Bus> tripQuery = session.createQuery("from Bus bus join fetch bus.drivers", Bus.class);
        return tripQuery.getResultList();
    }

    @Override
    public Map<Bus, Route> findAllBusesWithRoutes() {
/*        Query<Bus> tripQuery = session.createQuery("from Bus", Bus.class);
        List<Bus> buses = tripQuery.getResultList();
        Map<Bus, Route> map = new HashMap<>();
        for (Bus bus: buses) {
            if(bus.getTrips().isEmpty()) {
                map.put(bus, new Route());
            } else {
                for (Trip trip : bus.getTrips()) {
                    map.put(bus, trip.getRoute());
                }
            }
        }*/
        return new HashMap<>();
    }

    @Override
    public void addBusesHasDriverRelation(List<Bus> buses, int driverId) {

    }

    @Override
    public Optional<Bus> findById(Integer integer) {
        return null;
    }

    @Override
    public List<Bus> findAll() {
        Query<Bus> tripQuery = session.createQuery("from Bus", Bus.class);
        return tripQuery.getResultList();
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
