package ua.training.model.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class EmployeeDaoImplTest {
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resultSet;
    private Driver driver;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        driver = new Driver.DriverBuilder()
                .setFirstName("Dmitriy")
                .setLastName("Kylikovich")
                .setEmail("kylikovich@gmail.com")
                .setPhoneNumber("+38(067)234-34-46")
                .setRole(Employee.ROLE.DRIVER)
                .setDrivingLicenceNumber("CMT2143")
                .setDrivingExperience(12)
                .setPassword("77+977+9SO+/vciT77+977+9N2Z/fnPvv70SQw==,-1758309945")
                .createDriver();
    }

    @Test
    public void testCreateDriver() throws EntityAlreadyExistException, SQLException {
/*        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn(driver.getFirstName());
        when(resultSet.getString(3)).thenReturn(driver.getLastName());
        when(resultSet.getString(4)).thenReturn(driver.getEmail());
        when(resultSet.getString(5)).thenReturn(driver.getPassword());
        when(resultSet.getString(6)).thenReturn(driver.getPhoneNumber());
        when(resultSet.getString(7)).thenReturn(driver.getRole().toString());
        when(resultSet.getString(8)).thenReturn(driver.getDrivingLicenceNumber());
        when(resultSet.getInt(9)).thenReturn(driver.getDrivingExperience());
        when(resultSet.getBoolean(10)).thenReturn(false);
        when(resultSet.getBoolean(10)).thenReturn(true);
        when(statement.executeQuery()).thenReturn(resultSet);
        assertNotNull(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        new EmployeeDaoImpl(connection, abstractEmployeeHandler).create(driver);
        verify(connection).prepareStatement(any(String.class));*/
    }
}