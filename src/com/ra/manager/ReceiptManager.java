package com.ra.manager;

import com.ra.entity.Bill;
import com.ra.entity.BillDetails;
import com.ra.entity.Product;
import com.ra.model.BillType;
import com.ra.model.ConstStatus;
import com.ra.model.PermissionType;
import com.ra.repository.Repository;
import com.ra.service.ProductService;
import com.ra.service.impl.BillDetailServiceImpl;
import com.ra.service.impl.BillServiceImpl;
import com.ra.service.impl.ProductServiceImpl;
import com.ra.util.Console;
import com.ra.util.Storage;
import com.ra.view.adminMenu.ProductMenu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ReceiptManager implements Manager{
      BillDetailServiceImpl billDetailService = new BillDetailServiceImpl();
      ProductService productService = new ProductServiceImpl();
      BillServiceImpl billService = new BillServiceImpl();

    @Override
    public void show() {
        List<Bill> listReceipt =  billService.findAll();
        if(Storage.current_account.isPermission() == PermissionType.ADMIN){ // kiểm tra quyền, nếu là admin thì hiển thị tất cả hóa đơn
            if(listReceipt.isEmpty()){
                System.out.println("Không có hóa đơn nào");
            }else{
                for(Bill bill : listReceipt){
                    if(bill.isBillType() == BillType.IMPORT){
                        System.out.println(bill.toString());
                    }
                }
            }
        }else { // nếu không phải admin thì hiển thị hóa đơn của nhân viên đó
            if(listReceipt.isEmpty()){
                System.out.println("Không có hóa đơn nào");
            }else{
                for(Bill bill : listReceipt){
                    if(bill.getEmpIdCreated().equals(Storage.current_account.getEmpId()) && bill.isBillType() == BillType.IMPORT){
                        System.out.println(bill.toString());
                    }
                }
            }
        }


    }
    @Override
    public void showDetails() {
        List<BillDetails> listBillDetails = billDetailService.findAll();
            if(listBillDetails.isEmpty()){
                System.out.println("Không có chi tiết hóa đơn nào");
            }else{
                for(BillDetails billDetails : listBillDetails){
                    System.out.println(billDetails.toString());
                }
            }
    }
    @Override
    public  void add() {
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
                    System.out.println("Nhập số lượng nhập: ");
                    int quantity = Console.scanner.nextInt();
                    for (BillDetails billDetail : billDetailsTemp) {
                        if (billDetail.getProductId().equals(prodId)) {
                            billDetail.setQuantity(billDetail.getQuantity());
                            break;
                        }
                    }
                } else {
                    System.out.println("Nhập số lượng nhập: ");
                    int quantity = Integer.parseInt(Console.scanner.nextLine());
                    System.out.println("Nhập giá nhập: ");
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
            bill.setBillType(BillType.IMPORT);
            bill.setBillCode(("HD" + System.currentTimeMillis()).substring(0, 9));
            bill.setEmpIdCreated(Storage.current_account.getEmpId());
            bill.setCreated(new Date());
            bill.setEmpIdAuth(Storage.current_account.getEmpId());
            bill.setAuthDate(new Date());
            bill.setBillStatus(ConstStatus.BillStt.CREATE);

            Bill addedBill = billService.addBill(bill);
            if(addedBill != null) {
                System.out.println("Thêm phiếu nhập thành công, đợi duyệt từ quản lý");
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
    public void run() {
//        Bill bill = new Bill();
//        bill.setBillCode(("HD" + System.currentTimeMillis()).substring(0, 9));
//        bill.setBillType(BillType.EXPORT);
//        bill.setEmpIdCreated(Storage.current_user);
//        bill.setCreated(new Date());
//        System.out.println("Nhập mã user duyệt: ");
//        bill.setEmpIdAuth("");
//        bill.setBillStatus(ConstStatus.BillStt.CREATE);
//        // insert bills
//        // insert billDetails (lấy từ list billDetails)

    }
    @Override
    public void approve() {
        Bill bill = searchBill();
        if(bill != null){
            handleApprove(bill);
        }else{
            System.out.println("Vui lòng nhập lại mã hóa đơn");
        }
    }
    @Override
    public void update() {
        Bill bill = searchBill();
        if(bill != null){
            handleUpdate(bill);
        }else{
            System.out.println("Vui lòng nhập lại mã hóa đơn");
        }
    }


    public void search() {
        Bill bill = searchBill();
        if(bill != null) {
            boolean check = true;
            do{
                System.out.println(bill.toString());
                System.out.println("1.Cập nhật phiếu nhập");
                System.out.println("2.Duyệt phiếu nhập");
                System.out.println("3: Thoát");
                System.out.print("Lựa chọn của bạn là : ");
                try{
                    int choice = Integer.parseInt(Console.scanner.nextLine());
                    switch (choice) {
                        case 1:
                            handleUpdate(bill);
                            break;
                        case 2:
                            handleApprove(bill);
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
    public  long generateRandomLongId() {
        long min = 1000000000L; // 10^9
        long max = 9999999999L; // 10^10 - 1
        return min + (long) (Math.random() * (max - min));
    }
    public Bill searchBill(){
        System.out.println("Nhập mã hóa đơn cần cập nhật: ");
        String billCode = Console.scanner.nextLine();
        Bill bill = billService.findAny(billCode);
        if(bill != null && bill.isBillType() == BillType.IMPORT){
            if(Storage.current_account.isPermission() == PermissionType.ADMIN || bill.getEmpIdCreated().equals(Storage.current_account.getEmpId() )) {
                return bill;
            } else {
                System.out.println("Bạn không có quyền duyệt hóa đơn này!");
            }
        }
        return null;
    };
    public void handleUpdate(Bill bill){
                System.out.println(bill.toString());
                if(bill.getBillStatus() == ConstStatus.BillStt.CREATE || bill.getBillStatus() == ConstStatus.BillStt.CANCEL)  {
                    if(bill.getBillStatus() == ConstStatus.BillStt.CREATE){
                        bill.setBillStatus(ConstStatus.BillStt.CANCEL);
                        System.out.println("Đã cập nhật trạng thái hóa đơn: " + bill.getBillStatus());
                    }else{
                        bill.setBillStatus(ConstStatus.BillStt.CREATE);
                        System.out.println("Đã cập nhật trạng thái hóa đơn: " + bill.getBillStatus());
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
                            System.out.println("Nhập số lượng nhập mới: ");
                            billDetail.setQuantity(Integer.parseInt(Console.scanner.nextLine()));
                            System.out.println("Nhập giá nhập mới: ");
                            billDetail.setPrice(Float.parseFloat(Console.scanner.nextLine()));
                            billDetailService.updateBillDetails(billDetail);
                            System.out.println("Đã cập nhật lại tiết hóa đơn: " + billDetail.toString());
                        }
                    }
                    System.out.println("Cập nhật hóa đơn thành công!");
                } else {
                    System.out.println("Hóa đơn đã được duyệt");
                }
    }



    public void handleApprove(Bill bill){
                System.out.println(bill.toString());
                if (bill.getBillStatus() == ConstStatus.BillStt.CREATE || bill.getBillStatus() == ConstStatus.BillStt.CANCEL) {
                    bill.setBillStatus(ConstStatus.BillStt.APPROVE);
                    bill.setEmpIdAuth(Storage.current_account.getEmpId());
                    bill.setAuthDate(new Date());
                    billService.updateBill(bill);
                    //lấy danh sách billDetails
                    System.out.println("Danh sách sản phẩm trong hóa đơn: ");
                    List<BillDetails> billDetails = billDetailService.findAll();
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
                                        product.setQuantity(product.getQuantity() + billDetail.getQuantity());
                                        productService.updateProduct(product);
                                        System.out.println("đã cập nhật lại số tồn kho của sản phẩm: " + product.getProductName() + " thành công!");
                                        System.out.println("số lượng tăng lên từ " + (product.getQuantity() - billDetail.getQuantity()) + " thành " + product.getQuantity());
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
    }
}
