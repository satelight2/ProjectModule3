package com.ra.entity;

import com.ra.util.annotation.Column;
import com.ra.util.annotation.Id;
import com.ra.util.annotation.Table;

@Table(name = "bill_details")
public class BillDetails {
    @Id
    @Column(name = "Bill_Detail_Id")
    private long billDetailId;
    @Column(name = "Bill_Id")
    private long billId;
    @Column(name = "Product_Id")
    private String productId;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "Price")
    private float price;

    public BillDetails() {
    }
    public BillDetails(String productId, int quantity, float price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }


    public BillDetails(long billDetailId, long billId, String productId, int quantity, float price) {
        this.billDetailId = billDetailId;
        this.billId = billId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public long getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(long billDetailId) {
        this.billDetailId = billDetailId;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BillDetails{" +
                "billDetailId=" + billDetailId +
                ", billId=" + billId +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
