package ua.training.model.dao.impl;

import com.mysql.jdbc.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.MySQL5Dialect;
import ua.training.model.dao.RoleConverter;
import ua.training.model.entity.*;
import ua.training.util.PropertyLoader;

import java.util.Properties;

import static ua.training.constant.Config.*;

public class HibernateConfig {
    private static volatile SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (sessionFactory == null) {
                    Properties properties = PropertyLoader.load(DB_CONFIG);
                    Configuration config = new Configuration()
                            .addAnnotatedClass(Admin.class)
                            .addAnnotatedClass(Bus.class)
                            .addAnnotatedClass(ua.training.model.entity.Driver.class)
                            .addAnnotatedClass(Employee.class)
                            .addAnnotatedClass(Route.class)
                            .addAnnotatedClass(Trip.class)
                            .setProperty(Environment.DRIVER, Driver.class.getCanonicalName())
                            .setProperty(Environment.DIALECT, MySQL5Dialect.class.getCanonicalName())
                            .setProperty(Environment.URL, properties.getProperty(DB_URL))
                            .setProperty(Environment.USER, properties.getProperty(DB_USER))
                            .setProperty(Environment.PASS, properties.getProperty(DB_PASSWORD))
                            .setProperty(Environment.HBM2DDL_AUTO, "validate")
                            .setProperty(Environment.SHOW_SQL, "true");
                    config.addAttributeConverter(RoleConverter.class);
                    sessionFactory = config.buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }

}
