package com.example.groceryprices.models.forms;

import com.example.groceryprices.models.Item;
import com.example.groceryprices.models.ShoppingList;

import javax.validation.constraints.NotNull;

public class AddShoppingListItemForm {

    private ShoppingList shoppingList;


    @NotNull
    private int shoppingListId;


    public AddShoppingListItemForm(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public int getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(int shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

}
