package com.ra.manager;

import com.ra.entity.Product;
import com.ra.service.ProductService;
import com.ra.service.impl.ProductServiceImpl;
import com.ra.util.Console;

public class ProductManager implements Manager {
    private ProductService productService;

    public ProductManager() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public void run() {
        System.out.println("1. Danh sách sản phẩm");
        System.out.print("Chọn chức năng: ");
        int choose = Integer.parseInt(Console.scanner.nextLine());
        switch (choose) {
            case 1:
                System.out.println("Danh sách sản phẩm");
                for (Product p : productService.findAll()) {
                    System.out.println(p.getProductName());
                }
                break;
            case 6:
                return;
            default:
                System.out.println("Bạn chọn sai chức năng!");
        }
    }
}
