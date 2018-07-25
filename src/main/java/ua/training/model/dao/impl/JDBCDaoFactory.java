package ua.training.model.dao.impl;

import org.hibernate.Session;
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
    public EmployeeDao createUserDao(Session session) {
        return new EmployeeDaoImplH(session);
    }

    @Override
    public RouteDao createRouteDao(Connection connection) {
        return new RouteDaoImpl(connection, new RouteMapper());
    }

    @Override
    public RouteDao createRouteDao(Session session) {
        return new RouteDaoImplH(session);
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
    public TripDao createTripDao(Session session) {
        return new TripDaoImplH(session);
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

    @Override
    public BusDao createBusDao(Session session) {
        return new BusDaoImplH(session);
    }
}
