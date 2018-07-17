package ua.training.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Bus {
    private int id;
    private String model;
    private String licensePlate;
    private int manufactureYear;
    private String parkingSpot;
    private boolean used;
    private List<Driver> drivers = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bus bus = (Bus) o;

        if (manufactureYear != bus.manufactureYear) return false;
        if (used != bus.used) return false;
        if (model != null ? !model.equals(bus.model) : bus.model != null) return false;
        if (licensePlate != null ? !licensePlate.equals(bus.licensePlate) : bus.licensePlate != null) return false;
        return parkingSpot != null ? parkingSpot.equals(bus.parkingSpot) : bus.parkingSpot == null;
    }

    @Override
    public int hashCode() {
        int result = model != null ? model.hashCode() : 0;
        result = 31 * result + (licensePlate != null ? licensePlate.hashCode() : 0);
        result = 31 * result + manufactureYear;
        result = 31 * result + (parkingSpot != null ? parkingSpot.hashCode() : 0);
        result = 31 * result + (used ? 1 : 0);
        return result ;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", manufactureYear=" + manufactureYear +
                ", parkingSpot='" + parkingSpot + '\'' +
                ", used=" + used +
                '}';
    }
}
