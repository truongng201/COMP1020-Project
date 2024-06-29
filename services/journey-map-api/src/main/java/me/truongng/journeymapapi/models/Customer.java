package me.truongng.journeymapapi.models;

public class Customer {
    private String id;
    private User user;
    private Location location;

    public Customer() {
    }

    public Customer(User user, Location location) {
        this.user = user;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getUserId() {
        return user.getId();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", location=" + location +
                '}';
    }
}