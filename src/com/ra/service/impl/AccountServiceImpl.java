package com.ra.service.impl;

import com.ra.entity.Account;
import com.ra.entity.Employee;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.AccountService;
import com.ra.service.EmployeeService;
import com.ra.util.annotation.EmpID;
import com.ra.util.Storage;
import com.ra.util.annotation.Username;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private Repository<Account> accountRepository;

    public AccountServiceImpl() {
        this.accountRepository = new RepositoryImpl<>();
    }

    @Override
    public Account login(String user, String pass) {
        for (Account account : accountRepository.findAll(Account.class)) {
            if (account.getUserName().equals(user) && account.getPassword().equals(pass)) {
                Storage.current_account = account;
                Storage.current_user = account.getUserName();
                System.out.println("Đăng nhập thành công!");
                System.out.println("Xin chào " + Storage.current_user+ " mã nhân viên: " + Storage.current_account.getEmpId()) ;
                return account;
            }
        }
        return null;
    }

    @Override
    public List<Account> findAll() {
        return this.accountRepository.findAll(Account.class);
    }

    @Override
    public Account addAccount(Account account) {
        return this.accountRepository.add(account);
    }



    @Override
    public Account updateAcc(Account product) {
        return this.accountRepository.edit(product);
    }



    @Override
    public Account findAny(Object... keys) {
        return this.accountRepository.findWithAnything(Account.class, Username.class, EmpID.class, keys);
    }


    @Override
    public Account findByUsernameOrEmployeeName(String searchTerm) {
        Account accountByUsername = findAny(searchTerm);
        if (accountByUsername != null) {
            return accountByUsername;
        }
        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = employeeService.findAny(searchTerm);
        if (employee != null) {
            List<Account> accountList = findAll();
            for (Account account : accountList) {
                if (account.getEmpId().equals(employee.getEmpId())) {
                    return account;
                }
            }
            System.out.println("Không tìm thấy tài khoản nào phù hợp với tên nhân viên " + searchTerm);
        }

        return null;
    }
}
