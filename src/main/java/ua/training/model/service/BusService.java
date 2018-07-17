package ua.training.model.service;

import ua.training.model.entity.Bus;
import ua.training.model.entity.Route;

import java.util.List;
import java.util.Map;

/**
 * Provide service layer to manipulate with data from bus dao
 *
 * @author Zabudskyi Oleksandr
 */
public interface BusService {

    /**
     * Fetch bus and route
     *
     * @return map
     */
    Map<Bus, Route> getAllBusesWithRoutes();

    /**
     * Get list of all buses with all drivers
     *
     * @return list
     */
    List<Bus> getAllBusesToAllDrivers();
}
