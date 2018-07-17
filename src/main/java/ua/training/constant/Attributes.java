package ua.training.constant;

/**
 * All attributes for jsp pages and data base tables
 *
 * @author Zabudskyi Oleksandr
 */
public interface Attributes {
    String LANGUAGE = "language";
    String EN = "en";
    String UA = "ua";
    String ERROR_MESSAGE = "errorMessage";
    String LOGGED_USERS = "loggedUsers";

    String INFO_MESSAGE = "infoMessage";
    String BUS_INFO_MESSAGE = "busInfoMessage";
    String DRIVER_INFO_MESSAGE = "driverInfoMessage";
    String ACTIVE_USER = "activeUser";

    String ROWS_NUMBER = "rowsNumber";
    String PAGE = "page";

    /*Table column name*/
    /*user*/
    String USER_ID = "userId";
    String FIRST_NAME = "firstName";
    String LAST_NAME = "lastName";
    String EMAIL = "email";
    String PHONE_NUMBER = "phoneNumber";
    String ROLE = "role";
    String PASSWORD = "password";
    String DRIVER_LICENCE_NUMBER = "drivingLicenceNumber";
    String DRIVING_EXPERIENCE = "drivingExperience";
    String PASSPORT_NUMBER = "passportNumber";
    String PASSPORT_REGISTRATION = "passportRegistration";
    String ASSIGNED = "assigned";
    String REGISTERED = "registered";
    /*route*/
    String ROUTE_ID = "routeId";
    String ROTE_NAME = "routeName";
    String DESTINATION_FROM = "destinationFrom";
    String DESTINATION_TO = "destinationTo";
    /*bus*/
    String BUS_MODEL = "busModel";
    String LICENCE_PLATE = "licensePlate";
    String MANUFACTURE_YEAR = "manufactureYear";
    String PARKING_SPOT = "parkingSpot";
    /*trip*/
    String TRIP_NUMBER = "tripNumber";
    String TRIP_START_TIME = "tripStartTime";
    String TRIP_END_TIME = "tripEndTime";
    String BUS_ID = "busId";
    String DRIVER_ID = "driverId";
    String TRIPS = "trips";
    String NUMBER_OF_PAGES = "numberOfPages";
    String CURRENT_PAGE = "currentPage";
    String TRIP_ID = "tripId";
    String BUSES = "buses";
    String USED = "used";
    String DRIVERS = "drivers";
    String CONFIRMATION = "confirmation";
    String REFERER = "Referer";
}
