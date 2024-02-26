package com.ra.service;

import com.ra.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product addProduct(Product product);
    Product findbyID(String id);
    Product updateProduct(Product product);
    Product searchProductByName(String name);
    List<Product> findPagination(int startPosition, int maxResult);

}
