package ua.training.model.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.training.constant.LogMessages;
import ua.training.constant.Messages;
import ua.training.exeptions.EntityAlreadyHandledException;
import ua.training.exeptions.ServiceException;
import ua.training.model.dao.BusDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TripDao;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.dao.impl.HibernateConfig;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;
import ua.training.model.entity.Trip;
import ua.training.model.service.TripService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TripServiceImpl implements TripService {
    private final static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Override
    public List<Trip> getTripsAndRoutes(int offset, int limit) {
        try (Session session = HibernateConfig.getSession();
             TripDao tripDao = DaoFactory.getInstance().createTripDao(session)) {
            return tripDao.findTripsWithRoutes(offset, limit);
        }
    }

    @Override
    public int getNumberOfRecords() {
        try (Session session = HibernateConfig.getSession();
             TripDao tripDao = DaoFactory.getInstance().createTripDao(session)) {
            return tripDao.getNumberOfRecords();
        }
    }

    @Override
    public void setBusOnTrip(int tripId, int busId) throws ServiceException {
        try (Session session = HibernateConfig.getSession();
             TripDao tripDao = DaoFactory.getInstance().createTripDao(session);
             BusDao busDao = DaoFactory.getInstance().createBusDao(session)) {
            Transaction transaction = session.beginTransaction();
            Optional<Bus> busOptional = busDao.findById(busId);
            Optional<Trip> tripOptional = tripDao.findById(tripId);

            if (busOptional.isPresent() && tripOptional.isPresent()
                    && isBusUpdateAllowed(tripOptional.get(), busOptional.get())) {
                Trip trip = tripOptional.get();
                Bus bus = busOptional.get();
                bus.setUsed(true);
                trip.setBus(bus);
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

                Bus bus = session.get(Bus.class, busId);
                if (Objects.nonNull(bus)) {
                    bus.setUsed(false);
                }
            }
            transaction.commit();
        }
    }

    @Override
    public void deleteDriverFromTrip(int tripId) {
        try (Session session = HibernateConfig.getSession()){
            Transaction transaction = session.beginTransaction();
            Trip trip = session.get(Trip.class, tripId);

            if(Objects.nonNull(trip)) {
                int driverId = trip.getDriver().getId();
                trip.setDriver(null);
                trip.setConfirmation(false);

                Driver driver = (Driver) session.get(Employee.class, driverId);
                if(Objects.nonNull(driver)) {
                    driver.setAssigned(false);
                }
            }
            transaction.commit();
        }
    }

    @Override
    public List<Trip> getAppointmentTripsToDrivers(Employee employee) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection)) {
            return tripDao.findTripsWithDetailsByDriverId(employee.getId());
        }
    }

    @Override
    public void setTripConfirmation(int tripId) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection)) {
            connection.setAutoCommit(false);
            Optional<Trip> tripOptional = tripDao.findById(tripId);
            if (tripOptional.isPresent()) {
                Trip trip = tripOptional.get();
                trip.setConfirmation(true);
                tripDao.update(trip);
            }
            connection.commit();
        } catch (SQLException e) {
            logger.error(LogMessages.TRANSACTION_ERROR, e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(LogMessages.ROLLBACK_ERROR, e1);
            }
        }
    }
}
