package ua.training.constant;

/**
 * Error messages
 *
 * @author Zabudskyi Oleksandr
 */
public interface Messages {
    String WRONG_LOGIN_OR_PASSWORD = "wrong.login.password";
    String PARAMS_ERROR = "params.error";
    String PARAMS_BLANK_FIELD = "params.blank";
    String WRONG_PARAMS = "wrong.parameters";
    String WRONG_PARAMS_DATA = "wrong.parameters.data";
    String USER_ALREADY_EXIST = "user.already.exist";
    String ENTITY_ALREADY_EXIST = "entity.already.exist";
    String INVALID_ROLE = "invalid.role";
    String MD5_IS_NOT_AVAILABLE = "md5.error";
    String UTF8_IS_NOT_AVAILABLE = "utf8.error";

    String BUS_ALREADY_USED = "bus.used";
    String DRIVER_ALREADY_USED = "driver.used";
    String BUS_OR_DRIVER_USED = "bus.or.driver.used";
    String TRANSACTION_IS_NOT_COMPLETED = "invalid.transaction";
}
