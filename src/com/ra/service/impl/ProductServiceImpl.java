package com.ra.service.impl;

import com.ra.entity.Product;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private Repository<Product> productRepository;

    public ProductServiceImpl() {
        this.productRepository = new RepositoryImpl<>();
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll(Product.class);
    }
}
