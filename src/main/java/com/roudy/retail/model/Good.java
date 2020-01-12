package com.roudy.retail.model;

public class Good {

    private Long id;
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

    public Good(Long id, String name, String type, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
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
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
}
