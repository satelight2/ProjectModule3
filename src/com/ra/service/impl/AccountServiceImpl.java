package com.ra.service.impl;

import com.ra.entity.Account;
import com.ra.entity.Employee;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.AccountService;
import com.ra.service.EmployeeService;
import com.ra.util.Storage;

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
                Storage.current_user = account.getUserName();
                System.out.println("Đăng nhập thành công!");
                System.out.println("Xin chào " + Storage.current_user);
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
    public Account findByEmpID(String empID) {
        return this.accountRepository.findByEmpID(Account.class, empID);
    }



    @Override
    public Account findByUsernameOrEmployeeName(String searchTerm) {
        // Tìm kiếm tài khoản theo username
        Account accountByUsername = this.accountRepository.findByUsername(Account.class,searchTerm);
        if (accountByUsername != null) {
            return accountByUsername;
        }

        // Nếu không tìm thấy theo username, thử tìm theo tên nhân viên
        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = employeeService.searchProductByNameOrID(searchTerm);
        if (employee != null) {
            return this.accountRepository.findByEmpID(Account.class,employee.getEmpId());
        }

        // Nếu không tìm thấy theo cả username và tên nhân viên, trả về null
        return null;
    }
}
