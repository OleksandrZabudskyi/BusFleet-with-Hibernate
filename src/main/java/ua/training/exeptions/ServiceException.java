package ua.training.exeptions;

/**
 *  Class marks exception with could be throwing from service layer
 *
 * @author Zabudskyi Oleksandr
 * @see Exception
 */
public class ServiceException extends Exception {

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
