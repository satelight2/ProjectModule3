package com.ra;

import com.ra.entity.Account;
import com.ra.entity.Product;
import com.ra.manager.Manager;
import com.ra.manager.ProductManager;
import com.ra.model.ConstStatus;
import com.ra.model.PermissionType;
import com.ra.service.AccountService;
import com.ra.service.impl.AccountServiceImpl;
import com.ra.util.Console;

public class Application {
    public static void main(String[] args) {
        AccountService accountService = new AccountServiceImpl();

        System.out.print("Nhập tài khoản: ");
        String user = Console.scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String pass = Console.scanner.nextLine();
        Account acc = accountService.login(user, pass);
        if (acc == null) {
            System.out.println();
        } else {
            if (PermissionType.ADMIN == acc.isPermission()) {

            } else {

            }
        }

    }

    public static void roleAdmin() {
        Manager productManager = new ProductManager();
        System.out.println("MENU");
        System.out.print("Chọn chức năng: ");
        int choose = Integer.parseInt(Console.scanner.nextLine());
        switch (choose) {
            case 1:
                productManager.run();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;

            default:
                System.out.println("Sai tính năng!");
        }
    }
}
