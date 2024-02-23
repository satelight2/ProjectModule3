package com.ra.manager;

import com.ra.entity.Bill;
import com.ra.entity.BillDetails;
import com.ra.entity.Product;
import com.ra.model.BillType;
import com.ra.model.ConstStatus;
import com.ra.repository.Repository;
import com.ra.util.Console;
import com.ra.util.Storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillManager implements Manager{
    private Repository<Product> productRepository;

    @Override
    public void run() {
        // Nhập sản phẩm
        List<BillDetails> billDetails = new ArrayList<>();
        boolean stop = false;
        while (stop == false) {
            System.out.print("Nhập mã sp: ");
            String prodId = Console.scanner.nextLine();

            Product product = productRepository.findId(Product.class, prodId);
            if (product != null && product.isProductStatus() == ConstStatus.ProductStt.ACTIVE) {
                // Nhập số lượng xuất

                // Kiểm tra số lượng còn trong kho

                // Tạo bill_details
                // Thêm vào list danh sách đã tạo
                    // 1. TH sp đã có trong list billDetails
                        // cập nhật lại số lượng
                    // 2. TH sp chưa có trong list
                        // thêm mới
                System.out.print("Bạn có muốn tiếp tục nhập không (có/không)?: ");
                String ans = Console.scanner.nextLine();
                if (!ans.equals("có")) {
                    stop = true;
                }
            } else {
                // Xử lý để quay lại nhập lại hoặc kết thúc
                System.out.println("Sản phẩm không tồn tại hoặc đã ngừng kinh doanh!");
                System.out.print("Bạn có muốn tiếp tục nhập không (có/không)?: ");
                String ans = Console.scanner.nextLine();
                if (!ans.equals("có")) {
                    stop = true;
                }
            }
        }
        // Kiểm tra lại list xem có xuất sp nào ko
        if (billDetails.size() > 0) {
            // Có thể hiển thị lại danh sách các sản phẩm đã nhập

            // Tạo phiếu xuất
            Bill bill = new Bill();
            bill.setBillCode(("HD" + System.currentTimeMillis()).substring(0, 9));
            bill.setBillType(BillType.EXPORT);
            bill.setEmpIdCreated(Storage.current_user);
            bill.setCreated(new Date());
            System.out.println("Nhập mã user duyệt: ");
            bill.setEmpIdAuth("");
            bill.setBillStatus(ConstStatus.BillStt.CREATE);
            // insert bills
            // insert billDetails (lấy từ list billDetails)
        }
    }
}
