package com.ra.service;

import com.ra.entity.Bill;
import com.ra.entity.Product;

import java.util.List;

public interface BillService {
    List<Bill> findAll();
    Bill addBill(Bill bill);
    Bill findbyID(String id);
    Bill updateBill(Bill bill);
    Bill searchProductByName(String name);
    Bill findByBillCodeOrID(String any);

}
