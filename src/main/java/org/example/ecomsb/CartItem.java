package org.example.ecomsb;

public class CartItem {
    private static int nextId = 1;
    private int id;
    private String name;
    private int quantity;
    private double price;

    public CartItem(String name, int quantity, double price) {
        this.id = nextId++;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return quantity * price;
    }
}