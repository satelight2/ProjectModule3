package com.ra.view.adminMenu;

import com.ra.manager.BillManager;
import com.ra.model.BillType;
import com.ra.util.font.MessageCustom;
;

public class ReceiptMenu{
    public static void reMenu(){
        BillManager billManager = new BillManager();
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
                        System.out.println("1. Danh sách phiếu nhập");
                        billManager.show(BillType.IMPORT);
                        break;
                    case 2:
                        System.out.println("2. Tạo phiếu nhập");
                        billManager.add(BillType.IMPORT);
                        break;
                    case 3:
                        System.out.println("3. Cập nhật thông tin phiếu nhập");
                        billManager.update(BillType.IMPORT);
                        break;
                    case 4:
                        System.out.println("4. Chi tiết phiếu nhập");
                        billManager.showDetails(BillType.IMPORT);
                        break;
                    case 5:
                        System.out.println("5. Duyệt phiếu nhập");
                        billManager.approve(BillType.IMPORT);
                        break;
                    case 6:
                        System.out.println("6. Tìm kiếm phiếu nhập");
                        billManager.search(BillType.IMPORT);
                        break;

                    case 7:
                        check = false;
                        break;
                    default:
                        MessageCustom.choiceFailure();
                }
            } catch (NumberFormatException e) {
                System.err.println("Lựa chọn phải là số nguyên từ 1 - 7");
            }
        } while (check);
    }
}
