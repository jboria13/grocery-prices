package com.example.groceryprices.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Store {
    @Id
    @GeneratedValue
    private int storeId;

    @NotNull
    @Size(min=3, message = "Invalid Department Name")
    private String storeName;

    public Store(String departmentName) {
        this.storeName = departmentName;
    }

    public Store(){
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
