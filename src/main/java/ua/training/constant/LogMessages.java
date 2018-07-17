package ua.training.constant;

/**
 * Log messages
 *
 * @author Zabudskyi Oleksandr
 */
public interface LogMessages {
    String REMOVE_USER = "Remove logged user from context: ";
    String ADD_USER = "Add user to context: ";
    String NO_RESULT_FROM_DB = "No result from database";
    String DRIVER_REGISTRATION_ERROR = "Invalid driver registration";
    String SESSION_CREATED = "Session created. Session ID: ";
    String SESSION_DESTROYED = "Session destroyed. Session ID: ";
    String USER_ALREADY_LOGGED = "Already logged user with email:";
    String TRANSACTION_ERROR = "Transactions was not successful";
    String ROLLBACK_ERROR = "Rollback transactions was not successful";
    String CONNECTION_CLOSE_ERROR = "Close connection was not successful";
    String CREATE_ENTITY_ERROR = "Entity was not created";
    String UPDATE_ENTITY_ERROR = "Entity was not updated";
    String DELETE_ENTITY_ERROR = "Entity was not removed";
    String USER_SUCCESSFUL_LOGIN = "User logged successfully: ";
    String USER_LOGOUT = "User logged out";
}