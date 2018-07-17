package ua.training.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import ua.training.util.PropertyLoader;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static ua.training.constant.Config.*;

public class ConnectionPoolHolder {
    private final static Logger logger = Logger.getLogger(ConnectionPoolHolder.class);
    private static volatile DataSource dataSource;

    private static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    Properties properties = PropertyLoader.load(DB_CONFIG);
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(properties.getProperty(DB_URL));
                    ds.setUsername(properties.getProperty(DB_USER));
                    ds.setPassword(properties.getProperty(DB_PASSWORD));
                    ds.setMinIdle(Integer.parseInt(properties.getProperty(DB_MIN_IDLE)));
                    ds.setMaxIdle(Integer.parseInt(properties.getProperty(DB_MAX_IDLE)));
                    ds.setMaxOpenPreparedStatements(Integer.parseInt(properties.getProperty(DB_MAX_OPEN_PS)));
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException();
        }
    }

}
