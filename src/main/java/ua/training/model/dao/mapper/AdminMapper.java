package ua.training.model.dao.mapper;

import ua.training.constant.Attributes;
import ua.training.model.entity.Admin;
import ua.training.model.entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AdminMapper implements EntityMapper<Admin>{
    @Override
    public Admin extractFromResultSet(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        admin.setId(resultSet.getInt(Attributes.USER_ID));
        admin.setFirstName(resultSet.getString(Attributes.FIRST_NAME));
        admin.setLastName(resultSet.getString(Attributes.LAST_NAME));
        admin.setEmail(resultSet.getString(Attributes.EMAIL));
        admin.setPassword(resultSet.getString(Attributes.PASSWORD));
        admin.setPhoneNumber(resultSet.getString(Attributes.PHONE_NUMBER));
        admin.setRole(Employee.ROLE.ADMIN);
        admin.setPassportNumber(resultSet.getString(Attributes.PASSPORT_NUMBER));
        admin.setPassportRegistration(resultSet.getString(Attributes.PASSPORT_REGISTRATION));
        return admin;
    }

    @Override
    public void setParameters(Admin entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getEmail());
        statement.setString(4, entity.getPassword());
        statement.setString(5, entity.getPhoneNumber());
        statement.setString(6, entity.getRole().name());
        statement.setNull(7, java.sql.Types.NULL);
        statement.setNull(8, java.sql.Types.NULL);
        statement.setString(9, entity.getPassportNumber());
        statement.setString(10, entity.getPassportRegistration());
        statement.setNull(11, java.sql.Types.NULL);
        statement.setNull(12, java.sql.Types.NULL);
        statement.setInt(13, entity.getId());
    }

    @Override
    public Admin makeUnique(Map<Integer, Admin> cache, Admin admin) {
        cache.putIfAbsent(admin.getId(), admin);
        return cache.get(admin.getId());
    }
}
