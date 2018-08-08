package ua.training.model.dao;

import ua.training.exeptions.EntityAlreadyExistException;

import java.util.List;
import java.util.Optional;

/**
 * General CRUD interface
 *
 * @author Zabudskyi Oleksandr
 * @param <T>
 * @param <ID>
 */
public interface GenericDao<T, ID> extends AutoCloseable {
    Optional<T> findById(ID id);

    List<T> findAll();

    void create(T entity);

    void update(T entity);

    void delete(ID id);

    void close();
}
