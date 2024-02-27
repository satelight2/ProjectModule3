package com.ra.manager;

import com.ra.entity.Bill;
import com.ra.entity.BillDetails;
import com.ra.entity.Product;
import com.ra.model.BillType;
import com.ra.model.ConstStatus;
import com.ra.model.PermissionType;
import com.ra.repository.Repository;
import com.ra.service.impl.BillDetailServiceImpl;
import com.ra.service.impl.BillServiceImpl;
import com.ra.service.impl.ProductServiceImpl;
import com.ra.util.Console;
import com.ra.util.Storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillManager implements Manager{
    ProductServiceImpl productService = new ProductServiceImpl();
    BillServiceImpl billService = new BillServiceImpl();
    BillDetailServiceImpl billDetailService = new BillDetailServiceImpl();


    @Override
    public void show() {
        List<Bill> listReceipt =  billService.findAll();
        if(Storage.current_account.isPermission() == PermissionType.ADMIN){ // kiểm tra quyền, nếu là admin thì hiển thị tất cả hóa đơn
            if(listReceipt.isEmpty()){
                System.out.println("Không có hóa đơn nào");
            }else{
                for(Bill bill : listReceipt){
                    if(bill.isBillType() == BillType.EXPORT){
                        System.out.println(bill.toString());
                    }
                }
            }
        }else { // nếu không phải admin thì hiển thị hóa đơn của nhân viên đó
            if(listReceipt.isEmpty()){
                System.out.println("Không có hóa đơn nào");
            }else{
                for(Bill bill : listReceipt){
                    if(bill.getEmpIdCreated().equals(Storage.current_account.getEmpId()) && bill.isBillType() == BillType.EXPORT){
                        System.out.println(bill.toString());
                    }
                }
            }
        }
    }

    @Override
    public void showDetails() {
        List<BillDetails> listBillDetails = billDetailService.findAll();
        for(BillDetails billDetails : listBillDetails){
            System.out.println(billDetails.toString());
        }

    }

    @Override
    public  void add() {
        List<BillDetails> billDetailsTemp = new ArrayList<>();
        boolean stop = false;
        while (stop == false) {
            System.out.print("Nhập mã sp: ");
            String prodId = Console.scanner.nextLine();
            Product product = productService.findbyID(prodId);
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
                    System.out.println("Nhập số lượng xuất: ");
                    int quantity = Console.scanner.nextInt();
                    for (BillDetails billDetail : billDetailsTemp) {
                        if (billDetail.getProductId().equals(prodId)) {
                            billDetail.setQuantity(billDetail.getQuantity());
                            break;
                        }
                    }
                } else {
                    System.out.println("Nhập số lượng xuất: ");
                    int quantity = Integer.parseInt(Console.scanner.nextLine());
                    if(quantity > product.getQuantity()){
                        System.out.println("Số lượng sản phẩm trong kho không đủ!");
                        continue;
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
            bill.setBillType(BillType.EXPORT);
            bill.setBillCode(("HD" + System.currentTimeMillis()).substring(0, 9));
            bill.setEmpIdCreated(Storage.current_account.getEmpId());
            bill.setCreated(new Date());
            bill.setEmpIdAuth(Storage.current_account.getEmpId());
            bill.setAuthDate(new Date());
            bill.setBillStatus(ConstStatus.BillStt.CREATE);

            Bill addedBill = billService.addBill(bill);
            if(addedBill != null) {
                System.out.println("Thêm phiếu xuất thành công, đợi duyệt từ quản lý");
                Bill billTemp = billService.findByBillCodeOrID(addedBill.getBillCode());
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
//        // Nhập sản phẩm
//        List<BillDetails> billDetails = new ArrayList<>();
//        boolean stop = false;
//        while (stop == false) {
//            System.out.print("Nhập mã sp: ");
//            String prodId = Console.scanner.nextLine();
//
//            Product product = productRepository.findId(Product.class, prodId);
//            if (product != null && product.isProductStatus() == ConstStatus.ProductStt.ACTIVE) {
//                // Nhập số lượng xuất
//
//                // Kiểm tra số lượng còn trong kho
//
//                // Tạo bill_details
//                // Thêm vào list danh sách đã tạo
//                    // 1. TH sp đã có trong list billDetails
//                        // cập nhật lại số lượng
//                    // 2. TH sp chưa có trong list
//                        // thêm mới
//                System.out.print("Bạn có muốn tiếp tục nhập không (có/không)?: ");
//                String ans = Console.scanner.nextLine();
//                if (!ans.equals("có")) {
//                    stop = true;
//                }
//            } else {
//                // Xử lý để quay lại nhập lại hoặc kết thúc
//                System.out.println("Sản phẩm không tồn tại hoặc đã ngừng kinh doanh!");
//                System.out.print("Bạn có muốn tiếp tục nhập không (có/không)?: ");
//                String ans = Console.scanner.nextLine();
//                if (!ans.equals("có")) {
//                    stop = true;
//                }
//            }
//        }
//        // Kiểm tra lại list xem có xuất sp nào ko
//        if (billDetails.size() > 0) {
//            // Có thể hiển thị lại danh sách các sản phẩm đã nhập
//
//            // Tạo phiếu xuất
//            Bill bill = new Bill();
//            bill.setBillCode(("HD" + System.currentTimeMillis()).substring(0, 9));
//            bill.setBillType(BillType.EXPORT);
//            bill.setEmpIdCreated(Storage.current_user);
//            bill.setCreated(new Date());
//            System.out.println("Nhập mã user duyệt: ");
//            bill.setEmpIdAuth("");
//            bill.setBillStatus(ConstStatus.BillStt.CREATE);
//            // insert bills
//            // insert billDetails (lấy từ list billDetails)
//        }
    }

    @Override
    public void approve() {
        System.out.println("Nhập mã phiếu xuất cần duyệt: ");
        String billCode = Console.scanner.nextLine();
        Bill bill = billService.findByBillCodeOrID(billCode);
        if(bill != null) {
            System.out.println(bill.toString());
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
                                    product.setQuantity(product.getQuantity() - billDetail.getQuantity());
                                    productService.updateProduct(product);
                                    System.out.println("đã cập nhật lại số tồn kho của sản phẩm: " + product.getProductName() + " thành công!");
                                    System.out.println("số lượng  giảm xuống từ " + (product.getQuantity()  + " thành " + (product.getQuantity() - billDetail.getQuantity())));
                                }
                            }
                        }
                    }
                    System.out.println("Duyệt hóa đơn thành công!");
                } else {
                    System.out.println("Hủy duyệt hóa đơn thành công!");

                }
//                System.out.println("Danh sách chi tiết hóa đơn: " + bill.getBillCode());
//                for()
//                for(BillDetails billDetail : billDetails){
//                    if(billDetail.getBillId() == (bill.getBillId())){
//                        List<Product> products = productService.findAll();
//                        for(Product product : products){
//                            if(product.getProductId().equals(billDetail.getProductId())){
//                                product.setQuantity(product.getQuantity() - billDetail.getQuantity());
//                                productService.updateProduct(product);
//                                System.out.println("đã cập nhật lại số tồn kho của sản phẩm: " + product.getProductName() + " thành công!");
//                                System.out.println("số lượng  giảm xuống từ " + (product.getQuantity()  + " thành " + (product.getQuantity() - billDetail.getQuantity())));
//                            }
//                        }
//                    }
//                }
                System.out.println("Cập nhật hóa đơn thành công!");
            } else {
                System.out.println("Hóa đơn đã được duyệt");
            }
            } else {
                System.out.println("Bạn không có quyền duyệt hóa đơn này!");
            }
        } else {
            System.out.println("Hóa đơn không tồn tại!");
        }

    }

    @Override
    public void update() {
        System.out.println("Nhập mã phiếu xuất cần cập nhật: ");
        String billCode = Console.scanner.nextLine();
        Bill bill = billService.findByBillCodeOrID(billCode);
        if(bill != null) {
            System.out.println(bill.toString());
            if(Storage.current_account.isPermission() == PermissionType.ADMIN || bill.getEmpIdCreated().equals(Storage.current_account.getEmpId() )) {
                if(bill.isBillType()==BillType.EXPORT){
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
                                System.out.println("Nhập số lượng xuất mới: ");
                                billDetail.setQuantity(Integer.parseInt(Console.scanner.nextLine()));
                                System.out.println("Nhập giá xuất mới: ");
                                billDetail.setPrice(Float.parseFloat(Console.scanner.nextLine()));
                                billDetailService.updateBillDetails(billDetail);
                                System.out.println("Đã cập nhật lại chi tiết hóa đơn: " + billDetail.toString());
                            }
                        }
                        System.out.println("Cập nhật phiếu xuất thành công!");
                    } else {
                        System.out.println("Hóa đơn đã được duyệt");
                    }
                }
                else {
                    System.out.println("Hóa đơn đã được duyệt hoặc ko có hóa đơn nào do người dùng nhập, không thể cập nhật!");
                }
            } else {
                System.out.println("Bạn không có quyền cập nhật hóa đơn này!");
            }
        } else {
            System.out.println("Hóa đơn không tồn tại!");
        }


    }
    public void search() {
        System.out.println("Nhập mã hóa đơn  hoặc mã code cần tìm: ");
        String any = Console.scanner.nextLine();
        Bill bill = billService.findByBillCodeOrID(any);
        if(bill != null) {
            System.out.println(bill.toString());
            System.out.println("1.Cập nhật phiếu nhập");
            System.out.println("2.Duyệt phiếu nhập");
            System.out.print("Lựa chọn của bạn là : ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 1:
                    if(Storage.current_account.isPermission() == PermissionType.ADMIN || bill.getEmpIdCreated().equals(Storage.current_account.getEmpId() )) {
                        if(bill.isBillType()==BillType.EXPORT){
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
                                        System.out.println("Nhập số lượng xuất mới: ");
                                        billDetail.setQuantity(Integer.parseInt(Console.scanner.nextLine()));
                                        System.out.println("Nhập giá xuất mới: ");
                                        billDetail.setPrice(Float.parseFloat(Console.scanner.nextLine()));
                                        billDetailService.updateBillDetails(billDetail);
                                        System.out.println("Đã cập nhật lại chi tiết hóa đơn: " + billDetail.toString());
                                    }
                                }
                                System.out.println("Cập nhật phiếu xuất thành công!");
                            } else {
                                System.out.println("Hóa đơn đã được duyệt");
                            }
                        }
                        else {
                            System.out.println("Hóa đơn đã được duyệt hoặc ko có hóa đơn nào do người dùng nhập, không thể cập nhật!");
                        }
                    } else {
                        System.out.println("Bạn không có quyền cập nhật hóa đơn này!");
                    }
                    break;
                case 2:
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
                                                product.setQuantity(product.getQuantity() - billDetail.getQuantity());
                                                productService.updateProduct(product);
                                                System.out.println("đã cập nhật lại số tồn kho của sản phẩm: " + product.getProductName() + " thành công!");
                                                System.out.println("số lượng  giảm xuống từ " + (product.getQuantity()  + " thành " + (product.getQuantity() - billDetail.getQuantity())));
                                            }
                                        }
                                    }
                                }
                                System.out.println("Duyệt hóa đơn thành công!");
                            } else {
                                System.out.println("Hủy duyệt hóa đơn thành công!");

                            }
//                System.out.println("Danh sách chi tiết hóa đơn: " + bill.getBillCode());
//                for()
//                for(BillDetails billDetail : billDetails){
//                    if(billDetail.getBillId() == (bill.getBillId())){
//                        List<Product> products = productService.findAll();
//                        for(Product product : products){
//                            if(product.getProductId().equals(billDetail.getProductId())){
//                                product.setQuantity(product.getQuantity() - billDetail.getQuantity());
//                                productService.updateProduct(product);
//                                System.out.println("đã cập nhật lại số tồn kho của sản phẩm: " + product.getProductName() + " thành công!");
//                                System.out.println("số lượng  giảm xuống từ " + (product.getQuantity()  + " thành " + (product.getQuantity() - billDetail.getQuantity())));
//                            }
//                        }
//                    }
//                }
                            System.out.println("Cập nhật hóa đơn thành công!");
                        } else {
                            System.out.println("Hóa đơn đã được duyệt");
                        }
                    } else {
                        System.out.println("Bạn không có quyền duyệt hóa đơn này!");
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }
        } else {
            System.out.println("Hóa đơn không tồn tại!");
        }
    }


    public  long generateRandomLongId() {
        long min = 1000000000L; // 10^9
        long max = 9999999999L; // 10^10 - 1
        return min + (long) (Math.random() * (max - min));
    }

}
