package com.ra.view.adminMenu;

import com.ra.entity.Product;
import com.ra.model.Header;
import com.ra.model.TableForm;
import com.ra.service.impl.ProductServiceImpl;
import com.ra.util.Console;
import com.ra.util.font.MessageCustom;


import java.util.Date;
import java.util.List;

import static com.ra.model.Header.printSeparator;

public class ProductMenu {
    public static ProductServiceImpl productService = new ProductServiceImpl();
    private static final int PAGE_SIZE = 10;
    public static void pMenu(){

       boolean check = true;
       do{
           System.out.println("******************PRODUCT MANAGEMENT****************");
           System.out.println("Chọn chức năng: ");
           System.out.println("1. Danh sách sản phẩm");
           System.out.println("2. Thêm mới sản phẩm");
           System.out.println("3. Cập nhật sản phẩm");
           System.out.println("4. Tìm kiếm sản phẩm");
           System.out.println("5. Cập nhật trạng thái sản phẩm");
           System.out.println("6. Thoát");
           System.out.println("Mời bạn chọn từ 1 - 6");
           System.out.print("Lựa chọn của bạn là: ");

           try {
               int choose = Integer.parseInt(Console.scanner.nextLine());
               switch (choose) {
                   case 1:
                       System.out.println("Danh sách sản phẩm");
                       showProductList();
                       break;
                   case 2:
                       System.out.println("Thêm mới sản phẩm");
                        addProduct();
                       break;
                   case 3:
                       System.out.println("Thực hiện cập nhật Sản phẩm");
                       System.out.println("Nhập mã sản phẩm cần cập nhật: ");
                          Product updatePro = productService.findAny(Console.scanner.nextLine());
                            if(updatePro != null){
                                Header.products();
                                printProductInfo(updatePro);
                                System.out.println("Nhập tên sản phẩm: ");
                                updatePro.setProductName(Console.scanner.nextLine());
                                System.out.println("Nhập nhà sản xuất: ");
                                updatePro.setManufacturer(Console.scanner.nextLine());
                                Date uDate = new Date();
                                updatePro.setCreated(uDate);
                                System.out.println("Nhập lô chứa sản phẩm: ");
                                updatePro.setBatch(Integer.parseInt(Console.scanner.nextLine()));
                                System.out.println("Nhập trạng thái sản phẩm: true/false ");
                                updatePro.setProductStatus(Boolean.parseBoolean(Console.scanner.nextLine()));
                                if(productService.updateProduct(updatePro) != null){
                                   MessageCustom.updateSuccess();
                                }else {
                                    MessageCustom.updateFailure();
                                }
                            }else {
                                MessageCustom.objectNotExist();

                            }
                       break;
                   case 4:
                       System.out.println("Tìm kiếm sản phẩm");
                       System.out.println("Nhập tên sản phẩm cần tìm: ");
                       String namePro = Console.scanner.nextLine();
                       Product searchPro = productService.findAny(namePro);
                       if(searchPro != null){
                           Header.products();
                           printProductInfo(searchPro);
                          }else {
                           MessageCustom.objectNotExist();;
                          }
                       break;
                   case 5:
                       System.out.println("Cập nhật trạng thái sản phẩm");
                       System.out.println("Nhập mã sản phẩm cần cập nhật: ");
                          Product updateStatus = productService.findAny(Console.scanner.nextLine());
                          if(updateStatus != null){
                              Header.products();
                              printProductInfo(updateStatus);
                              System.out.println("Nhập trạng thái sản phẩm: ");
                              System.out.println("0. Hoạt động (true)");
                              System.out.println("1. Không hoạt động (false)");
                              String status = Console.scanner.nextLine();
                              if(status.equals("0")){
                                  updateStatus.setProductStatus(true);
                                }else {
                                    updateStatus.setProductStatus(false);
                              }
                              if(productService.updateProduct(updateStatus) != null){
                                 MessageCustom.updateSuccess();
                              }else {
                                 MessageCustom.updateFailure();
                              }
                            }else {
                              MessageCustom.objectNotExist();
                          }
                       break;

                   case 6:
                       check = false;
                       break;
                   default:
                      MessageCustom.choiceFailure();
               }
           } catch (NumberFormatException e) {
               System.err.println("Lựa chọn phải là số nguyên từ 1 - 6");

           }
       } while (check);
   }
    public static void showProductList() {
        ProductServiceImpl productService = new ProductServiceImpl();
        int page = 1;
        while (true) {
            System.out.println("Danh sách sản phẩm:");
            List<Product> productList = productService.findPagination(page, PAGE_SIZE);
            Header.products();
            for (Product product : productList) {
               printProductInfo(product);
            }
            System.out.println("Trang " + page);
            System.out.println("1. Trang trước");
            System.out.println("2. Trang sau");
            System.out.println("3. Quay lại menu chính");
            System.out.print("Lựa chọn của bạn là: ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 1:
                    if (page > 1) {
                        page--;
                    } else {
                        System.out.println("Bạn đã ở trang đầu tiên.");
                    }
                    break;
                case 2:
                    if (!productList.isEmpty()) {
                        page++;
                    } else {
                        System.out.println("Bạn đã ở trang cuối cùng.");
                    }
                    break;
                case 3:
                    return;
                default:
                    MessageCustom.choiceFailure();
            }
        }
    }
    public static void addProduct(){
        System.out.println("Thêm mới sản phẩm");
        Product product = new Product();
        System.out.println("Nhập mã sản phẩm: ");
        product.setProductId(Console.scanner.nextLine());
        System.out.println("Nhập tên sản phẩm: ");
        product.setProductName(Console.scanner.nextLine());
        System.out.println("Nhập nhà sản xuất: ");
        product.setManufacturer(Console.scanner.nextLine());
        Date pDate = new Date();
        product.setCreated(pDate);
        System.out.println("Nhập lô chứa sản phẩm: ");
        product.setBatch(Integer.parseInt(Console.scanner.nextLine()));
        System.out.println("Nhập số lượng sản phẩm: ");
        product.setQuantity(Integer.parseInt(Console.scanner.nextLine()));
        System.out.println("Nhập trạng thái sản phẩm: ");
        product.setProductStatus(Boolean.parseBoolean(Console.scanner.nextLine()));

        if( productService.addProduct(product) != null){
            MessageCustom.createdSuccess();
        }else {
            MessageCustom.createdFailure();
        }
    }
    public static void printProductInfo(Product pro) {
        String info = String.format(TableForm.products.column,
                pro.getProductId(),
                pro.getProductName(),
                pro.getManufacturer(),
                pro.getCreated(),
                pro.getBatch(),
                pro.getQuantity(),
                pro.isProductStatus() ? "Hoạt Động" : "Không hoạt động");
        System.out.println(info);
        int headerLength = info.length();
        printSeparator(headerLength);
    }
}

