package ua.training.model.dao.impl;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.LogMessages;
import ua.training.constant.Messages;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.TripDao;
import ua.training.model.dao.mapper.*;
import ua.training.model.dao.util.SQLQueries;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Route;
import ua.training.model.entity.Trip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripDaoImpl implements TripDao {
    private final static Logger logger = Logger.getLogger(TripDaoImpl.class);
    private final Connection connection;
    private final EntityMapper<Trip> tripMapper;
    private final EntityMapper<Route> routeMapper;
    private final EntityMapper<Bus> busMapper;
    private final EntityMapper<ua.training.model.entity.Driver> driverMapper;

    private TripDaoImpl(TripDaoImplBuilder tripDaoImplBuilder) {
        this.connection = tripDaoImplBuilder.connection;
        this.tripMapper = tripDaoImplBuilder.tripMapper;
        this.routeMapper = tripDaoImplBuilder.routeMapper;
        this.busMapper = tripDaoImplBuilder.busMapper;
        this.driverMapper = tripDaoImplBuilder.driverMapper;
    }

    @Override
    public Optional<Trip> findById(Integer id) {
        Optional<Trip> trip = Optional.empty();
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.FIND_TRIP_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                trip = Optional.ofNullable(tripMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error(LogMessages.NO_RESULT_FROM_DB, e);
            throw new RuntimeException(e);
        }
        return trip;
    }

    @Override
    public List<Trip> findAll() {
        List<Trip> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_ALL_TRIPS);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(tripMapper.extractFromResultSet(resultSet));
            }
            return resultList;
        } catch (SQLException e) {
            logger.error(LogMessages.NO_RESULT_FROM_DB, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Trip entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_TRIP)) {
            tripMapper.setParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            try {
                throw new EntityAlreadyExistException(Messages.ENTITY_ALREADY_EXIST, e, entity.getNumber());
            } catch (EntityAlreadyExistException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e) {
            logger.error(LogMessages.CREATE_ENTITY_ERROR, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Trip entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_TRIP_BY_ID)) {
            tripMapper.setParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessages.UPDATE_ENTITY_ERROR, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.DELETE_TRIP_BY_ID)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessages.DELETE_ENTITY_ERROR, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Trip> findTripsWithRoutes(int offset, int limit) {
        List<Trip> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_ALL_TRIPS_WITH_LINKS)) {
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet resultSet = ps.executeQuery();
            collectTripsWithLinks(resultList, resultSet);
            return resultList;
        } catch (SQLException e) {
            logger.error(LogMessages.NO_RESULT_FROM_DB, e);
            throw new RuntimeException(e);
        }
    }

    private void collectTripsWithLinks(List<Trip> resultList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Trip trip = tripMapper.extractFromResultSet(resultSet);
            trip.setRoute(routeMapper.extractFromResultSet(resultSet));
            trip.setBus(busMapper.extractFromResultSet(resultSet));
            trip.setDriver(driverMapper.extractFromResultSet(resultSet));
            resultList.add(trip);
        }
    }

    @Override
    public int getNumberOfRecords() {
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.FIND_ALL_TRIPS_COUNT)) {
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(Attributes.ROWS_NUMBER);
            }
        } catch (SQLException e) {
            logger.error(LogMessages.NO_RESULT_FROM_DB, e);
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Trip> findTripsWithDetailsByDriverId(int driverId) {
        List<Trip> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_TRIPS_WITH_LINKS_BY_ID)) {
            ps.setInt(1, driverId);
            ResultSet resultSet = ps.executeQuery();
            collectTripsWithLinks(resultList, resultSet);
            return resultList;
        } catch (SQLException e) {
            logger.error(LogMessages.NO_RESULT_FROM_DB, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(LogMessages.CONNECTION_CLOSE_ERROR, e);
            throw new RuntimeException(e);
        }
    }

    public static final class TripDaoImplBuilder {

        private Connection connection;
        private EntityMapper<Trip> tripMapper;
        private EntityMapper<Route> routeMapper;
        private EntityMapper<Bus> busMapper;
        private EntityMapper<Driver> driverMapper;

        public TripDaoImplBuilder setConnection(Connection connection) {
            this.connection = connection;
            return this;
        }

        public TripDaoImplBuilder setTripMapper(EntityMapper<Trip> tripMapper) {
            this.tripMapper = tripMapper;
            return this;
        }

        public TripDaoImplBuilder setRouteMapper(EntityMapper<Route> routeMapper) {
            this.routeMapper = routeMapper;
            return this;
        }

        public TripDaoImplBuilder setBusMapper(EntityMapper<Bus> busMapper) {
            this.busMapper = busMapper;
            return this;
        }

        public TripDaoImplBuilder setDriverMapper(EntityMapper<Driver> driverMapper) {
            this.driverMapper = driverMapper;
            return this;
        }
        public TripDaoImpl createTripDaoImpl() {
            return new TripDaoImpl(this);
        }
    }
}
