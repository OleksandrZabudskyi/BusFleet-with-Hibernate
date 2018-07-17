package ua.training.constant;

/**
 * Database parameters
 *
 * @author Zabudskyi Oleksandr
 */
public interface Config {
    String DB_CONFIG = "application.properties";
    String DB_URL = "datasource.url";
    String DB_USER = "datasource.user";
    String DB_PASSWORD = "datasource.password";
    String DB_MIN_IDLE = "datasource.min.idle";
    String DB_MAX_IDLE = "datasource.max.idle";
    String DB_MAX_OPEN_PS = "datasource.max.open.prepare.statements";
}
