package ua.training.model.service.impl;

import org.apache.log4j.Logger;
import ua.training.constant.LogMessages;
import ua.training.constant.Messages;
import ua.training.exeptions.EntityAlreadyHandledException;
import ua.training.exeptions.ServiceException;
import ua.training.model.dao.BusDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.EmployeeDao;
import ua.training.model.dao.TripDao;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;
import ua.training.model.entity.Trip;
import ua.training.model.service.TripService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TripServiceImpl implements TripService {
    private final static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Override
    public List<Trip> getTripsAndRoutes(int offset, int limit) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection)) {
            return tripDao.findTripsWithRoutes(offset, limit);
        }
    }

    @Override
    public int getNumberOfRecords() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection)) {
            return tripDao.getNumberOfRecords();
        }
    }

    @Override
    public void setBusOnTrip(int tripId, int busId) throws ServiceException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection);
             BusDao busDao = DaoFactory.getInstance().createBusDao(connection)) {
            connection.setAutoCommit(false);
            Optional<Bus> busOptional = busDao.findById(busId);
            Optional<Trip> tripOptional = tripDao.findById(tripId);

            if (busOptional.isPresent() && tripOptional.isPresent()
                    && isBusUpdateAllowed(tripOptional.get(), busOptional.get())) {
                Trip trip = tripOptional.get();
                Bus bus = busOptional.get();
                bus.setUsed(true);
                trip.setBus(bus);
                tripDao.update(trip);
                busDao.update(bus);
            } else {
                throw new EntityAlreadyHandledException(Messages.BUS_ALREADY_USED, busId);
            }
            connection.commit();
        } catch (SQLException | EntityAlreadyHandledException e) {
            rollbackAndThrow(connection, e);
        }
    }

    private boolean isBusUpdateAllowed(Trip trip, Bus bus) {
        return (trip.getBus().getId() == 0) && !bus.isUsed();
    }

    @Override
    public void setDriverOnTrip(int tripId, int driverId) throws ServiceException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection);
             EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(connection)) {
            connection.setAutoCommit(false);
            Optional<Employee> employeeOptional = employeeDao.findById(driverId);
            Optional<Trip> tripOptional = tripDao.findById(tripId);

            Optional<Driver> driverOptional = employeeOptional.map(employee -> (Driver) employee);

            if (driverOptional.isPresent() && tripOptional.isPresent()
                    && isDriverUpdateAllowed(tripOptional.get(), driverOptional.get())) {
                Trip trip = tripOptional.get();
                Driver driver = driverOptional.get();
                driver.setAssigned(true);
                trip.setDriver(driver);
                tripDao.update(trip);
                employeeDao.update(driver);
            } else {
                throw new EntityAlreadyHandledException(Messages.DRIVER_ALREADY_USED, driverId);
            }
            connection.commit();
        } catch (SQLException | EntityAlreadyHandledException e) {
            rollbackAndThrow(connection, e);
        }
    }

    private boolean isDriverUpdateAllowed(Trip trip, Driver driver) {
        return (trip.getDriver().getId() == 0) && !driver.isAssigned();
    }

    private void rollbackAndThrow(Connection connection, Exception e) throws ServiceException {
        try {
            connection.rollback();
            throw new ServiceException(Messages.TRANSACTION_IS_NOT_COMPLETED, e);
        } catch (SQLException e1) {
            throw new ServiceException(Messages.TRANSACTION_IS_NOT_COMPLETED, e);
        }
    }

    @Override
    public void deleteBusFromTrip(int tripId) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection);
             BusDao busDao = DaoFactory.getInstance().createBusDao(connection)) {
            connection.setAutoCommit(false);
            Optional<Trip> tripOptional = tripDao.findById(tripId);

            if (tripOptional.isPresent()) {
                Trip trip = tripOptional.get();
                int busId = trip.getBus().getId();
                trip.getBus().setId(0);
                tripDao.update(trip);

                Optional<Bus> busOptional = busDao.findById(busId);
                if (busOptional.isPresent()) {
                    Bus bus = busOptional.get();
                    bus.setUsed(false);
                    busDao.update(bus);
                }
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

    @Override
    public void deleteDriverFromTrip(int tripId) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection);
             EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(connection)) {
            connection.setAutoCommit(false);
            Optional<Trip> tripOptional = tripDao.findById(tripId);

            if (tripOptional.isPresent()) {
                Trip trip = tripOptional.get();
                int driverId = trip.getDriver().getId();
                trip.getDriver().setId(0);
                trip.setConfirmation(false);
                tripDao.update(trip);

                Optional<Employee> employeeOptional = employeeDao.findById(driverId);
                Optional<Driver> driverOptional = employeeOptional.map(employee -> (Driver) employee);
                if (driverOptional.isPresent()) {
                    Driver driver = driverOptional.get();
                    driver.setAssigned(false);
                    employeeDao.update(driver);
                }
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
