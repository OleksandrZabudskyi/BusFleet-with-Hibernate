package ua.training.model.dao.mapper;

import ua.training.constant.Attributes;
import ua.training.model.entity.Route;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RouteMapper implements EntityMapper<Route> {
    @Override
    public Route extractFromResultSet(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getInt(Attributes.ROUTE_ID));
        route.setName(resultSet.getString(Attributes.ROTE_NAME));
        route.setDestinationFrom(resultSet.getString(Attributes.DESTINATION_FROM));
        route.setDestinationTo(resultSet.getString(Attributes.DESTINATION_TO));
        return route;
    }

    @Override
    public void setParameters(Route entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDestinationFrom());
        statement.setString(3, entity.getDestinationTo());
        statement.setInt(4, entity.getId());
    }

    @Override
    public Route makeUnique(Map<Integer, Route> cache, Route route) {
        cache.putIfAbsent(route.getId(), route);
        return cache.get(route.getId());
    }
}
