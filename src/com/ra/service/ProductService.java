package com.ra.service;

import com.ra.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product addProduct(Product product);
    Product findAny(Object... keys);
    Product updateProduct(Product product);
    List<Product> findPagination(int startPosition, int maxResult);

}
