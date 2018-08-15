package ua.training.model.dao;

import ua.training.model.entity.Bus;
import ua.training.model.entity.Route;

import java.util.List;
import java.util.Map;

public interface BusDao {
    /**
     * Find all buses with drivers through the many to many relation ship by using intermediate table
     *
     * @return Buses with list of drivers
     */
    List<Bus> findAllBusesWithDrivers();

    /**
     * Update many to many table bus_has_driver by joining all bus to driver
     *
     * @param buses all buses
     * @param driverId driverId
     */
    void addBusesHasDriverRelation(List<Bus> buses, int driverId);

    List<Bus> findAll();
}
