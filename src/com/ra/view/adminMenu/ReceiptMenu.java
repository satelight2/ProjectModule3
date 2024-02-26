package com.ra.view.adminMenu;

public class ReceiptMenu {
    public static void reMenu(){
        boolean check = true;
        do {
            System.out.println("******************RECEIPT MANAGEMENT****************");
            System.out.println("Chọn chức năng: ");
            System.out.println("1. Danh sách phiếu nhập");
            System.out.println("2. Tạo phiếu nhập");
            System.out.println("3. Cập nhật thông tin phiếu nhập");
            System.out.println("4. Chi tiết phiếu nhập");
            System.out.println("5. Duyệt phiếu nhập");
            System.out.println("6. Tìm kiếm phiếu nhập");
            System.out.println("7. Thoát");
            System.out.println("Mời bạn chọn từ 1 - 7");
            System.out.print("Lựa chọn của bạn là: ");
            try {
                int choose = Integer.parseInt(com.ra.util.Console.scanner.nextLine());
                switch (choose) {
                    case 1:
                        System.out.println("Danh sách doanh thu");
                        break;
                    case 2:
                        System.out.println("Thống kê doanh thu");
                        break;
                    case 3:
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
}
