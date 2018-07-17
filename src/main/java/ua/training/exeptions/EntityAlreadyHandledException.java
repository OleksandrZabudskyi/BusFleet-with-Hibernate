package ua.training.exeptions;

/**
 *  Class marks exception for entity what already was updated in transaction
 *
 * @author Zabudskyi Oleksandr
 * @see Exception
 */
public class EntityAlreadyHandledException extends Exception {
    private int id;

    public EntityAlreadyHandledException(String message, int id) {
        super(message);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
