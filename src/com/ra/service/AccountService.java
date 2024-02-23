package com.ra.service;

import com.ra.entity.Account;

public interface AccountService {
    Account login(String user, String pass);
}
