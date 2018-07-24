package ua.training.model.dao.impl;

import org.apache.log4j.Logger;
import ua.training.constant.LogMessages;
import ua.training.constant.Messages;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.RouteDao;
import ua.training.model.dao.mapper.EntityMapper;
import ua.training.model.dao.mapper.RouteMapper;
import ua.training.model.dao.util.SQLQueries;
import ua.training.model.entity.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteDaoImpl implements RouteDao {
    private final static Logger logger = Logger.getLogger(RouteDaoImpl.class);
    private final Connection connection;
    private final EntityMapper<Route> routeMapper;

    public RouteDaoImpl(Connection connection, EntityMapper<Route> routeMapper) {
        this.connection = connection;
        this.routeMapper = routeMapper;
    }

    @Override
    public Optional<Route> findById(Integer id) {
        Optional<Route> route = Optional.empty();
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.FIND_ROUTE_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                route = Optional.ofNullable(routeMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error(LogMessages.NO_RESULT_FROM_DB, e);
            throw new RuntimeException(e);
        }
        return route;
    }

    @Override
    public List<Route> findAll() {
        List<Route> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_ALL_ROUTES);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(routeMapper.extractFromResultSet(resultSet));
            }
            return resultList;
        } catch (SQLException e) {
            logger.error(LogMessages.NO_RESULT_FROM_DB, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Route entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_ROUTE)) {
            routeMapper.setParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            try {
                throw new EntityAlreadyExistException(Messages.ENTITY_ALREADY_EXIST, e, entity.getName());
            } catch (EntityAlreadyExistException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e) {
            logger.error(LogMessages.CREATE_ENTITY_ERROR, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Route entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_ROUTE_BY_ID)) {
            routeMapper.setParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessages.UPDATE_ENTITY_ERROR, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.DELETE_ROUTE_BY_ID)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessages.DELETE_ENTITY_ERROR, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(LogMessages.CONNECTION_CLOSE_ERROR, e);
            throw new RuntimeException(e);
        }
    }
}
