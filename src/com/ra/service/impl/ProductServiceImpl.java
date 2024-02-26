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

    @Override
    public Product addProduct(Product product) {
        return this.productRepository.add(product);
    }

    @Override
    public Product findbyID(String id) {
        return this.productRepository.findId(Product.class, id);
    }


    @Override
    public Product  updateProduct(Product product) {
        return this.productRepository.edit(product);
    }

    @Override
    public Product searchProductByName(String name) {
        return this.productRepository.findByName(Product.class, name);
    }

    @Override
    public List<Product> findPagination(int startPosition, int maxResult) {
        return this.productRepository.findByPagination(Product.class,startPosition, maxResult);
    }


}
