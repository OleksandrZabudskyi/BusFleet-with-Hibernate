package ua.training.model.service;

import ua.training.exeptions.EntityAlreadyHandledException;
import ua.training.exeptions.ServiceException;
import ua.training.model.entity.Employee;
import ua.training.model.entity.Trip;

import java.util.List;

/**
 * Provide service layer to manipulate with data from trip dao
 *
 * @author Zabudskyi Oleksandr
 */
public interface TripService {

    /**
     * Find all trips with routes
     *
     * @param offset number rows in previous page
     * @param limit max number of fetching rows
     * @return list trip with route
     */
    List<Trip> getTripsAndRoutes(int offset, int limit);

    /**
     * Fetching number of rows in trip table
     *
     * @return number
     */
    int getNumberOfRecords();

    /**
     * Add bus to trip and update bus status
     *
     * @param tripId updated trip id
     * @param busId inserted and updated bus id
     * @throws ServiceException
     */
    void setBusOnTrip(int tripId, int busId) throws ServiceException;

    /**
     * Delete bus from trip by id and updating bus status
     *
     * @param tripId updated trip id
     */
    void deleteBusFromTrip(int tripId);

    /**
     * Add driver to trip and update driver status
     *
     * @param tripId updated trip id
     * @param driverId inserted and updated driver id
     * @throws ServiceException
     */
    void setDriverOnTrip(int tripId, int driverId) throws ServiceException;

    /**
     * Delete driver from trip by id and updating driver status
     *
     * @param tripId updated trip id
     */
    void deleteDriverFromTrip(int tripId);

    /**
     * Fetching all appointments for employee
     *
     * @param employee  employee
     * @return list of trips
     */
    List<Trip> getAppointmentTripsToDrivers(Employee employee);

    /**
     * Update trip with confirmation
     *
     * @param tripId updated trip id
     */
    void setTripConfirmation(int tripId);
}
