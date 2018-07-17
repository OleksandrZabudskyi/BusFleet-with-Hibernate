package ua.training.model.dao.mapper;

import ua.training.constant.Attributes;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Route;
import ua.training.model.entity.Trip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Map;

public class TripMapper implements EntityMapper<Trip>{
    @Override
    public Trip extractFromResultSet(ResultSet resultSet) throws SQLException {
        Trip trip = new Trip();
        trip.setId(resultSet.getInt(Attributes.TRIP_ID));
        trip.setNumber(resultSet.getString(Attributes.TRIP_NUMBER));
        trip.setStartTime(resultSet.getTime(Attributes.TRIP_START_TIME).toLocalTime());
        trip.setEndTime(resultSet.getTime(Attributes.TRIP_END_TIME).toLocalTime());
        Route route = new Route();
        route.setId(resultSet.getInt(Attributes.ROUTE_ID));
        trip.setRoute(route);
        Bus bus = new Bus();
        bus.setId(resultSet.getInt(Attributes.BUS_ID));
        trip.setBus(bus);
        Driver driver = new Driver();
        driver.setId(resultSet.getInt(Attributes.DRIVER_ID));
        trip.setDriver(driver);
        trip.setConfirmation(resultSet.getBoolean(Attributes.CONFIRMATION));
        return trip;
    }

    @Override
    public void setParameters(Trip entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getNumber());
        statement.setTime(2, Time.valueOf(entity.getStartTime()));
        statement.setTime(3, Time.valueOf(entity.getEndTime()));
        statement.setInt(4, entity.getRoute().getId());
        int busId = entity.getBus().getId();
        if (busId == 0) {
            statement.setNull(5, java.sql.Types.NULL);
        } else {
            statement.setInt(5, busId);
        }
        int driverId = entity.getDriver().getId();
        if (driverId == 0) {
            statement.setNull(6, java.sql.Types.NULL);
        } else {
            statement.setInt(6, driverId);
        }
        statement.setBoolean(7, entity.isConfirmation());
        statement.setInt(8, entity.getId());
    }

    @Override
    public Trip makeUnique(Map<Integer, Trip> cache, Trip trip) {
        cache.putIfAbsent(trip.getId(), trip);
        return cache.get(trip.getId());
    }
}
