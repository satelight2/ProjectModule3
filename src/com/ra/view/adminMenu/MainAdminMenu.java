package com.ra.view.adminMenu;

import com.ra.util.Console;

public class MainAdminMenu {
    public static void roleAdmin() throws Exception{
        boolean check = true;
        do {
            System.out.println("******************WAREHOUSE MANAGEMENT****************");
            System.out.println("Chọn chức năng: ");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Quản lý tài khoản");
            System.out.println("4. Quản lý phiếu nhập");
            System.out.println("5. Quản lý phiếu xuất");
            System.out.println("6. Quản lý báo cáo");
            System.out.println("7. Đăng xuất");
            System.out.println("Mời bạn chọn từ 1 - 7");
            System.out.print("Lựa chọn của bạn là: ");

            try{
                int choose = Integer.parseInt(Console.scanner.nextLine());
                switch (choose){
                    case 1:
                        System.out.println("Quản lý sản phẩm");
                        ProductMenu.pMenu();
                        //  productService.addProduct();
                        break;
                    case 2:
                        System.out.println("Quản lý nhân viên");
                        EmployeeMenu.eMenu();
                        break;
                    case 3:
                        System.out.println("Quản lý tài khoản");
                        AccountMenu.aMenu();
                        break;
                    case 4:
                        System.out.println("Quản lý phiếu nhập");
                        ReceiptMenu.reMenu();
                        break;
                    case 5:
                        System.out.println("Quản lý phiếu xuất");
                        BillMenu.billMenu();
                        break;
                    case 6:
                        System.out.println("Quản lý báo cáo");
                        StatisticMenu.statisticMenu();
                        break;

                    case 7:
                        check = false;
                        break;
                    default:
                        System.out.println("Chọn sai mời chọn lại");
                }
            }catch (NumberFormatException e){
                System.err.println("Lựa chọn phải là số nguyên từ 1 - 8");

            }
        } while (check);
    }
}
