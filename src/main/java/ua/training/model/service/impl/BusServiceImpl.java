package ua.training.model.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.training.model.dao.BusDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.HibernateConfig;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Route;
import ua.training.model.service.BusService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusServiceImpl implements BusService {

    @Override
    public Map<Bus, Route> getAllBusesWithRoutes() {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            BusDao busDao = DaoFactory.getInstance().createBusDao();
            List<Bus> buses = busDao.findAll();
            final Map<Bus, Route> map = new HashMap<>();
            for (final Bus bus : buses) {
                if (bus.getTrips().isEmpty()) {
                    map.put(bus, new Route());
                } else {
                    bus.getTrips().forEach((k) -> map.put(bus, k.getRoute()));
                }
            }
            transaction.commit();
            return map;
        }
    }

    @Override
    public List<Bus> getAllBusesToAllDrivers() {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            BusDao busDao = DaoFactory.getInstance().createBusDao();
            List<Bus> buses = busDao.findAllBusesWithDrivers();
            transaction.commit();
            return buses;
        }
    }
}
