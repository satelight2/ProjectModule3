package com.ra.view.adminMenu;

import com.ra.manager.BillManager;
import com.ra.util.Console;

public class BillMenu {
    public static void billMenu(){
        BillManager bm = new BillManager();
        boolean check = true;
        do {
            System.out.println("******************BILL MANAGEMENT****************");
            System.out.println("Chọn chức năng: ");
            System.out.println("1. Danh sách phiếu xuất");
            System.out.println("2. Tạo phiếu xuất");
            System.out.println("3. Cập nhật thông tin phiếu xuất");
            System.out.println("4. Chi tiết phiếu xuất");
            System.out.println("5. Duyệt phiếu xuất");
            System.out.println("6. Tìm kiếm phiếu xuất");
            System.out.println("7. Thoát");
            System.out.println("Mời bạn chọn từ 1 - 7");
            System.out.print("Lựa chọn của bạn là: ");
            try {
                int choose = Integer.parseInt(com.ra.util.Console.scanner.nextLine());
                switch (choose) {
                    case 1:
                        System.out.println("1. Danh sách phiếu xuất");
                        bm.show();
                        break;
                    case 2:
                        System.out.println("2. Tạo phiếu xuất");
                        bm.add();
                        break;
                    case 3:
                        System.out.println("3. Cập nhật thông tin phiếu xuất");
                        bm.update();
                        break;
                    case 4:
                        System.out.println("4. Chi tiết phiếu xuất");
                        bm.showDetails();
                        break;
                    case 5:
                        System.out.println("5. Duyệt phiếu xuất");
                        bm.approve();
                        break;
                    case 6:
                        System.out.println("6. Tìm kiếm phiếu xuất");
                        bm.search();
                        break;
                    case 7:
                        check = false;
                        break;
                    default:
                        System.out.println("Chọn sai mời chọn lại");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lựa chọn phải là số nguyên từ 1 - 7");
            }
        } while (check);

    }
    public static void insertBill(){

    }
}