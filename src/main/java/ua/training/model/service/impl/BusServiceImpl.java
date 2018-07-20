package ua.training.model.service.impl;

import org.hibernate.Session;
import ua.training.model.dao.BusDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.dao.impl.HibernateConfig;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Route;
import ua.training.model.service.BusService;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class BusServiceImpl implements BusService {

    @Override
    public Map<Bus, Route> getAllBusesWithRoutes() {
        try(Session session = HibernateConfig.getSession()) {

        }
        Connection connection = ConnectionPoolHolder.getConnection();
        try (BusDao busDao = DaoFactory.getInstance().createBusDao(connection)) {
            return busDao.findAllBusesWithRoutes();
        }
    }

    @Override
    public List<Bus> getAllBusesToAllDrivers() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (BusDao busDao = DaoFactory.getInstance().createBusDao(connection)) {
            return busDao.findAllBusesWithDrivers();
        }
    }
}
