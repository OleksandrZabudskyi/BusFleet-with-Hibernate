package ua.training.model.dao.mapper;

import ua.training.constant.Attributes;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DriverMapper implements EntityMapper<Driver> {
    @Override
    public Driver extractFromResultSet(ResultSet resultSet) throws SQLException {
        Driver driver = new Driver();
        driver.setId(resultSet.getInt(Attributes.USER_ID));
        driver.setFirstName(resultSet.getString(Attributes.FIRST_NAME));
        driver.setLastName(resultSet.getString(Attributes.LAST_NAME));
        driver.setEmail(resultSet.getString(Attributes.EMAIL));
        driver.setPassword(resultSet.getString(Attributes.PASSWORD));
        driver.setPhoneNumber(resultSet.getString(Attributes.PHONE_NUMBER));
        driver.setRole(Employee.ROLE.DRIVER);
        driver.setDrivingLicenceNumber(resultSet.getString(Attributes.DRIVER_LICENCE_NUMBER));
        driver.setDrivingExperience(resultSet.getInt(Attributes.DRIVING_EXPERIENCE));
        driver.setAssigned(resultSet.getBoolean(Attributes.ASSIGNED));
        driver.setRegistered(resultSet.getBoolean(Attributes.REGISTERED));
        return driver;
    }

    @Override
    public void setParameters(Driver entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getEmail());
        statement.setString(4, entity.getPassword());
        statement.setString(5, entity.getPhoneNumber());
        statement.setString(6, entity.getRole().name());
        statement.setString(7, entity.getDrivingLicenceNumber());
        statement.setInt(8, entity.getDrivingExperience());
        statement.setNull(9, java.sql.Types.NULL);
        statement.setNull(10, java.sql.Types.NULL);
        statement.setBoolean(11, entity.isAssigned());
        statement.setBoolean(12, entity.isRegistered());
        statement.setInt(13, entity.getId());
    }

    public Driver makeUnique(Map<Integer, Driver> cache, Driver driver) {
        cache.putIfAbsent(driver.getId(), driver);
        return cache.get(driver.getId());
    }
}
