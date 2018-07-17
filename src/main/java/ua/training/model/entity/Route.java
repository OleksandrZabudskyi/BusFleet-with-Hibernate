package ua.training.model.entity;

public class Route {
    private int id;
    private String name;
    private String destinationFrom;
    private String destinationTo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestinationFrom() {
        return destinationFrom;
    }

    public void setDestinationFrom(String destinationFrom) {
        this.destinationFrom = destinationFrom;
    }

    public String getDestinationTo() {
        return destinationTo;
    }

    public void setDestinationTo(String destinationTo) {
        this.destinationTo = destinationTo;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", destinationFrom='" + destinationFrom + '\'' +
                ", destinationTo='" + destinationTo + '\'' +
                '}';
    }
}
