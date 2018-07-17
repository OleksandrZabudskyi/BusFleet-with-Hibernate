package ua.training.model.service;

/**
 * Provide security service layer for hashing password
 *
 * @author Zabudskyi Oleksandr
 */
public interface SecurityService {

    /**
     * Hashing string password presentation
     *
     * @param password simple string
     * @return hashed result
     */
    String makePasswordHash(String password);

    /**
     * Compare passwords by hash
     *
     * @param password simple string
     * @param hashedAndSaltedPassword hashed and salted
     * @return true if passwords compere by hash false otherwise
     */
    boolean comparePasswords(String password, String hashedAndSaltedPassword);
}
