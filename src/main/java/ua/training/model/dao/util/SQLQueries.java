package ua.training.model.dao.util;

public interface SQLQueries {
    /*user table*/
    String FIND_ALL_USERS = "SELECT * FROM user";
    String FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    String FIND_USER_BY_ID = "SELECT * FROM user WHERE userId = ?";
    String INSERT_USER = "INSERT INTO user (firstName, lastName, " +
            "email, password, phoneNumber, role, drivingLicenceNumber, drivingExperience, passportNumber," +
            " passportRegistration, assigned, registered, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_USER_BY_ID = "UPDATE user SET firstName = ? , lastName = ?, email = ?, password = ?," +
            " phoneNumber = ?, role = ?, drivingLicenceNumber = ?, drivingExperience = ?," +
            "  passportNumber = ?, passportRegistration = ?, assigned = ?, registered = ? WHERE userId = ?";
    String DELETE_USER_BY_ID = "DELETE FROM user WHERE userId = ?";

    /*route table*/
    String FIND_ALL_ROUTES = "SELECT * FROM route";
    String FIND_ROUTE_BY_ID = "SELECT * FROM route  WHERE routeId = ?";
    String INSERT_ROUTE = "INSERT INTO route (routeName, destinationFrom, destinationTo) VALUES (?, ?, ?)";
    String UPDATE_ROUTE_BY_ID = "UPDATE route SET routeName = ? , destinationFrom = ?, " +
            "destinationTo = ? WHERE routeId = ?";
    String DELETE_ROUTE_BY_ID = "DELETE FROM route WHERE routeId = ?";

    /*trip table*/
    String FIND_ALL_TRIPS = "SELECT * FROM trip";
    String FIND_TRIP_BY_ID = "SELECT * FROM trip  WHERE tripId = ?";
    String INSERT_TRIP = "INSERT INTO trip (tripNumber, tripStartTime, tripEndTime, routeId, busId, driverId, confirmation)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_TRIP_BY_ID = "UPDATE trip SET tripNumber = ?, tripStartTime = ?, " +
            "tripEndTime = ?, routeId = ?, busId = ?, driverId = ?, confirmation = ? WHERE tripId = ?";
    String DELETE_TRIP_BY_ID =  "DELETE FROM trip WHERE tripId = ?";

    /*bus table*/
    String FIND_ALL_BUSES = "SELECT * FROM bus";
    String FIND_BUS_BY_ID = "SELECT * FROM bus  WHERE busId = ?";
    String INSERT_BUS = "INSERT INTO bus (busModel, licensePlate, manufactureYear, parkingSpot) VALUES (?, ?, ?, ?)";
    String UPDATE_BUS_BY_ID = "UPDATE bus SET busModel = ? , licensePlate = ?, manufactureYear = ?, " +
            "parkingSpot = ?, used = ? WHERE busId = ?";
    String DELETE_BUS_BY_ID = "DELETE FROM bus WHERE busId = ?";

    /*join requests*/
    String FIND_ALL_TRIPS_WITH_LINKS = "SELECT * FROM trip INNER JOIN route on trip.routeId = route.routeId " +
            "LEFT JOIN bus on trip.busId = bus.busId LEFT JOIN user ON trip.driverId = user.userId LIMIT ?, ?";
    String FIND_TRIPS_WITH_LINKS_BY_ID = "SELECT * FROM trip INNER JOIN bus" +
            " on trip.busId = bus.busId INNER JOIN route on trip.routeId = route.routeId" +
            " INNER JOIN user ON trip.driverId = user.userId WHERE trip.driverId = ?";
    String FIND_ALL_BUSES_WITH_DRIVERS = "SELECT * FROM bus LEFT JOIN bus_has_driver ON" +
            " bus.busId = bus_has_driver.bus_busId LEFT JOIN user ON bus_has_driver.user_userId = user.userId";
    String FIND_ALL_BUSES_WITH_ROUTES = "SELECT bus.busId, bus.busModel, bus.licensePlate, bus.manufactureYear, " +
            "bus.parkingSpot, bus.used, route.routeId, route.routeName, route.destinationFrom, route.destinationTo " +
            "FROM bus LEFT JOIN trip USING(busId) LEFT JOIN route USING(routeId);";

    String FIND_ALL_TRIPS_COUNT = "SELECT COUNT(*) as rowsNumber FROM trip;";
    String INSERT_BUS_HAS_DRIVER = "INSERT INTO bus_has_driver (bus_busId, user_userId) VALUES(?, ?)";
}
