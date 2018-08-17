package ua.training.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue(value = "DRIVER")
public class Driver extends Employee {
    private String drivingLicenceNumber;
    private int drivingExperience;
    private boolean assigned;
    private boolean registered;
    @ManyToMany
    @JoinTable(
            name = "bus_has_driver",
            joinColumns = { @JoinColumn(name = "user_userId") },
            inverseJoinColumns = { @JoinColumn(name = "bus_busId") }
    )
    private List<Bus> buses;
    @OneToMany(mappedBy = "driver")
    private List<Trip> trip;

    public String getDrivingLicenceNumber() {
        return drivingLicenceNumber;
    }

    public void setDrivingLicenceNumber(String drivingLicenceNumber) {
        this.drivingLicenceNumber = drivingLicenceNumber;
    }

    public int getDrivingExperience() {
        return drivingExperience;
    }

    public void setDrivingExperience(int drivingExperience) {
        this.drivingExperience = drivingExperience;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public List<Trip> getTrip() {
        return trip;
    }

    public void setTrip(List<Trip> trip) {
        this.trip = trip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Driver driver = (Driver) o;

        if (drivingExperience != driver.drivingExperience) return false;
        if (assigned != driver.assigned) return false;
        if (registered != driver.registered) return false;
        return drivingLicenceNumber != null ? drivingLicenceNumber.equals(driver.drivingLicenceNumber)
                : driver.drivingLicenceNumber == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (drivingLicenceNumber != null ? drivingLicenceNumber.hashCode() : 0);
        result = 31 * result + drivingExperience;
        result = 31 * result + (assigned ? 1 : 0);
        result = 31 * result + (registered ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Driver{" +
                super.toString() +
                "drivingLicenceNumber='" + drivingLicenceNumber + '\'' +
                ", drivingExperience=" + drivingExperience +
                ", assigned=" + assigned +
                ", registered=" + registered +
                '}';
    }

    public static final class DriverBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String password;
        private ROLE role;
        private String drivingLicenceNumber;
        private int drivingExperience;
        private boolean assigned;
        private boolean registered;

        public DriverBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public DriverBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public DriverBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public DriverBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public DriverBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public DriverBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public DriverBuilder setRole(ROLE role) {
            this.role = role;
            return this;
        }

        public DriverBuilder setDrivingLicenceNumber(String drivingLicenceNumber) {
            this.drivingLicenceNumber = drivingLicenceNumber;
            return this;
        }

        public DriverBuilder setDrivingExperience(int drivingExperience) {
            this.drivingExperience = drivingExperience;
            return this;
        }

        public DriverBuilder setAssigned(boolean assigned) {
            this.assigned = assigned;
            return this;
        }

        public DriverBuilder setRegistered(boolean registered) {
            this.registered = registered;
            return this;
        }

        public Driver createDriver() {
            Driver driver = new Driver();
            driver.setId(id);
            driver.setFirstName(firstName);
            driver.setLastName(lastName);
            driver.setPhoneNumber(phoneNumber);
            driver.setEmail(email);
            driver.setPassword(password);
            driver.setRole(role);
            driver.setDrivingLicenceNumber(drivingLicenceNumber);
            driver.setDrivingExperience(drivingExperience);
            driver.setAssigned(assigned);
            driver.setRegistered(registered);
            return driver;
        }
    }

}
