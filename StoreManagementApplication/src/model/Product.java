
package model;

import java.io.Serializable;

public class Product implements Serializable, Comparable<Product> {
    private String code;
    private String name;
    private String producer;
    private String manufacturingDate;
    private String expirationDate;
    private int quantity;
    private int price;
    
    public Product() {}

    public Product(String code, String name, String producer, String manufacturingDate, String expirationDate, int quantity, int price) {
        this.code = code;
        this.name = name;
        this.producer = producer;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    @Override
    public String toString() {      
        return String.format("|%-7s|%-20s|%-20s|%-18s|%-15s|%8d|%10d|\n", code, name, producer, manufacturingDate, expirationDate, quantity, price);
    }

    
    
    @Override
    public int compareTo(Product o) {
        int result = 0;
        if (this.quantity < o.quantity) {
            return 1;
        }
        else if (this.quantity > o.quantity) {
            return -1;
        }
        else return 0;
    }

    public void displayRegisteredProducts() {
        System.out.printf("|%-7s|%-20s|%-20s|\n", code, name, producer);
    }
    
    public void displayProduct() {
        System.out.printf("|%-7s|%-20s|%-20s|%-18s|%-15s|%8d|\n", code, name, producer, manufacturingDate, expirationDate, quantity);
    }
    
    public String displayProductString() {
        return String.format("|%-7s|%-20s|%-20s|%-18s|%-15s|%8d|\n", code, name, producer, manufacturingDate, expirationDate, quantity);
    }
}
