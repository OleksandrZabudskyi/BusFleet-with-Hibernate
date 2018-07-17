package ua.training.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Provide resource bundle for locales
 *
 * @author Zabudskyi Oleksandr
 */
public class LocaleManager {
    public static final Locale ENGLISH = new Locale("en", "US");
    public static final Locale UKRAINIAN = new Locale("ua", "UA");

    private static final String BUNDLE_NAME = "messages";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, ENGLISH);

    public static void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
