package com.ra.service;

import com.ra.entity.Account;
import com.ra.entity.Product;


import java.util.List;

public interface AccountService {
    Account login(String user, String pass);
    List<Account> findAll();
    Account addAccount(Account account);
    Account updateAcc(Account product);
    Account findAny(Object... keys);
    Account findByUsernameOrEmployeeName(String searchTerm);
}
