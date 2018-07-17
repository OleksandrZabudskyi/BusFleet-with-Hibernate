package ua.training.model.dao.mapper;

import ua.training.constant.Attributes;
import ua.training.model.entity.Bus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BusMapper implements EntityMapper<Bus> {
    @Override
    public Bus extractFromResultSet(ResultSet resultSet) throws SQLException {
        Bus bus = new Bus();
        bus.setId(resultSet.getInt(Attributes.BUS_ID));
        bus.setModel(resultSet.getString(Attributes.BUS_MODEL));
        bus.setLicensePlate(resultSet.getString(Attributes.LICENCE_PLATE));
        bus.setManufactureYear(resultSet.getInt(Attributes.MANUFACTURE_YEAR));
        bus.setParkingSpot(resultSet.getString(Attributes.PARKING_SPOT));
        bus.setUsed(resultSet.getBoolean(Attributes.USED));
        return bus;
    }

    @Override
    public void setParameters(Bus entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getModel());
        statement.setString(2, entity.getLicensePlate());
        statement.setInt(3, entity.getManufactureYear());
        statement.setString(4, entity.getParkingSpot());
        statement.setBoolean(5, entity.isUsed());
        statement.setInt(6, entity.getId());
    }

    public Bus makeUnique(Map<Integer, Bus> cache, Bus bus) {
        cache.putIfAbsent(bus.getId(), bus);
        return cache.get(bus.getId());
    }
}
