package ua.training.model.dao;

import ua.training.model.entity.Trip;

import java.util.List;

public interface TripDao {

    /**
     * Find limited list trips and routes according to ingoing parameters
     *
     * @param offset number already fetched rows
     * @param limit  number to need fetch
     * @return list trips with routes
     */
    List<Trip> findTripsWithRoutes(int offset, int limit);

    /**
     * Get total records in trip table
     *
     * @return number
     */
    int getNumberOfRecords();

    /**
     * Find all trips with routes, buses and drivers
     *
     * @param driverId driverId
     * @return list trips
     */
    List<Trip> findTripsWithDetailsByDriverId(int driverId);
}
