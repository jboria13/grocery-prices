package com.example.groceryprices.models;

import com.example.groceryprices.models.data.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountDAO accountDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account findByUsername(String username){
        return accountDAO.findByUsername(username);
    }
}
