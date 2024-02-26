package com.ra.view.userMenu;

import com.ra.util.Console;
import com.ra.view.adminMenu.*;

public class MUserMenu {
    public static void roleUser() {
        boolean check = true;
        do {
            System.out.println("MENU USER");
            System.out.println("Chọn chức năng: ");
            System.out.println("1. Danh sách phiếu nhập theo trạng thái");
            System.out.println("2. Tạo phiếu nhập");
            System.out.println("3. Cập nhật phiếu nhập");
            System.out.println("4. Tìm kiếm phiếu nhập");
            System.out.println("5. Danh sách phiếu xuất theo trạng thái");
            System.out.println("6. Tạo phiếu xuất");
            System.out.println("7. Cập nhật phiếu xuất");
            System.out.println("8. Tìm kiếm phiếu xuất");
            System.out.println("9. Thoát");

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

                    case 9:
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
