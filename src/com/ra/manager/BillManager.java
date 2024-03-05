package com.ra.manager;

import com.ra.entity.Bill;
import com.ra.entity.BillDetails;
import com.ra.entity.Product;
import com.ra.model.*;
import com.ra.service.impl.BillDetailServiceImpl;
import com.ra.service.impl.BillServiceImpl;
import com.ra.service.impl.ProductServiceImpl;
import com.ra.util.Console;
import com.ra.util.Storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.ra.model.Header.printSeparator;

public class BillManager implements Manager {
    ProductServiceImpl productService = new ProductServiceImpl();
    BillServiceImpl billService = new BillServiceImpl();
    BillDetailServiceImpl billDetailService = new BillDetailServiceImpl();



    public void printBill(Bill b){
        String info = String.format(TableForm.bills.column,
                b.getBillId(),
                b.getBillCode(),
                b.isBillType() == (BillType.IMPORT) ? "Phiếu nhập" : "Phiếu xuất",
                b.getEmpIdCreated(),
                b.getCreated(),
                b.getEmpIdAuth(),
                b.getAuthDate(),
                b.getBillStatus() == (ConstStatus.BillStt.CREATE) ? "Tạo" : b.getBillStatus() == (ConstStatus.BillStt.APPROVE) ? "Duyệt" : "Hủy");
        System.out.println(info);
        int headerLength = info.length();
        printSeparator(headerLength);
    }
    public void printBillDetails(BillDetails bd){
        String info = String.format(TableForm.billDetails.column,
                bd.getBillDetailId(),
                bd.getBillId(),
                bd.getProductId(),
                bd.getQuantity(),
                bd.getPrice());
        System.out.println(info);
        int headerLength = info.length();
        printSeparator(headerLength);
    }



    @Override
    public void show(boolean billtype) {
        List<Bill> listReceipt =  billService.findAll();
        if(Storage.current_account.isPermission() == PermissionType.ADMIN){ // kiểm tra quyền, nếu là admin thì hiển thị tất cả hóa đơn
            if(listReceipt.isEmpty()){
                System.out.println("Không có hóa đơn nào");
            }else{
                Header.bills();
                for(Bill bill : listReceipt){
                    if(bill.isBillType() == billtype){
                        printBill(bill);
                    }
                }
            }
        }else { // nếu không phải admin thì hiển thị hóa đơn của nhân viên đó
            if(listReceipt.isEmpty()){
                System.out.println("Không có hóa đơn nào");
            }else{
                Header.bills();
                for(Bill bill : listReceipt){
                    if(bill.getEmpIdCreated().equals(Storage.current_account.getEmpId()) && bill.isBillType() == billtype){
                        printBill(bill);
                    }
                }
            }

    }
}

    @Override
    public void showDetails(boolean type) {
        List<BillDetails> listBillDetails = billDetailService.findAll();
        List<Bill> listReceipt =  billService.findAll();
        if(Storage.current_account.isPermission() == PermissionType.ADMIN){ // kiểm tra quyền, nếu là admin thì hiển thị tất cả hóa đơn
            if(listReceipt.isEmpty()){
                System.out.println("Không có hóa đơn nào");
            }else{
                // Lọc danh sách Bill dựa trên trạng thái
                List<Bill> filteredBills = listReceipt.stream()
                        .filter(bill -> bill.isBillType() == type)
                        .collect(Collectors.toList());

                List<BillDetails> matchedBillDetails = listBillDetails.stream()
                        .filter(billDetail -> filteredBills.stream()
                                .anyMatch(bill -> bill.getBillId()==(billDetail.getBillId())))
                        .collect(Collectors.toList());
                System.out.println("matched bill details : " + matchedBillDetails.size());
                Header.billDetails();
                for(BillDetails billDetail : matchedBillDetails){
                    printBillDetails(billDetail);
                }
            }


        }

    }

    @Override
    public void add(boolean billtype) {
        List<BillDetails> billDetailsTemp = new ArrayList<>();
            boolean stop = false;
            while (stop == false) {
                System.out.print("Nhập mã sp: ");
                String prodId = Console.scanner.nextLine();
                Product product = productService.findAny(prodId);
                if (product != null && product.isProductStatus() == ConstStatus.ProductStt.ACTIVE) {
                    System.out.println("Sản phẩm: " + product.getProductId()+ " - " + product.getProductName()  + " - " + product.getQuantity() + " - " + product.getCreated());
                    boolean isProductExist = false;
                    BillDetails billDetailTemp = new BillDetails();
                    for (BillDetails billDetail : billDetailsTemp) {
                        if (billDetail.getProductId().equals(prodId)) {
                            isProductExist = true;
                            break; // Nếu tìm thấy sản phẩm trong danh sách, không cần kiểm tra tiếp
                        }
                    }
                    if (isProductExist) {
                        System.out.println("Sản phẩm đã có trong danh sách!");
                        System.out.println("Nhập số lượng mới: ");
                        int quantity = Integer.parseInt(Console.scanner.nextLine());
                        for (BillDetails billDetail : billDetailsTemp) {
                            if (billDetail.getProductId().equals(prodId)) {
                                billDetail.setQuantity(quantity);
                                break;
                            }
                        }
                    } else {
                        System.out.println("Nhập số lượng : ");
                        int quantity = Integer.parseInt(Console.scanner.nextLine());
                        if(billtype == BillType.EXPORT){
                            if(quantity > product.getQuantity()){
                                System.out.println("Số lượng sản phẩm trong kho không đủ!");
                                continue;
                            }
                        }
                        System.out.println("Nhập giá bán: ");
                        float price = Float.parseFloat(Console.scanner.nextLine());

                        billDetailTemp.setProductId(prodId);
                        billDetailTemp.setQuantity(quantity);
                        billDetailTemp.setPrice(price);
                        billDetailsTemp.add(billDetailTemp);
                    }
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
            if (billDetailsTemp.size() > 0) {
                System.out.println("Danh sách sản phẩm đã nhập: ");
                for (BillDetails billDetail : billDetailsTemp) {
                    System.out.println(billDetail.toString());
                }
                Bill bill = new Bill();
                bill.setBillCode(("HD" + generateRandomLongId()).substring(0, 9));
                bill.setBillType(billtype);
                bill.setEmpIdCreated(Storage.current_account.getEmpId());
                bill.setCreated(new Date());
                bill.setEmpIdAuth(Storage.current_account.getEmpId());
                bill.setAuthDate(new Date());
                bill.setBillStatus(ConstStatus.BillStt.CREATE);

                Bill addedBill = billService.addBill(bill);
                if(addedBill != null) {
                    System.out.println("Thêm phiếu thành công, đợi duyệt từ quản lý");
                    Bill billTemp = billService.findAny(addedBill.getBillCode());
                    BillDetails billDetail = new BillDetails();
                    for(BillDetails billDetailTemp : billDetailsTemp){
                        billDetail.setBillDetailId((generateRandomLongId()));
                        billDetail.setBillId(billTemp.getBillId());
                        billDetail.setProductId(billDetailTemp.getProductId());
                        billDetail.setQuantity(billDetailTemp.getQuantity());
                        billDetail.setPrice(billDetailTemp.getPrice());
                        billDetailService.addBillDetails(billDetail);
                        System.out.println(billDetail.toString());
                    }
                } else {
                    System.out.println("Thêm thất bại");
                }

            } else {
                System.out.println("Không có sản phẩm nào được nhập!");
            }
    }

    @Override
    public Bill searchBill(boolean type) {
        System.out.println("Nhập mã hóa đơn  hoặc mã code cần tìm: ");
        String any = Console.scanner.nextLine();
        Bill bill = billService.findAny(any);
        if(bill != null && bill.isBillType() == type){
            if(Storage.current_account.isPermission() == PermissionType.ADMIN || bill.getEmpIdCreated().equals(Storage.current_account.getEmpId() )) {
                return bill;
            } else {
                System.out.println("Bạn không có quyền duyệt hóa đơn này!");
            }
        }
        return null;
    }

    public  long generateRandomLongId() {
        long min = 1000000000L; // 10^9
        long max = 9999999999L; // 10^10 - 1
        return min + (long) (Math.random() * (max - min));
    }

    @Override
    public void approve(boolean billType) {

        Bill bill = searchBill(billType);
        if(bill != null){
            handleApprove(bill,billType);
        }else{
            System.out.println("Mã hóa đơn ko tồn tại");
        }
    }

        @Override
    public void update(boolean billType) {
       Bill bill = searchBill(billType);
       if(bill != null){
           handleUpdate(bill);
       }else{
           System.out.println("Mã hóa đơn ko tồn tại");
       }
    }
    public void handleUpdate(Bill bill){
        if(bill.getBillStatus() == ConstStatus.BillStt.CREATE || bill.getBillStatus() == ConstStatus.BillStt.CANCEL)  {
            if(bill.getBillStatus() == ConstStatus.BillStt.CREATE){
                bill.setBillStatus(ConstStatus.BillStt.CANCEL);
                System.out.println("Đã cập nhật trạng thái phiếu xuất: " + bill.getBillStatus());
            }else{
                bill.setBillStatus(ConstStatus.BillStt.CREATE);
                System.out.println("Đã cập nhật trạng thái phiếu xuất: " + bill.getBillStatus());
            }
            bill.setEmpIdAuth(Storage.current_account.getEmpId());
            System.out.println("Đã cập nhật mã nhân viên: " + bill.getEmpIdAuth());
            bill.setAuthDate(new Date());
            System.out.println("Đã cập nhật ngày: " + bill.getAuthDate());
            billService.updateBill(bill);
            //lấy danh sách billDetails
            List<BillDetails> billDetails = billDetailService.findAll();
            for(BillDetails billDetail : billDetails){
                if(billDetail.getBillId() == (bill.getBillId())){
                    System.out.println("Nhập số lượng mới: ");
                    billDetail.setQuantity(Integer.parseInt(Console.scanner.nextLine()));
                    System.out.println("Nhập giá  mới: ");
                    billDetail.setPrice(Float.parseFloat(Console.scanner.nextLine()));
                    billDetailService.updateBillDetails(billDetail);
                    System.out.println("Đã cập nhật lại chi tiết hóa đơn: " + billDetail.toString());
                }
            }
            System.out.println("Cập nhật phiếu thành công!");
        } else {
            System.out.println("Hóa đơn đã được duyệt");
        }
    }
    public void handleApprove(Bill bill,Boolean billtype){
        if(Storage.current_account.isPermission() == PermissionType.ADMIN || bill.getEmpIdCreated().equals(Storage.current_account.getEmpId() )) {
            if(bill.getBillStatus() == ConstStatus.BillStt.CREATE || bill.getBillStatus() == ConstStatus.BillStt.CANCEL)  {
                bill.setBillStatus(ConstStatus.BillStt.APPROVE);
                bill.setEmpIdAuth(Storage.current_account.getEmpId());
                bill.setAuthDate(new Date());
                billService.updateBill(bill);
                //lấy danh sách billDetails
                List<BillDetails> billDetails = billDetailService.findAll();
                System.out.println("Danh sách sản phẩm trong hóa đơn: ");
                for (BillDetails billDetail : billDetails) {
                    if (billDetail.getBillId() == (bill.getBillId())) {
                        System.out.println(billDetail.getProductId() + " - " + billDetail.getQuantity() + " - " + billDetail.getPrice());
                    }
                }
                System.out.println("Bạn muốn duyệt hóa đơn này ko (có/không)?: ");
                String ans = Console.scanner.nextLine();
                if (ans.equals("có")) {
                    for (BillDetails billDetail : billDetails) {
                        if (billDetail.getBillId() == (bill.getBillId())) {
                            List<Product> products = productService.findAll();
                            for (Product product : products) {
                                if (product.getProductId().equals(billDetail.getProductId())) {
                                    if(billtype == BillType.EXPORT){
                                        product.setQuantity(product.getQuantity() - billDetail.getQuantity());
                                        productService.updateProduct(product);
                                        System.out.println("đã cập nhật lại số tồn kho của sản phẩm: " + product.getProductName() + " thành công!");
                                        System.out.println("số lượng  giảm xuống từ " + (product.getQuantity()  + " thành " + (product.getQuantity() - billDetail.getQuantity())));
                                    }else if(billtype == BillType.IMPORT){
                                        product.setQuantity(product.getQuantity() + billDetail.getQuantity());
                                        productService.updateProduct(product);
                                        System.out.println("đã cập nhật lại số tồn kho của sản phẩm: " + product.getProductName() + " thành công!");
                                        System.out.println("số lượng tăng lên từ " + (product.getQuantity() - billDetail.getQuantity()) + " thành " + product.getQuantity());
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("Duyệt hóa đơn thành công!");
                } else {
                    System.out.println("Hủy duyệt hóa đơn thành công!");
                }
                System.out.println("Cập nhật hóa đơn thành công!");
            } else {
                System.out.println("Hóa đơn đã được duyệt");
            }
        } else {
            System.out.println("Bạn không có quyền duyệt hóa đơn này!");
        }
    }
    public void search(Boolean billType) {
        Bill bill = searchBill(billType);
        if(bill != null) {
            boolean check = true;
            do{
                System.out.println(bill.toString());
                System.out.println("1.Cập nhật phiếu ");
                System.out.println("2.Duyệt phiếu ");
                System.out.println("3: Thoát");
                System.out.print("Lựa chọn của bạn là : ");
                try{
                    int choice = Integer.parseInt(Console.scanner.nextLine());
                    switch (choice) {
                        case 1:
                            handleUpdate(bill);
                            break;
                        case 2:
                            handleApprove(bill,billType);
                        case 3 :
                            check = false;
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ!");
                            break;
                    }
                }catch (NumberFormatException e) {
                    System.err.println("Lựa chọn phải là số nguyên từ 1 - 2");
                }
            }
            while (check);
        } else {
            System.out.println("Vui lòng nhập lại mã hóa đơn");
        }
    }
}
