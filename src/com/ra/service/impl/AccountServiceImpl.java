package com.ra.service.impl;

import com.ra.entity.Account;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private Repository<Account> accountRepository;

    public AccountServiceImpl() {
        this.accountRepository = new RepositoryImpl<>();
    }

    @Override
    public Account login(String user, String pass) {
        return null;
    }
}
