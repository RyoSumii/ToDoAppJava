package model;

public class Users {
    private int id;
    private String name;

    public Users(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {return name;}
    public int getId() {return id;}
}
