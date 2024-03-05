package com.ra.view.adminMenu;


import com.ra.manager.BillManager;
import com.ra.model.BillType;
import com.ra.util.font.MessageCustom;

public class DeliveryMenu {
    public static void billMenu(){
        BillManager billManager = new BillManager();
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
                        billManager.show(BillType.EXPORT);
                        break;
                    case 2:
                        System.out.println("2. Tạo phiếu xuất");
                        billManager.add(BillType.EXPORT);
                        break;
                    case 3:
                        System.out.println("3. Cập nhật thông tin phiếu xuất");
                        billManager.update(BillType.EXPORT);
                        break;
                    case 4:
                        System.out.println("4. Chi tiết phiếu xuất");
                        billManager.showDetails(BillType.EXPORT);
                        break;
                    case 5:
                        System.out.println("5. Duyệt phiếu xuất");
                        billManager.approve(BillType.EXPORT);
                        break;
                    case 6:
                        System.out.println("6. Tìm kiếm phiếu xuất");
                        billManager.search(BillType.EXPORT);
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