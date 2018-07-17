package ua.training.model.dao.impl;

import ua.training.model.dao.*;
import ua.training.model.dao.mapper.BusMapper;
import ua.training.model.dao.mapper.DriverMapper;
import ua.training.model.dao.mapper.RouteMapper;
import ua.training.model.dao.mapper.TripMapper;

import java.sql.Connection;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public EmployeeDao createUserDao(Connection connection) {
        return new EmployeeDaoImpl(connection, new EmployeeHandler());
    }

    @Override
    public RouteDao createRouteDao(Connection connection) {
        return new RouteDaoImpl(connection, new RouteMapper());
    }

    @Override
    public TripDao createTripDao(Connection connection) {
        return new TripDaoImpl.TripDaoImplBuilder()
                .setConnection(connection)
                .setBusMapper(new BusMapper())
                .setDriverMapper(new DriverMapper())
                .setRouteMapper(new RouteMapper())
                .setTripMapper(new TripMapper())
                .createTripDaoImpl();
    }

    @Override
    public BusDao createBusDao(Connection connection) {
        return new BusDaoImpl.BusDaoImplBuilder()
                .setConnection(connection)
                .setBusMapper(new BusMapper())
                .setDriverMapper(new DriverMapper())
                .setRouteMapper(new RouteMapper())
                .createBusDaoImpl();
    }
}
