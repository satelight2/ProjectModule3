package com.ra.entity;

import com.ra.util.annotation.Column;
import com.ra.util.annotation.Id;
import com.ra.util.annotation.Table;
import com.ra.util.annotation.Name;

import java.util.Date;

@Table(name = "products")
public class Product {
    @Id
    @Column(name = "Product_Id")
    private String productId;
    @Name
    @Column(name = "Product_Name")
    private String productName;
    @Column(name = "Manufacturer")
    private String manufacturer;
    @Column(name = "Created")
    private Date created;
    @Column(name = "Batch")
    private Integer batch;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "Product_Status")
    private boolean productStatus;

    public Product() {
    }

    public Product(String productId, String productName, String manufacturer, Date created, Integer  batch, int quantity, boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.created = created;
        this.batch = batch;
        this.quantity = quantity;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

}
