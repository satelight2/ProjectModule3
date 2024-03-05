package com.ra.view;

import com.ra.entity.Account;
import com.ra.model.PermissionType;
import com.ra.service.AccountService;
import com.ra.service.impl.AccountServiceImpl;
import com.ra.util.Console;
import com.ra.util.Storage;
import com.ra.view.adminMenu.MainAdminMenu;
import com.ra.view.userMenu.MUserMenu;

public class Menu {
    public static void mainMenu() throws Exception {
        do {
            System.out.println("Welcome to the Inventory Management System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose function: ");
            try {
                int choose = Integer.parseInt(Console.scanner.nextLine());
                switch (choose) {
                    case 1:
                        loginMenu();
                        break;
                    case 2:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("You choose wrong function!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lựa chọn phải là số nguyên từ 1 - 2");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);

    }
    public static void logout() {
        Storage.current_user = null;
        Storage.current_account = null;
        System.out.println("Đăng xuất thành công!");
    }

    private static void loginMenu() throws Exception {
        AccountService accountService = new AccountServiceImpl();
        boolean isRunning = true;
        while (isRunning) {
            System.out.print("Nhập tài khoản: ");
            String user = Console.scanner.nextLine();
            System.out.print("Nhập mật khẩu: ");
            String pass = Console.scanner.nextLine();
            Account acc = accountService.login(user, pass);

            if (acc == null) {
                System.out.println("Không tìm thấy tài khoản!");
            } else {
                if (PermissionType.ADMIN == acc.isPermission()) {
                    MainAdminMenu.roleAdmin();
                } else {
                    MUserMenu.roleUser();
                }

                // Kiểm tra nếu người dùng muốn đăng xuất
                System.out.println("Bạn có muốn đăng xuất? (yes/no)");
                String choice = Console.scanner.nextLine();
                if (choice.equalsIgnoreCase("yes")) {
                    // Đăng xuất
                    logout();
                    isRunning = false;

                } else {
                   // Không đăng xuất
                    isRunning = true;
                }
            }
        }
    }




}
