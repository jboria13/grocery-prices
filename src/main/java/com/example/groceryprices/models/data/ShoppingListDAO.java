package com.example.groceryprices.models.data;

import com.example.groceryprices.models.Account;
import com.example.groceryprices.models.ShoppingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ShoppingListDAO extends CrudRepository<ShoppingList, Integer>{
    ShoppingList findByshoppingListId(int shoppingListId);

    List<ShoppingList> findByAccount(Account userAccount);
}
