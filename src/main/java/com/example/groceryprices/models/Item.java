package com.example.groceryprices.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private int itemId;

    @NotNull
    @Size(min = 1, message = "Item name must be entered")
    private String itemName;

    @NotNull
    @Size(min = 1, message = "Brand name must be entered")
    private String brand;

    @NotNull
    @DecimalMin(value = "0.01", message = "Cannot enter a negative value.")
    private Double price;

    @NotNull
    @Size(min = 1, message = "Quantity must be entered")
    private String quantity;

    @NotNull
    @DateTimeFormat(pattern = "MM/DD/YYYY")
    private String itemDate;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Account account;

    @ManyToMany(mappedBy = "items")
    private List<ShoppingList> shoppingLists;

    public Item (String itemName, String brand, Double price, String quantity, String itemDate){
        this.itemName = itemName;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.itemDate = itemDate;
    }

    public Item (){ }

    public String getItemName() { return itemName; }

    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getItemId() { return itemId; }

    public String getItemDate() {
        return itemDate;
    }

    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }

    public Department getDepartment() { return department; }

    public Store getStore() { return store; }

    public Account getAccount() { return account; }

    public List<ShoppingList> getShoppingLists() { return shoppingLists; }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
