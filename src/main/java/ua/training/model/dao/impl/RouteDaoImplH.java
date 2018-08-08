package ua.training.model.dao.impl;

import org.hibernate.Session;
import ua.training.model.dao.RouteDao;
import ua.training.model.entity.Route;

import java.util.List;
import java.util.Optional;

public class RouteDaoImplH implements RouteDao {
    private Session session;

    public RouteDaoImplH(Session session) {
        this.session = session;
    }

    @Override
    public Optional<Route> findById(Integer integer) {
        return null;
    }

    @Override
    public List<Route> findAll() {
        return null;
    }

    @Override
    public void create(Route entity) {

    }

    @Override
    public void update(Route entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void close() {

    }
}
