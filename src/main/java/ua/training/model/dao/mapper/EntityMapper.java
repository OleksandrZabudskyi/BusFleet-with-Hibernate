package ua.training.model.dao.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Mapper for transmission data from dao layer to object and vice versa
 *
 * @author Zabudskyi Oleksandr
 * @param <T>
 */
public interface EntityMapper<T> {

    /**
     *  Extracting data from result set to object
     *
     * @param resultSet result set
     * @return object <T>
     * @throws SQLException exception from db
     */
    T extractFromResultSet(ResultSet resultSet) throws SQLException;

    /**
     * Set parameters from object to prepared statement
     *
     * @param entity entity <T>
     * @param statement prepared statement
     * @throws SQLException exception from db
     */
    void setParameters(T entity, PreparedStatement statement) throws SQLException;

    /**
     * Method for creating unique entity in map
     *
     * @param cache map which stores unique entity
     * @param entity entity for comparing
     * @return unique entity from map
     */
    T makeUnique(Map<Integer, T> cache, T entity);
}
