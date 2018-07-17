package ua.training.constant;

/**
 *  Regex pattern
 *
 * @author Zabudskyi Oleksandr
 */
public interface Regex {
    String REGEX_EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    String PHONE = "^\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";
    String URL = "(.*\\/app\\/)|(\\/)|(bus-fleet\\/)|(admin\\/)|(driver\\/)";
    String POSITIVE_NUMBER = "^[1-9]\\d*$";
    String NAME = "^[A-ZА-ЩЮЯҐІЇЄ][a-zA-ZА-ЩЬЮЯҐІЇЄа-щьюяґіїєʼ]*$";
    String PASSWORD = "[-0-9A-Za-z]{5,16}";
    String LICENCE_NUMBER = "[a-zA-ZА-ЩЬЮЯҐІЇЄа-щьюяґіїє0-9]{1,10}";
}
