package ua.training.exeptions;

/**
 *  Class marks exception for when entity with unique value already exist
 *
 * @author Zabudskyi Oleksandr
 * @see Exception
 */
public class EntityAlreadyExistException extends Exception {
    private String uniqueValue;

    public EntityAlreadyExistException(String message, Throwable cause, String uniqueValue) {
        super(message, cause);
        this.uniqueValue = uniqueValue;
    }

    public EntityAlreadyExistException(String message) {
        super(message);
    }

    public String getUniqueValue() {
        return uniqueValue;
    }
}
