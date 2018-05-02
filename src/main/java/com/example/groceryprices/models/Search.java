package com.example.groceryprices.models;


import javax.validation.constraints.Size;

public class Search {

    @Size(max=20, message = "Enter one item")
    private String searchItemName;

    public Search(@Size(max = 20, message = "Enter one item") String searchItemName) {
        this.searchItemName = searchItemName;
    }

    public Search(){}

    public String getSearchItemName() {
        return searchItemName;
    }

    public void setSearchItemName(String searchItemName) {
        this.searchItemName = searchItemName;
    }
}
