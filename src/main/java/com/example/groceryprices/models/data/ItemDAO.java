package com.example.groceryprices.models.data;

import com.example.groceryprices.models.Account;
import com.example.groceryprices.models.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ItemDAO extends CrudRepository<Item, String>{
    List<Item> findAllByItemName(String searchName);

    Item findByitemId(int itemId);

    List<Item> findAllByAccount(Account userAccount);
}
