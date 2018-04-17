package com.example.groceryprices.models.data;

import com.example.groceryprices.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AccountDAO extends CrudRepository<Account, Integer>{

    Account findByUsername(String username);
}
