package com.roudy.retail.model;

public class User {

    private Long id;
    private String name;
    private String type;
    private String createdDate;

    public User() {
    }

    public User(String name, String type, String createdDate) {
        this.name = name;
        this.type = type;
        this.createdDate = createdDate;
    }

    public User(Long id, String name, String type, String createdDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", createdDate=" + createdDate +
                '}';
    }

    public Long getID() {
        return id;
    }

    public void setID(Long ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
