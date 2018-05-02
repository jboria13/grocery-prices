package com.example.groceryprices.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class ShoppingList {

    @Id
    @GeneratedValue
    private int shoppingListId;

    @NotNull
    @Size(min=1, message = "List name must be entered")
    private String shoppingListName;

    @ManyToOne
    private Account account;

    @ManyToMany
    private List<Item> items;

    public ShoppingList(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }

    public ShoppingList(){}

    public String getShoppingListName() {
        return shoppingListName;
    }

    public void setShoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }

    public int getShoppingListId() {
        return shoppingListId;
    }

    public Account getAccount() {
        return account;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item  item){ items.add(item); }

    public void setAccount(Account account) {
        this.account = account;
    }
}
