package com.example.groceryprices.models.data;

import com.example.groceryprices.models.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StoreDAO extends CrudRepository<Store, Integer>{
    Store findAllBystoreId(Integer storeId);
}
