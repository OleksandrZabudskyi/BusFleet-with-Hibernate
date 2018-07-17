package ua.training.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Provide loader for property file
 *
 * @author Zabudskyi Oleksandr
 */
public class PropertyLoader {
    private static Logger logger = Logger.getLogger(PropertyLoader.class);

    public static Properties load(String name) {
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            inputStream = classLoader.getResourceAsStream(name);
            properties.load(inputStream);
        } catch (IOException e) {
            logger.debug(e);
        }
        return properties;
    }
}
