package ua.training.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.training.model.dao.TripDao;
import ua.training.model.entity.Trip;

import java.util.List;

public class TripDaoImpl implements TripDao {

    @Override
    public List<Trip> findTripsWithRoutes(int offset, int limit) {
        Session session = HibernateConfig.getCurrentSession();
        Query<Trip> tripQuery = session.createQuery("FROM Trip", Trip.class);
        tripQuery.setFirstResult(offset);
        tripQuery.setMaxResults(limit);
        return tripQuery.getResultList();
    }

    @Override
    public int getNumberOfRecords() {
        Session session = HibernateConfig.getCurrentSession();
        Query countQuery = session.createQuery("SELECT COUNT(*) AS rowsNumber from Trip");
        return ((Long) countQuery.getSingleResult()).intValue();
    }

    @Override
    public List<Trip> findTripsWithDetailsByDriverId(int driverId) {
        Session session = HibernateConfig.getCurrentSession();
        Query<Trip> query = session.createQuery("FROM Trip WHERE driver.id = :driverId", Trip.class);
        query.setParameter("driverId", driverId);
        return query.getResultList();
    }
}
