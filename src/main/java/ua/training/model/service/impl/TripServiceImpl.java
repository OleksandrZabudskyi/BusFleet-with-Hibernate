package ua.training.model.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.training.constant.Messages;
import ua.training.exeptions.EntityAlreadyHandledException;
import ua.training.exeptions.ServiceException;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TripDao;
import ua.training.model.dao.impl.HibernateConfig;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;
import ua.training.model.entity.Trip;
import ua.training.model.service.TripService;

import java.util.List;
import java.util.Objects;

public class TripServiceImpl implements TripService {

    @Override
    public List<Trip> getTripsAndRoutes(int offset, int limit) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            TripDao tripDao = DaoFactory.getInstance().createTripDao();
            List<Trip> tripsWithRoutes = tripDao.findTripsWithRoutes(offset, limit);
            transaction.commit();
            return tripsWithRoutes;
        }
    }

    @Override
    public int getNumberOfRecords() {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            TripDao tripDao = DaoFactory.getInstance().createTripDao();
            transaction.commit();
            return tripDao.getNumberOfRecords();
        }
    }

    @Override
    public void setBusOnTrip(int tripId, int busId) throws ServiceException {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            Trip trip = session.get(Trip.class, tripId);
            Bus bus = session.get(Bus.class, busId);

            if (Objects.nonNull(trip) && isBusUpdateAllowed(trip, bus)) {
                bus.setUsed(true);
                trip.setBus(bus);
                session.update(bus);
                session.update(trip);
            } else {
                transaction.rollback();
                throw new EntityAlreadyHandledException(Messages.BUS_ALREADY_USED, busId);
            }
            transaction.commit();
        } catch (EntityAlreadyHandledException e) {
            throw new ServiceException(Messages.TRANSACTION_IS_NOT_COMPLETED, e);
        }
    }

    private boolean isBusUpdateAllowed(Trip trip, Bus bus) {
        return Objects.isNull(trip.getBus()) && !bus.isUsed();
    }

    @Override
    public void setDriverOnTrip(int tripId, int driverId) throws ServiceException {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            Driver driver = (Driver) session.get(Employee.class, driverId);
            Trip trip = session.get(Trip.class, tripId);

            if (Objects.nonNull(trip) && isDriverUpdateAllowed(trip, driver)) {
                driver.setAssigned(true);
                trip.setDriver(driver);
                session.update(driver);
                session.update(trip);
            } else {
                transaction.rollback();
                throw new EntityAlreadyHandledException(Messages.DRIVER_ALREADY_USED, driverId);
            }
            transaction.commit();
        } catch (EntityAlreadyHandledException e) {
            throw new ServiceException(Messages.TRANSACTION_IS_NOT_COMPLETED, e);
        }
    }

    private boolean isDriverUpdateAllowed(Trip trip, Driver driver) {
        return Objects.isNull(trip.getDriver()) && !driver.isAssigned();
    }

    @Override
    public void deleteBusFromTrip(int tripId) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            Trip trip = session.get(Trip.class, tripId);
            if (Objects.nonNull(trip)) {
                int busId = trip.getBus().getId();
                trip.setBus(null);
                session.update(trip);

                Bus bus = session.get(Bus.class, busId);
                if (Objects.nonNull(bus)) {
                    bus.setUsed(false);
                    session.update(bus);
                }
            }
            transaction.commit();
        }
    }

    @Override
    public void deleteDriverFromTrip(int tripId) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            Trip trip = session.get(Trip.class, tripId);

            if (Objects.nonNull(trip)) {
                int driverId = trip.getDriver().getId();
                trip.setDriver(null);
                trip.setConfirmation(false);
                session.update(trip);

                Driver driver = (Driver) session.get(Employee.class, driverId);
                if (Objects.nonNull(driver)) {
                    driver.setAssigned(false);
                    session.update(driver);
                }
            }
            transaction.commit();
        }
    }

    @Override
    public List<Trip> getAppointmentTripsToDrivers(Employee employee) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            TripDao tripDao = DaoFactory.getInstance().createTripDao();
            List<Trip> tripsWithDetailsByDriverId = tripDao.findTripsWithDetailsByDriverId(employee.getId());
            transaction.commit();
            return tripsWithDetailsByDriverId;
        }
    }

    @Override
    public void setTripConfirmation(int tripId) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            Trip trip = session.get(Trip.class, tripId);
            trip.setConfirmation(true);
            session.update(trip);
            transaction.commit();
        }
    }
}
