package ua.training.model.dao;

import ua.training.model.dao.impl.JDBCDaoFactory;

import java.sql.Connection;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract EmployeeDao createUserDao(Connection connection);

    public abstract RouteDao createRouteDao(Connection connection);

    public abstract TripDao createTripDao(Connection connection);

    public abstract BusDao createBusDao(Connection connection);

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
