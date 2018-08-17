package ua.training.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.training.model.dao.BusDao;
import ua.training.model.entity.Bus;

import java.util.List;

public class BusDaoImpl implements BusDao {

    @Override
    public List<Bus> findAllBusesWithDrivers() {
        Session session = HibernateConfig.getCurrentSession();
        Query<Bus> query = session.createQuery("FROM Bus bus JOIN FETCH bus.drivers", Bus.class);
        return query.getResultList();
    }

    @Override
    public List<Bus> findAll() {
        Session session = HibernateConfig.getCurrentSession();
        Query<Bus> query = session.createQuery("FROM Bus", Bus.class);
        return query.getResultList();
    }

}
