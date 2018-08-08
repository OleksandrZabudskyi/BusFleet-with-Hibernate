package ua.training.model.entity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tripId")
    private int id;
    @Column(name = "tripNumber")
    private String number;
    @Column(name = "tripStartTime")
    private LocalTime startTime;
    @Column(name = "tripEndTime")
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;
    @ManyToOne
    @JoinColumn(name = "busId")
    private Bus bus;
    @ManyToOne
    @JoinColumn(name = "driverId", referencedColumnName = "userId")
    private Driver driver;
    private boolean confirmation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", route=" + route +
                ", bus=" + bus +
                ", driver=" + driver +
                ", confirmation=" + confirmation +
                '}';
    }

    public static final class TripBuilder {

        private int id;
        private String number;
        private LocalTime startTime;
        private LocalTime endTime;
        private Route route;
        private Bus bus;
        private Driver driver;
        private boolean confirmation;

        public TripBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public TripBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public TripBuilder setStartTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public TripBuilder setEndTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public TripBuilder setRoute(Route route) {
            this.route = route;
            return this;
        }

        public TripBuilder setBus(Bus bus) {
            this.bus = bus;
            return this;
        }

        public TripBuilder setDriver(Driver driver) {
            this.driver = driver;
            return this;
        }

        public TripBuilder setConfirmation(boolean confirmation) {
            this.confirmation = confirmation;
            return this;
        }

        public Trip createTrip() {
            Trip trip = new Trip();
            trip.setId(id);
            trip.setNumber(number);
            trip.setStartTime(startTime);
            trip.setEndTime(endTime);
            trip.setRoute(route);
            trip.setBus(bus);
            trip.setDriver(driver);
            trip.setConfirmation(confirmation);
            return trip;
        }
    }
}
