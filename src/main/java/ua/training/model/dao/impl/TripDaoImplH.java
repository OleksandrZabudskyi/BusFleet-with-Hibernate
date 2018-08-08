package ua.training.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.training.model.dao.TripDao;
import ua.training.model.entity.Trip;

import java.util.List;
import java.util.Optional;

public class TripDaoImplH implements TripDao {
    private Session session;

    public TripDaoImplH(Session session) {
        this.session = session;
    }

    @Override
    public List<Trip> findTripsWithRoutes(int offset, int limit) {
        Query<Trip> tripQuery = session.createQuery("FROM Trip", Trip.class);
        tripQuery.setFirstResult(offset);
        tripQuery.setMaxResults(limit);
        return tripQuery.getResultList();
    }

    @Override
    public int getNumberOfRecords() {
        Query countQuery = session.createQuery("SELECT COUNT(*) AS rowsNumber from Trip");
        return ((Long) countQuery.getSingleResult()).intValue();
    }

    @Override
    public List<Trip> findTripsWithDetailsByDriverId(int driverId) {
        Query<Trip> query = session.createQuery("FROM Trip WHERE driver.id = :driverId", Trip.class);
        query.setParameter("driverId", driverId);
        return query.getResultList();
    }

    @Override
    public Optional<Trip> findById(Integer integer) {
        Query<Trip> query = session.createQuery("FROM Trip WHERE id = :id", Trip.class);
        query.setParameter("id", integer);
        return Optional.of(query.getSingleResult());
    }

    @Override
    public List<Trip> findAll() {
        Query<Trip> tripQuery = session.createQuery("FROM Trip", Trip.class);
        return tripQuery.getResultList();
    }

    @Override
    public void create(Trip entity) {

    }

    @Override
    public void update(Trip entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void close() {

    }
}
