package model;

public class Customer {

    private int id;
    private String name;
    private String city;
    private User user;

    public Customer() {
    }

    public Customer(int id, String name, String city, User user) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.user = user;
    }

    public Customer(String name, String city, User user) {
        this.name = name;
        this.city = city;
        this.user = user;
    }

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
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return name;
    }

}
