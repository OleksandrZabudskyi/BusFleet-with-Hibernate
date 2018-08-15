package ua.training.model.dao.impl;

import ua.training.model.dao.*;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public EmployeeDao createUserDao() {
        return new EmployeeDaoImpl();
    }

    @Override
    public RouteDao createRouteDao() {
        return new RouteDaoImpl();
    }

    @Override
    public TripDao createTripDao() {
        return new TripDaoImpl();
    }

    @Override
    public BusDao createBusDao() {
        return new BusDaoImpl();
    }
}
