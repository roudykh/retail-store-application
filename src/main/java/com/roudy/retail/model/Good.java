package com.roudy.retail.model;

public class Good {

    private Long ID;
    private String name;
    private String type;
    private double price;

    public Good() {
    }

    public Good(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Good(Long ID, String name, String type, double price) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                '}';
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
}
